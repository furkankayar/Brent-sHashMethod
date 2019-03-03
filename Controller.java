
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class controls the all program. It reads a file and puts the
 * words of file to a hash map. This is the main class that has main method.
 * It has two constructors. One of them is default constructor and
 * the other one takes file name as parameter.
 * 
 * @author Furkan Kayar
 */
public class Controller{

	private BrentHashMap<Key, Integer> hashTable;
	private Scanner scan;
	private BufferedReader read;
	private FileReader fileRead;
	private View view; 
	private Key key;
	
	private static final String DEFAULTFILE = "story.txt";
	
	public static void main(String[] args) {
		
		new Controller();
	}
	
	/**
	 * Constructor that calls other constructor with default file name.
	 */
	public Controller() {
		
		this(DEFAULTFILE);
	}
	
	/**
	 * Second constructor.
	 * @param fileName name of file that will be read.
	 */
	public Controller(String fileName) {
		
		try {
			 
			 hashTable = new BrentHashMap<Key,Integer>();
			 view = new View();
			 fileRead = new FileReader(fileName);
			 read = new BufferedReader(fileRead);
			 scan = new Scanner(System.in);
			 readFile();
			 run();
			 
		}catch(IOException e) { view.printTxtReadError();}
	}
	
	/**
	 * Reads file line by line and splits lines to words.
	 * Then puts the words to Brent's Hash Table.
	 * @throws IOException in case of problem while reading file. 
	 */
	private void readFile() throws IOException {
		
		String line = null;
		String[] words = null;
		
		while((line = read.readLine()) != null) {
			
			words = line.split(" ");
			for(String s : words) {
			
				if(!s.equals("")) {
				
					s = s.toLowerCase();
				
					if(s.contains("ı")) {
						s = s.replace("ı", "i");
					}
					
					key = new Key(s);
						
					hashTable.put(key, 1);
				}		
			}
		}
	}
	
	/**
	 * Controls the interaction with user while program is running.
	 * Takes inputs from user and performs required operations.
	 */
	private void run() {
		
		Key key = null;
		String input = null;
		
		do {
			
			view.printNewSearch();
			input = scan.nextLine().toLowerCase();
			
			
			if(input.contains("ı"))
				input = input.replace("ı", "i");
			
			if(!"-exit".equals(input) && !"-show".equals(input)) {
				
				key = new Key(input);
			
				if(hashTable.get(key) != null) {
				
					view.printResults(key.hashCode(), 
										hashTable.getCount(key), 
											hashTable.getIndex(key));
				}
				else 
					view.printNotFound(key.toString());
			}
			
		}while(!"-exit".equals(input) && !"-show".equals(input));
		
		
		if("-show".equals(input)) {
			
			show();
			
		}
			
		view.printClosedMessage();

	}
	
	/**
	 * Shows the all entries in hash map.
	 * Calculates and prints total word number and total unique word number. 
	 */
	private void show(){
		
		int totalWords = 0;
		int i = 0;
	
		for(Entry<Key,Integer> entry : hashTable.entrySet()) {
			
			if(entry != null) {
				view.printTableLine(i, entry.getKey(), entry.getKey().hashCode(), entry.getCount());
				totalWords += entry.getCount();
			}
			i++;
		}
	
		view.printTotalValues(totalWords, hashTable.size());
	}

			
}
