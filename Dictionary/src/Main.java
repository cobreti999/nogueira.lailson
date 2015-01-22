/*
 * 
 * WordFinder
 * author: Lailson Lima Nogueira
 * COMP 460 Algorithms and Complexity
 * Ms in Computer Science
 * Loyola University Chicago
 * Spring 2015
 * 
 */


import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	private static char matrix[][];
	private static TrieTree dictionary = new TrieTree();
	
	/*
	 * 
	 * createMatrix(int n) creates a char matrix with order of size n with random chars
	 * 
	 */
	
	public static void createMatrix(int n){
		matrix = new char[n][n];
		for (int i =0; i < n; i++){
			for (int j = 0; j < n; j++){
				matrix[i][j] = getRandomChar();
			}
		}
	}
	
	/*
	 * 
	 * getRandomChar() returns a randomly generated char
	 * 
	 */
	
	public static char getRandomChar(){
        String chars = "abcdedfghijklmnopqrstuvwxyz";
        int n = chars.length();
        Random r = new Random();
        return chars.charAt(r.nextInt(n));
	}
	
	/*
	 * 
	 * printMatrix() prints the matrix created
	 * 
	 */
	
	public static void printMatrix(){
		for (int i =0; i < matrix.length; i++){
			for (int j = 0; j < matrix.length; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/*
	 * 
	 * searchMatrixHorizontally() performs an horizontal search looking for every possible combination of words
	 * 
	 */
	
	public static String searchMatrixHorizontally(){
		String horizontalWords="";
		//Search through lines
		for (int line =0; line < matrix.length; line++){
			//Search through columns
			for (int column = 0; column < matrix.length; column++){
				//endIndex marks the end of the word on the line
				for (int endIndex = matrix.length-1; endIndex >= column; endIndex--){
					//Get the string between the current column and endIndex inside the line
					String temp = getHorizontalWord(line, column, endIndex);
					//Search the word inside the dictionary
					if (compareWords(temp)){
						//If the word is found, add to the list of words
						horizontalWords += (temp + " ");
						//remove word from dictionary to avoid repetitions
						dictionary.remove(temp);
					}
				}
			}
		}
		return horizontalWords;
	}
	
	/*
	 * 
	 * searchMatrixVertically() performs a vertical search looking for every possible combination of words
	 * 
	 */
	
	public static String searchMatrixVertically(){
		String verticalWords="";
		//Search through columns
		for (int column =0; column < matrix.length; column++){
			//Search through lines
			for (int line = 0; line < matrix.length; line++){
				//endIndex marks the end of the word on the column
				for (int endIndex = matrix.length-1; endIndex >= line; endIndex--){
					//Get the string between the current line and endIndex inside the column
					String temp = getVerticalWord(line, column, endIndex);
					if (compareWords(temp)){
						//If the word is found, add to the list of words
						verticalWords += (temp + " ");
						//remove word from dictionary to avoid repetitions
						dictionary.remove(temp);
					}
				}
			}
		}
		return verticalWords;
	}
	
	/*
	 * 
	 * getHorizontalWord(int line, int column, int endIndex) returns the string between the 
	 * current column and endIndex inside the line
	 * 
	 */
	static int t = 0;
	public static String getHorizontalWord(int line, int column, int endIndex){
		String result = "";
		for (int k = column; k <= endIndex; k++){
			//the line is fixed, what changes is the column
			result += matrix[line][k];
			t++;
		}
		return result;
	}
	
	/*
	 * 
	 * getVerticalWord(int line, int column, int endIndex) returns the string between the 
	 * current line and endIndex inside the column
	 * 
	 */
	
	public static String getVerticalWord(int line, int column, int endIndex){
		String result = "";
		for (int k = line; k <= endIndex; k++){
			//the column is fixed, what changes is the line
			result += matrix[k][column];
			t++;
		}
		return result;
	}
	
	/*
	 * 
	 * compareWords(String word) returns true if the string is inside the dictionary
	 * 
	 */
	
	public static boolean compareWords(String word){
		t = t+ word.length();
		if (dictionary.search(word)){
			return true;
		}
		return false;
	}
	
	/*
	 * 
	 * getWordsFromDictionary() scans and stores the Dictionary file inside a 
	 * Trie Tree data structure
	 * 
	 */
	
	public static void getWordsFromDictionary(){
		File file = new File("EnglishDictionary.txt");
		try {
			Scanner scanner = new Scanner(file);
			//Reads all the content of the file
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
		        dictionary.add(s);
		    }
		    scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 
	 * createMatrixFromFile(File file) creates the char matrix with provided text file
	 * 
	 */
	
	public static void createMatrixFromFile(File file){
		int n = findOrder(file);
		matrix = new char[n][n];
		int currentLine =0;
		try {
			Scanner scanner = new Scanner(file);
			//Reads all the content of the file
			while (scanner.hasNextLine()) {
				//Converts the line to a char array
				char[] line = scanner.nextLine().toCharArray();
				//Add the char array to the current line of the matrix
				for (int j =0; j < n; j++){
					matrix[currentLine][j] = line[j];
				}
				currentLine++;
		    }
		    scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 
	 * findOrder(File file) is an auxiliary method to find the order 
	 * of the matrix provided by the text file
	 * 
	 */
	
	private static int findOrder(File file){
		int n = 0;
		try {
			Scanner scanner = new Scanner(file);
			//Reads all the content of the file
			while (scanner.hasNextLine()) {
				scanner.nextLine();
				n++;
		    }
		    scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
	
	
	/*
	 * 
	 * main(String args[]) is the main method and is responsible to create the menu 
	 * 
	 */
	
	
	public static void main(String args[]){
		
		Scanner keyboard = new Scanner(System.in);
		boolean validOption = true;
		do{
			System.out.println("Press (r) to generate a matrix with random characters or Press (i) to input a text file with a matrix");
			char option = keyboard.next().charAt(0);
			if (option == 'r' || option == 'R'){
				validOption = true;
				boolean validOption2 = true;
				do{
					System.out.println("Choose the matrix order (1<n<=10000):");
					int n = keyboard.nextInt();
					if (n > 1 && n <= 10000){
						createMatrix(n);
						validOption2=true;
					}
					else{
						validOption2 = false;
					}
				}while (!validOption2);
			}
			else if (option == 'i' || option == 'I'){
				validOption = true;
				System.out.println("Type the name of the .txt file (must be in the same directory)");
				String fileName = keyboard.next();
				try{
					File file = new File(fileName);
					createMatrixFromFile(file);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			else{
				validOption = false;
			}
		}while(!validOption);		
		
		//printMatrix on the screen
		printMatrix();
		//Populates the Trie Tree with the words of the dictionary
		getWordsFromDictionary();
		System.out.println("Searching Horizontally...");
		long t1 = System.currentTimeMillis();
		//Search Horizontally
		String horizontalWords = searchMatrixHorizontally();
		long t2 = System.currentTimeMillis();
		System.out.println("time elapsed in search: " + (t2-t1) + " miliseconds");
		System.out.println("Searching Vertically...");
		long t3 = System.currentTimeMillis();
		//Search Vertically
		String veritcalWords = searchMatrixVertically();
		long t4 = System.currentTimeMillis();
		System.out.println("time elapsed in search: " + (t4-t3) + " miliseconds");
		System.out.println();
		System.out.println("Printing Strings:");
		System.out.println("Horizontal Words: " + horizontalWords);
		System.out.println("Vertical Words: " + veritcalWords);
		System.out.println("End.");
	}

}
