/**
 * This class simulates an hash map entry. It has an constructor that creates an entry
 * with given key and value. Then constructor sets the count number as 1. 
 * 
 * @author Furkan Kayar
 *
 * @param <K> Key type of entry.
 * @param <V> Value type of entry.
 */

public class MapEntry<K,V> implements Entry<K,V>{

	private K key;
	private V value;
	private int count;

	
	/**
	 * Creates a new entry with given key and value.
	 * @param key key of new entry.
	 * @param value value of new entry.
	 */
	public MapEntry(K key, V value) {
		
		this.key = key;
		this.value = value;
		this.count = 1;

	}
	
	/**
	 * @return count number of entry.
	 */
	@Override
	public int getCount() {
		
		return this.count;
	}
	
	/**
	 * @return key of entry.
	 */
	@Override
	public K getKey() {
		return this.key;
	}
	
	/**
	 * @return value of entry.
	 */
	@Override
	public V getValue() {
		return this.value;
	}
	
	/**
	 * @param key new key of entry.
	 */
	protected void setKey(K key) {
		
		this.key = key;
	}
	
	/**
	 * Sets the new value of entry and returns the old value.
	 * @param value new value of entry.
	 * @return old value of entry.
	 */
	protected V setValue(V value) {
		
		V old = this.value;
		this.value = value;
		return old;
	}
	
	/**
	 * @param count number of entry.
	 */
	protected void setCount(int count) {
		
		this.count = count;
	}
	
	/**
	 * Increases the count value by 1. 
	 */
	protected void increaseCount() {
		
		this.count++;
	}
	
	
	/**
	 * @return the hash code of key of entry.
	 */
	@Override
	public int hashCode() {
		
		return this.key.hashCode();
	}

}
