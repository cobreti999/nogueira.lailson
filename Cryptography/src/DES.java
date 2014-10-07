/*
 * 
 * Data Encryption Standard (DES) implementation using Java 7.
 * @author: Lailson Lima Nogueira - Graduate Student at Loyola University Chicago
 * Course: COMP 447 - Intrusion Detection and Network Security 
 * Professor: Corby Schmitz
 * 
 */

import java.io.File;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DES {
	private static File file = new File("test-msg.txt");
	//All the des sub-keys are stored in an ArrayList<String> 
	private static ArrayList<String> keys = new ArrayList<String>();
	//The 3-dimensional matrix variable Sbox stores the values of the tables used in the algorithm
	private final static String Sbox[][][] = {
			//Box 1
			{
				//line 0
				{"14", "4", "13", "1", "2", "15", "11", "8", "3", "10", "6", "12", "5", "9", "0", "7"},
				//line 1
				{"0", "15", "7", "4", "14", "2", "13", "1", "10", "6", "12", "11", "9", "5", "3", "8"},
				//line 2
				{"4", "1", "14", "8", "13", "6", "2", "11", "15", "12", "9", "7", "3", "10", "5", "0"},
				//line 3
				{"15", "12", "8", "2", "4", "9", "1", "7", "5", "11", "3", "14", "10", "0", "6", "13"},
			},
			//Box 2
			{
				//line 0
				{"15", "1", "8", "14", "6",  "11", "3", "4",  "9",  "7",  "2",  "13", "12", "0",  "5",  "10"},
				//line 1
				{"3",  "13", "4",  "7",  "15", "2",  "8",  "14", "12", "0",  "1",  "10", "6",  "9",  "11", "5"},
				//line 2
				{"0",  "14", "7",  "11", "10", "4",  "13", "1",  "5",  "8",  "12", "6",  "9",  "3",  "2",  "15"},
				//line 3
				{"13", "8",  "10", "1",  "3",  "15", "4",  "2",  "11","6",  "7",  "12", "0",  "5",  "14", "9"},
			},
			//Box 3
			{
				//line 0
				{"10", "0",  "9",  "14", "6",  "3",  "15", "5",  "1",  "13", "12", "7",  "11", "4",  "2",  "8"},
				//line 1
				{"13",  "7", "0",  "9",  "3",  "4",  "6",  "10", "2",  "8",  "5",  "14", "12", "11", "15", "1"},
				//line 2
				{"13", "6",  "4",  "9",  "8",  "15", "3",  "0",  "11", "1",  "2",  "12", "5",  "10", "14", "7"},
				//line 3
				{"1",  "10", "13", "0",  "6",  "9",  "8",  "7",  "4",  "15", "14", "3",  "11", "5",  "2",  "12"},
			},
			//Box 4
			{
				//line 0
				{"7",  "13", "14", "3",  "0",  "6",  "9",  "10", "1",  "2",  "8",  "5",  "11", "12", "4",  "15"},
				//line 1
				{"13", "8",  "11", "5",  "6",  "15", "0",  "3",  "4",  "7",  "2",  "12", "1",  "10", "14", "9"},
				//line 2
				{"10", "6",  "9",  "0",  "12", "11", "7",  "13", "15", "1",  "3",  "14", "5",  "2",  "8",  "4"},
				//line 3
				{"3",  "15", "0",  "6",  "10", "1",  "13", "8",  "9",  "4", "5",  "11", "12", "7",  "2",  "14"},
			},
			//Box 5
			{
				//line 0
				{"2",  "12", "4",  "1",  "7",  "10", "11", "6",  "8",  "5",  "3",  "15", "13", "0",  "14", "9"},
				//line 1
				{"14", "11", "2",  "12", "4",  "7",  "13", "1",  "5",  "0",  "15", "10", "3",  "9",  "8",  "6"},
				//line 2
				{"4",  "2",  "1",  "11", "10", "13", "7",  "8",  "15", "9",  "12", "5",  "6",  "3",  "0", "14"},
				//line 3
				{"11", "8",  "12", "7",  "1",  "14", "2",  "13", "6",  "15", "0",  "9",  "10", "4",  "5",  "3"},
			},
			//Box 6
			{
				//line 0
				{"12", "1",  "10", "15", "9",  "2",  "6",  "8",  "0",  "13", "3",  "4",  "14", "7",  "5",  "11"},
				//line 1
				{"10", "15", "4",  "2",  "7",  "12", "9",  "5",  "6",  "1",  "13", "14", "0",  "11", "3",  "8"},
				//line 2
				{"9",  "14", "15", "5",  "2",  "8",  "12", "3",  "7",  "0",  "4",  "10", "1",  "13", "11", "6"},
				//line 3
				{"4",  "3",  "2",  "12", "9",  "5",  "15", "10", "11", "14", "1",  "7",  "6",  "0",  "8", "13"},
			},
			//Box 7
			{
				//line 0
				{"4",  "11", "2",  "14", "15", "0",  "8",  "13", "3",  "12", "9",  "7",  "5",  "10", "6",  "1"},
				//line 1
				{"13", "0",  "11", "7",  "4",  "9",  "1",  "10", "14", "3",  "5",  "12", "2",  "15", "8",  "6"},
				//line 2
				{"1",  "4",  "11", "13", "12", "3",  "7",  "14", "10", "15", "6",  "8",  "0",  "5",  "9",  "2"},
				//line 3
				{"6",  "11", "13", "8",  "1", "4",  "10", "7",  "9",  "5",  "0",  "15", "14", "2",  "3",  "12"},
			},
			//Box 8
			{
				//line 0
				{"13", "2",  "8",  "4",  "6",  "15", "11", "1",  "10", "9",  "3",  "14", "5",  "0",  "12", "7"},
				//line 1
				{"1",  "15", "13", "8",  "10", "3",  "7",  "4",  "12", "5",  "6",  "11", "0",  "14", "9",  "2"},
				//line 2
				{"7",  "11", "4",  "1",  "9",  "12", "14", "2",  "0",  "6",  "10", "13", "15", "3",  "5",  "8"},
				//line 3
				{"2",  "1",  "14", "7",  "4",  "10", "8",  "13", "15", "12", "9",  "0",  "3",  "5",  "6",  "11"},
			},
		};
	

		
	public static String convertBinaryToString(String binary){
		  Charset oem = Charset.forName("Cp437");
			ByteBuffer b = ByteBuffer.allocate(0xFF - 0x20 - 1);
			for (int i = 0x20; i < 0xFF; i++) {
			    if (i == 0x7F) {
			        // skip DEL
			        continue;
			    }

			    b.put((byte) i);
			}
			b.flip();
			CharBuffer c = oem.decode(b);
			String table = c.toString();
			int ascii = 32;
			String result = "";
			StringBuilder asciiTable = new StringBuilder(table);
			asciiTable.insert(127-ascii, ' ');
			
			for (int i = 0; i < 32; i++) {
				asciiTable.insert(0, ' ');
			}
			asciiTable.append(' ');
			
			table = asciiTable.toString();
			
			int bits = 8;
			for (int i =0; i < binary.length(); i += bits){
				int value = Integer.parseInt(binary.substring(i, i + bits), 2);
				result += table.charAt(value);
			}
			return result;
	}
	
	/*
	 * 
	 * breakMsgInBlocks(String msg, int blockSize) splits the message into blocks of 
	 * blockSize length and stores each block in an ArrayList
	 * 
	 */
	
	
	public static ArrayList<String> breakMsgInBlocks(String msg, int blockSize){
		ArrayList<String> blocks = new ArrayList<String>();
		int begin = 0, end = blockSize;
		for (int i = 0; i < msg.length(); i++) {
			//Divides the message into blocks of 'blockSize' size and add these blocks to the ArrayList
			blocks.add(msg.substring(begin, end));
			//Increment the variables so that the next time, new blocks could be added to the ArrayList
			begin += blockSize;
			end += blockSize;
			if (end > msg.length()){
				//If we have fewer bits than blockSize to encrypt, just divide until msg.lenght()
				end = msg.length();
				String lastBlock = msg.substring(begin, end);
				String bitsLeftOver = "";
				//According to fips 81, if a user has less than sixty-four bits to encrypt, 
				//then the least significant bits of the unused portion of the input data 
				//block must be padded. 
				if (msg.length() % blockSize != 0){
					//Calculates the number of bits that are going to be padded.
					int numBitsLeftOver = blockSize - (msg.length()-begin);
					System.out.println(numBitsLeftOver);
					//Also, according to fips 81:  if the last data bit of the message is 
					//"0" then "1"s are used as padding bits and if the last data bit is"1" 
					//then "0"s are used
					for (int j =0; j < numBitsLeftOver;j++){
						if (lastBlock.endsWith("1")){
							bitsLeftOver += "0";
						}
						else{
							bitsLeftOver += "1";
						}
					}
					blocks.add(lastBlock+bitsLeftOver);
				}	
				break;
			}
		}
		return blocks;
	}
	
	/*
	 * 
	 * initialPermutation(ArrayList<String blocks) performs the DES initialPermutation
	 * in each one of the blocks.
	 * 
	 */
	
	public static ArrayList<String> initialPermutation(ArrayList<String> blocks){
		ArrayList<String> permutedList = new ArrayList<String>();		
		//Search through every block
		for (String string : blocks) {
			int initValue = 57, reset = 57;
			String temp = "";
			//Temp will store the new string formed by the permutation of elements
			//according to the initial permutation table
			//Permutes the first halt of the table. j < 4 insures that only the first 4 lines
			//of the table will be permuted. i < 8 represents each bit.
			for (int i =0, j =0; i < 8 && j < 4; i++){
				//Take the value of the bit at the specific position and store in the temp variable
				temp += string.charAt(initValue);
				initValue -= 8;
				//When we reach the end of the line
				if (initValue < 0){
					//Reassigns the value of initValue
					initValue = reset+2;
					reset = reset+2;
					//Start the loop again
					i=-1;
					//j marks the number of lines
					j++;
				}
			}
			//Other Part of table
			//Now we reassign the value of initValue and apply the same logic as before
			initValue = 56; 
			reset = 56;
			for (int i =0, j =0; i < 8 && j < 4; i++){
				temp += string.charAt(initValue);
				initValue -= 8;
				if (initValue < 0){
					initValue = reset+2;
					reset = reset+2;
					i=-1;
					j++;
				}
			}
			//Temp should have a 64 bit string of permuted values according to the initial permutation table
			//Add this string to the ArrayList and make the same process to the next block
			permutedList.add(temp);
		}
		return permutedList;
	}
	
	/*
	 * 
	 * generateKeys(String desKey) generates all des-subkeys based on the original desKey
	 * 
	 */
	
	public static ArrayList<String> generateKeys(String desKey){
		//The ArrayList keys, will store all the des-subkeys
		ArrayList<String> keys = new ArrayList<String>();
		String c[] = new String[17];
		String d[] = new String[17];
		//Performs the permutedChoice 1 on the desKey
		int initValue = 56, reset = 56;
		String temp = "";
		//Temp will store the new string formed by the permutation of elements
		//according to the initial permutation table
		//Permutes the first halt of the table. j < 4 insures that only the first 4 lines
		//of the table will be permuted. i < 8 represents each bit.
		for (int i =0, j =0; i < 8 && j < 3; i++){
			//Take the value of the bit at the specific position and store in the temp variable
			temp += desKey.charAt(initValue);
			initValue -= 8;
			//When we reach the end of the line
			if (initValue < 0){
				//Reassigns the value of initValue
				initValue = reset+1;
				reset = reset+1;
				//Start the loop again
				i=-1;
				//j marks the number of lines
				j++;
			}
		}
		temp += desKey.charAt(59);
		temp += desKey.charAt(51);
		temp += desKey.charAt(43);
		temp += desKey.charAt(35);
		c[0] = temp;
		temp="";
		//Other Part of table
		//Now we reassign the value of initValue and apply the same logic as before
		initValue = 62; 
		reset = 62;
		for (int i =0, j =0; i < 8 && j < 3; i++){
			temp += desKey.charAt(initValue);
			initValue -= 8;
			if (initValue < 0){
				initValue = reset-1;
				reset = reset-1;
				i=-1;
				j++;
			}
		}
		temp += desKey.charAt(27);
		temp += desKey.charAt(19);
		temp += desKey.charAt(11);
		temp += desKey.charAt(3);
		d[0] = temp; 
		//The first 28 bits of the permuted key is c[0] and the other 28 bits is d[0]
		//Now that we have c[0] and d[0], we must calculate all the k[16] keys.
		for (int i =1; i <= 16; i++){
			//Perform one or two circular left shifts on both c[i] and d[i]
			c[i] = shiftLeft(c[i-1], i);
			d[i] = shiftLeft(d[i-1], i);
			//Perform another permutation (permutedChoice2) on c[i] and d[i] and
			//saves the result on the ArrayList of keys.
			keys.add(permutedChoice2(c[i], d[i]));
		}
		return keys;
	}
	
	/*
	 * 
	 * shiftLeft(String value, int round) performs one or two circular left shift on the 
	 * binary string. The number of circular left shifts is controlled by the variable
	 * round.
	 * 
	 */
	
	private static String shiftLeft(String value, int round){
		String shiftedString = "";
		if (round==1 || round==2 || round==9 || round==16){
			//Perform 1 left shift
			StringBuffer s = new StringBuffer(value);
			char c = s.charAt(0);
			//Append the first character to the end of the string
			s.append(c);
			//Delete the first character
			s.deleteCharAt(0);
			shiftedString = s.toString();
		}
		else{
			//Perform 2 left shifts
			StringBuffer s = new StringBuffer(value);
			char c = s.charAt(0);
			char d = s.charAt(1);
			s.append(c);
			s.append(d);
			s.deleteCharAt(0);
			s.deleteCharAt(0);
			shiftedString = s.toString();
		}
		return shiftedString;
	}
	
	/*
	 * 
	 * permutedChoice2(String c, String d) performs a string permutation on the concatenated
	 * string c+d.
	 * 
	 */
	
	private static String permutedChoice2(String c, String d){
		String permutedString = "";
		String cd = c+d;
		permutedString = ""+cd.charAt(13)+cd.charAt(16)+cd.charAt(10)+cd.charAt(23)+cd.charAt(0)+cd.charAt(4)
				+ cd.charAt(2)+cd.charAt(27)+cd.charAt(14)+cd.charAt(5)+cd.charAt(20)+cd.charAt(9)
				+ cd.charAt(22)+cd.charAt(18)+cd.charAt(11)+cd.charAt(3)+cd.charAt(25)+cd.charAt(7)
				+ cd.charAt(15)+cd.charAt(6)+cd.charAt(26)+cd.charAt(19)+cd.charAt(12)+cd.charAt(1)
				+ cd.charAt(40)+cd.charAt(51)+cd.charAt(30)+cd.charAt(36)+cd.charAt(46)+cd.charAt(54)
				+ cd.charAt(29)+cd.charAt(39)+cd.charAt(50)+cd.charAt(44)+cd.charAt(32)+cd.charAt(47)
				+ cd.charAt(43)+cd.charAt(48)+cd.charAt(38)+cd.charAt(55)+cd.charAt(33)+cd.charAt(52)
				+ cd.charAt(45)+cd.charAt(41)+cd.charAt(49)+cd.charAt(35)+cd.charAt(28)+cd.charAt(31);
		return permutedString;
	}
	
	
	/*
	 * 
	 * exclusiveOr(String s1, String s2) performs a XOR operation between two binary string
	 * 
	 */
	
	
	public static String exclusiveOr(String s1, String s2){
		
		BigInteger i1 = new BigInteger(s1, 2);
		BigInteger i2 = new BigInteger(s2, 2);
		BigInteger res = i1.xor(i2);
		String s3 = res.toString(2);
		
		String zeros = "";
		int size = s3.length();
		//add zeros if necessary to make a 4 bit number
		for (int i = size; i < s1.length(); i++) {
			zeros += "0";
		}
		s3 = zeros+s3;
		return (s3);
	}
	
	/*
	 * 
	 * processInputData(String text, String operation) is where occurs the processing of the input data.
	 * It receives a String 'text' as argument and a String 'operation' which indicates if the user
	 * wants to encrypt or decrypt the text. 
	 * 
	 */
	
	public static String processInputData(String text, String operation){
		//The first step is to divide the text into blocks of 64 bits according to DES specification
		ArrayList<String> blocks;
		if (text.length() > 64){
			blocks = breakMsgInBlocks(text, 64);
		}else{
			//If the block is smaller than 64, just paddle the remaining bits to complete 64 bits
			//and add to the ArrayList
			String paddledString = paddle(text, 64);
			blocks = new ArrayList<String>();
			blocks.add(paddledString);
		}
		//Performs an initialPermutation on all blocks of the message
		ArrayList<String> permutedList = initialPermutation(blocks);
		
		
		ArrayList<String> outputBlocks = new ArrayList<String>();
		ArrayList<String> outputBlocksAscii = new ArrayList<String>();
		String finalBinaryEncrypted = "";
		String finalAsciiEncrypted = "";
		//Iterate over all blocks
		for (String block : permutedList) {
			String[] firstHalf = new String[17];
			String[] secondHalf = new String[17];
			//Divide each block into two parts, each one with 32 bits
			firstHalf[0] = block.substring(0, 32);
			secondHalf[0] = block.substring(32, 64);
			String expandedR = "";
			//Process the input data for 16 rounds
			for (int i =0 ; i < 16; i ++){
				String sboxResult ="";
				//Expanding secondHalf according to e-bit selection table
				expandedR = "" + secondHalf[i].charAt(31) + secondHalf[i].substring(0, 5) 
						+ secondHalf[i].substring(3, 9) + secondHalf[i].substring(7, 13)
						+ secondHalf[i].substring(11, 17) + secondHalf[i].substring(15, 21)
						+ secondHalf[i].substring(19, 25) + secondHalf[i].substring(23, 29)
						+ secondHalf[i].substring(27, 32) + secondHalf[i].charAt(0);	
				//Exclusive-OR E(secondHalf[i]) with K[i]
				String exclusiveResult;
				if (operation.equalsIgnoreCase("decrypt")){
					//If the operation is 'decrypt' apply the keys in the inverse order
					exclusiveResult = exclusiveOr(expandedR, keys.get(15-i));
				}else{
					//If the operation is 'encrypt' apply the keys in the normal order
					exclusiveResult = exclusiveOr(expandedR, keys.get(i));
				}
				//Divide the string into blocks of 6 bits
				ArrayList<String> string6bitsArray = breakMsgInBlocks(exclusiveResult, 6);
				int box = 0;
				//iterate over the 8 sbox
				for (String string6bits : string6bitsArray) {
					//Calculate which row of the s-box (first and last element of the 6-bit block)
					String stringRow = "" + string6bits.charAt(0)+string6bits.charAt(5);
					//Convert the binary number to base 10
					int row = Integer.parseInt(stringRow, 2);
					//Calculate which column of the s-box (the remaining four bits)
					String stringColumn = "" + string6bits.substring(1, 5);
					//Convert the binary number to base 10
					int column = Integer.parseInt(stringColumn, 2);
					//The result of the Sbox lookup is converted to a 4 bit number. 
					//The result is concatenated with the sboxResult variable
					sboxResult += convertStringTo4Bits(Sbox[box++][row][column]);
				}
				//Performs another permutation
				String permutedString = permutationAfterSbox(sboxResult);
				//Exclusive-OR firstHalf with permutedString
				String xorResult = exclusiveOr(firstHalf[i], permutedString);
				//This test avoids access to invalid positions in the array
				if (i < 16){
					//Prepares the initial values for the next iteration 
					firstHalf[i+1] = secondHalf[i];
					secondHalf[i+1] = xorResult;
				}
			}
			//Perform FinalPermutation on the string formed by the following 
			//concatenation "secondhalf[16]firsthalf[16]"
			String lastRoundResult = secondHalf[16]+firstHalf[16];
			String finalString = finalPermutation(lastRoundResult);
			String encryptedString = convertBinaryToString(finalString);
			outputBlocks.add(finalString);
			outputBlocksAscii.add(encryptedString);
			finalBinaryEncrypted += finalString;
			finalAsciiEncrypted += encryptedString;
		}
		System.out.println("printing encrypted binary strings:");
		for (String string : outputBlocks) {
			System.out.println(string);
		}
		System.out.println("printing printable ascii strings:");
		for (String string : outputBlocksAscii) {
			System.out.println(string);
		}
		System.out.println("encrypted text:");
		System.out.println(finalBinaryEncrypted);
		System.out.println("encrypted ascii text: ");
		System.out.println(finalAsciiEncrypted);
		return finalBinaryEncrypted;
	}
	
	/*
	 * 
	 * finalPermutation(String input) performs the finalPermutation in the block
	 * 
	 */
	
	public static String finalPermutation(String input){
		String output = "";
		int index = 0;
		for (int i =0; i < 8; i++){
			output = output + input.charAt(39-index)+input.charAt(7-index)+input.charAt(47-index)+input.charAt(15-index)
					+ input.charAt(55-index)+input.charAt(23-index)+input.charAt(63-index)+input.charAt(31-index);
			index++;
		}
		return output;
	}
	
	/*
	 * 
	 * permutationAfterSbox(String input) performs a string permutation after the string
	 * passed through the s-box phase.
	 * 
	 */
	
	public static String permutationAfterSbox(String input){
		String output = "";
		output = "" + input.charAt(15)+input.charAt(6)+input.charAt(19)+input.charAt(20)
				+ input.charAt(28)+input.charAt(11)+input.charAt(27)+input.charAt(16)
				+input.charAt(0)+input.charAt(14)+input.charAt(22)+input.charAt(25)
				+input.charAt(4)+input.charAt(17)+input.charAt(30)+input.charAt(9)
				+input.charAt(1)+input.charAt(7)+input.charAt(23)+input.charAt(13)
				+input.charAt(31)+input.charAt(26)+input.charAt(2)+input.charAt(8)
				+input.charAt(18)+input.charAt(12)+input.charAt(29)+input.charAt(5)
				+input.charAt(21)+input.charAt(10)+input.charAt(3)+input.charAt(24);
		return output;
	}
	
	/*
	 * 
	 * convertStringTo4Bits(String s) is a utility method to convert a string and
	 * make it a 4 bit digit
	 * 
	 */
	
	private static String convertStringTo4Bits(String s){
		String result = Integer.toBinaryString(Integer.parseInt(s));
		int size = result.length();
		String zeros = "";
		//add zeros if necessary to make a 4 bit number
		for (int i = size; i < 4; i++) {
			zeros += "0";
		}
		return (zeros+result);
	}
	
	/*
	 * 
	 * readBytesFromFile(File f) receives a file, get it's bytes and converts it to 
	 * a string in binary 8-bit format
	 * 
	 */
	
	public static String readBytesFromFile(File f){
		Path path = Paths.get(f.getPath());
		String binary = "";
		try{
			//breaks the file into a byte array 
			byte[] data = Files.readAllBytes(path);
			  for (byte b : data)
			  {
			     int val = b;
			     //Converts each byte to a binary, 8-bit format
			     for (int i = 0; i < 8; i++)
			     {
			    	binary += ((val&128) == 0 ? 0 : 1);
			        val <<= 1;
			     }
			  }
		}catch(Exception e){
			e.printStackTrace();
		}
		return binary;

	}
	
	
	/*
	 * 
	 * menu() asks for the user to specify a text file with a message to be encrypted/decrypted
	 * 
	 */
	
	public static boolean menu(){
		Scanner keyboard = new Scanner(System.in);
		String filename;
		boolean exit = false;
		do{
			System.out.println("Type the name of the file to be encrypted (exit to leave)");
			filename = keyboard.next();
			//If the user types 'exit', closes the program
			if (filename.equalsIgnoreCase("exit")){
				exit = true;
				break;
			}
			file = new File(filename);
			//Check if the file exists
			if (!file.exists()){
				System.out.print("The file " + filename + " doesn't exists in the current directory.");
			}
		}while(!file.exists());
		return exit;
	}

	/*
	 * 
	 * paddle(String msg, int blockSize) if the block is smaller than the blockSize,
	 * the remaining bits are paddled to make the block have the same size as the blockSize
	 * 
	 */
	
	public static String paddle(String msg, int blockSize){
		String bitsLeftOver = "";
		//Calculates the number of bits that are going to be padded.
		int numBitsLeftOver = blockSize - msg.length();
		//If the last data bit of the message is 
		//"0" then "1"s are used as padding bits and if the last data bit is"1" 
		//then "0"s are used
		for (int j =0; j < numBitsLeftOver;j++){
			if (msg.endsWith("1")){
				bitsLeftOver += "0";
			}
			else{
				bitsLeftOver += "1";
			}
		}
		return (msg+bitsLeftOver);
		
	}
	
	
	public static void main(String args[]){
		//Call menu and check if the user didn't type 'exit'
		if (!menu()){
			//Get all the bytes from the file in a binary 8-bit format string
			String binary = readBytesFromFile(file);
			//An example of a 64-bit key
			String desKey = "0001001100110100010101110111100110011011101111001101111111110001";
			//Generate all the DES sub-keys
			keys = generateKeys(desKey);
			//Encrypt the original message 
			String cypherText = processInputData(binary, "encrypt");
			//Decrypt the encrypted message
			String decryptedText = processInputData(cypherText, "decrypt");
			System.out.printf("decrypted text: " + decryptedText);
		}
	}
}

