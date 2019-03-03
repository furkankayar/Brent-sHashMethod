
/**
 * This interface defines required methods of an entry.
 * 
 * @author Furkan Kayar
 *
 * @param <K> Key type of entry.
 * @param <V> Value type of entry.
 */

public interface Entry<K,V> {
	
	/**
	 * @return key of entry.
	 */
	K getKey();
	/**
	 * @return value of entry.
	 */
	V getValue();
	/**
	 * @return count of entry that shows the collision number.
	 */
	int getCount();
}
