/**
 * This class defines some methods and fields that can be used any hash map implementation.
 * Defines default capacity and default load factor for an has map.
 * 
 * @author Furkan Kayar
 *
 * @param <K> key type of hash map entries.
 * @param <V> value type of has map entries.
 */
public abstract class AbstractHashMap<K,V> implements Map<K,V> {

	private static final int DEFAULT_CAPACITY = 101;
	private static final float DEFAULT_LOADFACTOR = 0.7f;
	protected float loadFactor;
	protected int threshold;
	protected int size;
	protected int capacity;

	/**
	 * Creates a new hash map with given values.
	 * @param capacity capacity of hash map.
	 * @param loadFactor loadFactor of hash map. It helps to calculate threshold.
	 */
	public AbstractHashMap(int capacity, float loadFactor) {
		
		if(capacity <= 0) throw new IllegalArgumentException("Capacity must be bigger than zero.");
		this.capacity = capacity;
		this.loadFactor = loadFactor;
		threshold = (int) (capacity * loadFactor);
		createTable();
		
	}
	
	/**
	 * Creates a new hash map with default values.
	 */
	public AbstractHashMap() {
		
		this(DEFAULT_CAPACITY, DEFAULT_LOADFACTOR);
	}
	
	/**
	 * @return entry number of hash map.
	 */
	@Override
	public int size() {
		
		return this.size;
	}
	
	/**
	 * @return value of given key.
	 */
	@Override
	public V get(K key) {
		
		return bucketGet(key);
	}
	
	
	/**
	 * @return value of newly inserted key.
	 */
	@Override
	public V put(K key, V value) {
		
		return bucketPut(key, value);

	}
	
	/**
	 * @return old value of given key.
	 */
	@Override
	public V set(K key, V value) {
		
		return bucketSet(key, value);

	}
	
	/**
	 * @return emptiness of hash map.
	 */
	@Override
	public boolean isEmpty() {
		
		return size() == 0;
	}
	


	
	protected abstract void createTable();
	
	protected abstract V bucketGet(K key);
	
	protected abstract V bucketPut(K key, V value);
	
	protected abstract V bucketSet(K key, V value);
	
	protected abstract int compression(K key);
	
}
