import java.util.ArrayList;

/**
 * This class simulates a hash map that uses Brent's method when a collision occured.
 * Collisions help to count the frequency of keys in hash map.
 * 
 * @author Furkan Kayar
 *
 * @param <K> key type of entries.
 * @param <V> value type of entries.
 */
public class  BrentHashMap<K,V> extends AbstractHashMap<K,V> {


	private MapEntry<K,V>[] buckets;

	/**
	 * Calls super class' constructor.
	 */
	public BrentHashMap() { super(); }
	public BrentHashMap(int capacity, float loadFactor) {	super(capacity, loadFactor);}
	
	
	/**
	 * Initializes the map with defined capacity.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void createTable() {
		
		buckets = (MapEntry<K,V>[]) new MapEntry[this.capacity];
	}
	
	/**
	 * Finds the step number to closest empty slot.
	 * Starts from index number and walks with increment value.
	 * @param index start index of key.
	 * @param increment step size of key.
	 * @return step number to closest empty slot.
	 */
	private int findEmptySlot(int index, int increment) {
		
		int step = 0;
		
		while(buckets[index] != null) {
			
			index = (index + increment) % buckets.length;
			step += 1;
		}
		
		return step; 
	}
	
	
	/**
	 * @return value of given key.
	 */
	@Override
	protected V bucketGet(K key) {
		
		MapEntry<K,V> element = contains(key);
		
		if(element == null) return null;
		return element.getValue();
	}
	
	/**
	 * @return index of given key.
	 */
	@Override
	public int getIndex(K key) {
		
		int index = compression(key);
		
		if(contains(key) != null) {
			while(buckets[index] != null ) {
	
				if(buckets[index].getKey().hashCode() == key.hashCode())
					return index;
				
				index = (index + increment(key)) % buckets.length;
			} 
		}
		
		return -1;
	}
	
	/**
	 * @param key 
	 * @return frequency of given key.
	 */
	public int getCount(K key) {
		
		MapEntry<K,V> element = contains(key);
		
		if(element != null) return element.getCount();
		
		return -1;
	}
		
	/**
	 * Puts new entry to hash map. If given key is found, increases its count(frequency) number.
	 * If it is a new key and the calculated index is empty, places new entry to empty place.
	 * If it is a new key and the calculated index is not empty, it calculates new index by using Brent's method.
	 */
	@Override
	protected V bucketPut(K key, V value) {
		
		MapEntry<K,V> newEntry = new MapEntry<K,V>(key, value);
		MapEntry<K,V> tempEntry = newEntry;
		int index = compression(newEntry.getKey());
		int tempIndex = index;
		MapEntry<K,V> oldEntry = buckets[index];
		MapEntry<K,V> ifFound = null;
		int closestLength = 0;
		int bestI = 0; 
		int bestJ = 0;
	
		
		if(value == null) throw new IllegalArgumentException("Given value can not be null");
		
		else if(oldEntry == null) {
			
			buckets[index] = newEntry;
			this.size += 1;
			
		}
		else if((ifFound = contains(key)) != null) { // Collision detected.
			
			V temp = ifFound.getValue();
			ifFound.increaseCount();
			return temp; 
		}
		
		else { 
			
			bestI = findEmptySlot(index, increment(oldEntry.getKey()));
			bestJ = 0;
			closestLength = bestI + bestJ;
			
			if(findEmptySlot(index, increment(newEntry.getKey())) <= closestLength) {
				
				bestJ = findEmptySlot(index, increment(newEntry.getKey()));
				bestI = 0;
				closestLength = bestI + bestJ;
			}

			
			for(int j = 1 ; buckets[tempIndex] != null ; j++) {
				
				tempIndex = (index + j*increment(newEntry.getKey())) % buckets.length;

				if(buckets[tempIndex] != null) {

					if(j + findEmptySlot(tempIndex, increment(buckets[tempIndex].getKey())) < closestLength) {
					
						bestI = findEmptySlot(tempIndex, increment(buckets[tempIndex].getKey()));
						bestJ = j;
						closestLength = bestI + bestJ;
					}
				}
			
			}
			
			if(bestI == 0) {
				
				buckets[index] = oldEntry;
				buckets[(index + increment(newEntry.getKey()) * bestJ) % buckets.length] = newEntry;
			}
			
			else if(bestJ == 0) {
				
				buckets[index] = newEntry;
				buckets[(index + increment(oldEntry.getKey()) * bestI) % buckets.length] = oldEntry;
			}
			
			else{
				
				tempEntry = buckets[(index + bestJ * increment(newEntry.getKey())) % buckets.length];
				buckets[(index + bestJ * increment(newEntry.getKey()) + bestI * increment(tempEntry.getKey())) % buckets.length] = tempEntry;
		   	    buckets[(index + bestJ * increment(newEntry.getKey())) % buckets.length] = newEntry;
			
			}
			
			this.size += 1;
		}
		
		if(size == threshold) 
			resize();
		//System.out.println(size + " " + buckets.length + " " + threshold);
		return null;
	}
	
	
	/**
	 * Searches given key in hash map. If it founds returns the entry that has given key.
	 * @param key
	 * @return entry that has given key.
	 */
	public MapEntry<K,V> contains(K key) {
		
		int index = compression(key);
		int increment = increment(key);
		int counter = 0;
		
		if(buckets[index] != null) {
			
			if(key.equals(buckets[index].getKey())) {
				
				return buckets[index];
			}
			else {
				
				while(buckets[index] != null) {
					
					if(buckets[index].getKey().hashCode() != key.hashCode()) {
						
						index = (index + increment) % buckets.length;
						counter += 1;
						
						if(counter >= buckets.length) {
						return null;
						}
					}
					else {
						return buckets[index];	
					}
				
				}	
			}	
		}
		return null;	
	}
	
	/**
	 * Compresses hash code of given key by length of map.
	 */
	@Override
	protected int compression(K key) {
		
		int index = (key.hashCode() % buckets.length) ;
		if(index < 0)
			index += buckets.length;
		return index;
	}
	
	/**
	 * Calculates the step size of given key. 
	 * This step size is used to calculate new indexes of entries when collision occurs.
	 * @param key
	 * @return step size of given key.
	 */
	private int increment(K key) {
		
		int increment = (key.hashCode() / buckets.length) % buckets.length;
		
		if(increment == 0)
			increment = 1; 
		else if(increment < 0)
			increment += buckets.length;
		return increment;
	}
	
	/**
	 * Resizes map when threshold is reached.
	 * New capacity is the smallest prime number that is greater than 2 times of old capacity. 
	 */
	private void resize() {
		
		int newCap = newPrimeCapacity();
		MapEntry<K,V>[] temp = buckets;
		this.size = 0;
		buckets = new MapEntry[newCap];
		
		for(MapEntry<K,V> e : temp) {
			
			if(e != null) {
				bucketPut(e.getKey(),e.getValue());
				
			}
		}
		
	}
	
	/**
	 * Calculates new capacity.
	 * @return new capacity.
	 */
	private int newPrimeCapacity() {
		
		int newCapacity = buckets.length * 2;
		boolean isPrime = false; 
		int counter = 0;
		
		do{
			for(int j = 2 ; j < newCapacity ; j++) 
				if(newCapacity % j == 0) 
					counter++;
				
			if(counter == 0) 
				isPrime = true;
			else {
				newCapacity += 1;
				counter = 0;
			}
			
		}while(!isPrime);
		
		threshold =(int)(loadFactor * newCapacity);

		return newCapacity;
	}
	
	/**
	 * @return iterable collection of entry set.
	 */
	@Override
	public Iterable<Entry<K,V>> entrySet() {
		
		ArrayList<Entry<K,V>> buffer = new ArrayList<>(this.capacity);
		for(Entry<K,V> element : buckets)
			buffer.add(element);
			
		return buffer;
	}
	
	/**
	 * Sets new value to given key.
	 * @return old value of given key.
	 */
	@Override
	public V bucketSet(K key, V value) {
		
		MapEntry<K,V> element = contains(key);
		V temp = null;
		
		if(element != null) {
			temp = element.getValue();
			element.setValue(value);
		}
		
		return temp;
		
	}

}
