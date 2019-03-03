/**
 * This class is used to print data and messages on the console.
 * @author Furkan Kayar
 */

public class View{
	
	/**
	 * Constructor prints the name of the program.
	 */
	public View() {
		
		System.out.print("------ Brent’s Method Hashing ------");
	}
	
	/**
	 * Prints new search.
	 */
	public void printNewSearch() {
		
		System.out.print("\n\nSearch : ");
	}
	
	/**
	 * Prints the result of a search. 
	 * @param key hash code of given key.
	 * @param count frequency of given key.
	 * @param index index of given key.
	 */
	public void printResults(int key, int count, int index) {
		
		System.out.println("Key : " + key + 
						   "\nCount : " + count +
						   "\nIndex : " + index) ;
	}
	
	/**
	 * Prints a message that shows given word is not found file.
	 * @param search is searched word.
	 */
	public void printNotFound(String search) {
		
		System.out.println(search + " is not found in text file.");
	}
	
	/**
	 * Prints a message that shows program is closed.
	 */
	public void printClosedMessage() {
		
		System.out.println("Program is closed.");
	}
	
	/**
	 * Prints an error if the given file is not found.
	 */
	public void printTxtReadError() {
		
		System.out.println("\n\nERROR : Txt file is not found.");
	}
	
	/**
	 * Prints all fields of an entry.
	 * @param i index number of key.
	 * @param key word of key.
	 * @param hashCode hash code of key.
	 * @param count frequency of key.
	 */
	public void printTableLine(int i, Key key, int hashCode, int count) {
			
		System.out.println(String.format("%d. Key: %-20s HashCode: %-20d Count: %-5d", i, key, hashCode, count));
			
	}
	
	/**
	 * Prints a message that shows total word number.
	 * @param word total word number.
	 * @param differentWords total unique word number.
	 */
	public void printTotalValues(int word,int differentWords) {
		
		System.out.println("\nTotal words : " + word + 
							"\nTotal unique words : " + differentWords);
	}

}
