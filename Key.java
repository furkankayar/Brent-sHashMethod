/**
 * This class simulates a key. Keys keep an string key and its hash value.
 * 
 * @author Furkan Kayar
 */
public class Key {

	private String key;
	private int hash; 
	
	/**
	 * Creates new Key with given string key.
	 * @param key value of Key.
	 */
	public Key(String key) {
		
		this.key = key;
	}
	
	/**
	 * Calculates and returns the hash code of string key.
	 * @return hash code of string key.
	 */
	@Override 
	public int hashCode() {
		
		int h = hash;
			
		if(h == 0 && key.length() > 0) {
				
			char value[] = key.toCharArray();
				
			for(int i = 0 ; i < value.length; i++) {
					
				h = ((33 * h) + value[i]);
			}
			
			hash = h;
		}
		return h;
	}
	
	/**
	 * @return value of key.
	 */
	@Override
	public String toString() {
		
		return this.key;
	}
	
}
