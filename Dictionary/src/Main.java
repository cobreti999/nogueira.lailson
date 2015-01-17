import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;


public class Main {
	
	private static char matrix[][];
	private static ArrayList<String> dictionary = new ArrayList<String>();
	//private static TrieTree dictionary = new TrieTree();
	private static ArrayList<String> words = new ArrayList<String>();
	
	public static char[][] createMatrix(int n){
		matrix = new char[n][n];
		for (int i =0; i < n; i++){
			for (int j = 0; j < n; j++){
				matrix[i][j] = getRandomChar();
			}
		}
		return matrix;
	}
	
	public static char getRandomChar(){
        String chars = "abcdedfghijklmnopqrstuvwxyz";
        //System.out.println(chars);
        int n = chars.length();
        Random r = new Random();
        return chars.charAt(r.nextInt(n));
	}
	
	public static void printMatrix(){
		for (int i =0; i < matrix.length; i++){
			for (int j = 0; j < matrix.length; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/*public static void searchMatrixHorizontally(){
		int indexBegin=0;
		int indexEnd=0;
		String currentString="";
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix.length; j++){
				currentString = "" + matrix[i][j];
			}
			//currentString tem a linha
			//compara a palavra da linha com o dicionario
			if (compareWords(currentString)){
				words.add(currentString);
			}
		}
	}*/
	
	public static void searchMatrixHorizontally(){
		for (int line =0; line < matrix.length; line++){
			for (int column = 0; column < matrix.length; column++){
				for (int endIndex = matrix.length-1; endIndex >= column; endIndex--){
					String temp = getWord(line, column, endIndex);
					if (compareWords(temp)){
						words.add(temp);
					}
				}
			}
		}
	}
	
	public static String getWord(int line, int column, int endIndex){
		String result = "";
		for (int k = column; k <= endIndex; k++){
			result += matrix[line][k];
		}
		return result;
	}
	
	public static boolean compareWords(String word){
		Iterator i = dictionary.listIterator();
		while (i.hasNext()){
			String temp = i.next().toString();
			if (word.equalsIgnoreCase(temp)){
				return true;
			}
		} 
		return false;
	}
	
	public static void getWordsFromDictionary(){
		File file = new File("EnglishWordList.txt");
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
	
	public static void printStrings(){
		Iterator i = words.listIterator();
		while (i.hasNext()){
			System.out.println(i.next().toString());
		} 
	}
	
	
	public static void main(String args[]){
		System.out.println("Choose the matrix order (up to 10000):");
		//Scanner keyboard = new Scanner(System.in);
		//int n = keyboard.nextInt();
		int n = 100;
		createMatrix(n);
		printMatrix();
		getWordsFromDictionary();
		System.out.println("Searching Horizontally...");
		long t1 = System.currentTimeMillis();
		searchMatrixHorizontally();
		long t2 = System.currentTimeMillis();
		System.out.println("time elapsed in search: " + (t2-t1)/1000 + " seconds");
		printStrings();
	}

}
