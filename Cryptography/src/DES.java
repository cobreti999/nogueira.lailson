import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class DES {
	public static ArrayList<String> keys = new ArrayList<String>();
	public final static String Sbox[][][] = {
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
	
	public static String convertStringtoBinary(String s){
		byte[] bytes = s.getBytes();
		String binary = "";
		  for (byte b : bytes)
		  {
		     int val = b;
		     for (int i = 0; i < 8; i++)
		     {
		    	binary += ((val&128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		  }
		  return binary;
	}
	
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
			System.out.println(table);
			
			int bits = 8;
			for (int i =0; i < binary.length(); i += bits){
				int value = Integer.parseInt(binary.substring(i, i + bits), 2);
				//System.out.println("integer value: " + value);
				System.out.println("binary value: " + binary.substring(i, i + bits));
				//System.out.println("teste: " + (char)value);
				System.out.println(binary.substring(i, i + bits) + " eq: " + table.charAt(value));
				result += table.charAt(value);
			}
			return result;
	}
	
	public String readMsgFromTextFile(File file){
		String binary = "";
		try {
			Scanner scanner = new Scanner(file);
			String lastString = "";
			while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();		        
		        lastString += line;
		    }
		    binary = convertStringtoBinary(lastString);
		    System.out.println(binary.length());
		    scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			//Adicionar depois codigo para criar o arquivo
		}
		return binary;
	}
	
	public ArrayList<String> breakMsgInBlocks(String msg, int blockSize){
		//<ArrayList<String> blocks keeps all the blocks that are going to be encrypted
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
		for (String string : blocks) {
			System.out.println(string);
		}
		System.out.println(blocks.size());
		return blocks;
	}
	
	public ArrayList<String> initialPermutation(ArrayList<String> blocks){
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
		/*System.out.println("Imprimindo strings permutadas: ");
		for (String string : permutedList) {
			System.out.println(string);
		}*/
		return permutedList;
	}
	
	public ArrayList<String> generateKeys(ArrayList<String> blocks, String desKey){
		ArrayList<String> keys = new ArrayList<String>();
		String c[] = new String[17];
		String d[] = new String[17];
		//(OBSSSSSS: VERIFICAR MAIS UMA VEZ SE ESSA PERMUTACAO TA CORRETA)
		//Search through every block
		int initValue = 56, reset = 56;
		String temp = "";
		System.out.println();
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
		
		
		//O cara da net errou o exemplo. Vamos usar esses valores que sao os valores q o vacilao ta usando
		//c[0] = "0111010100011001100010001000";
		//d[0] = "0100000101100101000111011111";
		
		System.out.println("c: " + c[0]);
		System.out.println("d: " + d[0]);
		//Now that we have c[0] and d[0], we must calculate all the k[16] keys.
		for (int i =1; i <= 16; i++){
			c[i] = shiftLeft(c[i-1], i);
			System.out.println("c[" + i + "]: " + c[i]);
			d[i] = shiftLeft(d[i-1], i);
			System.out.println("d[" + i + "]: " + d[i]);
			keys.add(permutedChoice2(c[i-1], d[i-1]));
		}
		return keys;
	}
	
	private static String shiftLeft(String value, int round){
		String shiftedString = "";
		if (round==1 || round==2 || round==9 || round==16){
			//Perform 1 left shift
			StringBuffer s = new StringBuffer(value);
			char c = s.charAt(0);
			s.append(c);
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
	
	public static String exclusiveOr(String s1, String s2){
		//return Long.toBinaryString((Long.parseLong(s1, 2) ^ Long.parseLong(s2, 2)));
		System.out.println("s1 size: " + s1.length() + " s1: " + s1);
		System.out.println("s2 size: " + s2.length() + " s2: " + s2);
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
		System.out.println("s3 size: " + s3.length() + " s3: " + s3);
		return (s3);
	}
	
	public void processInputData(ArrayList<String> permutedList){
		ArrayList<String> outputBlocks = new ArrayList<String>();
		ArrayList<String> outputBlocksAscii = new ArrayList<String>();
		String finalBinaryEncrypted = "";
		String finalAsciiEncrypted = "";
		for (String block : permutedList) {
			//ArrayList<String> sboxResultArray = new ArrayList<String>();
			String[] firstHalf = new String[16];
			String[] secondHalf = new String[16];
			firstHalf[0] = block.substring(0, 32);
			secondHalf[0] = block.substring(32, 64);
			String expandedR = "";
			System.out.println("firstHalf: " + firstHalf[0]);
			System.out.println("secondHalf: " + secondHalf[0]);
			//Process the input data for 16 rounds
			for (int i =0 ; i < 16; i ++){
				String sboxResult ="";
				//Expanding secondHalf according to e-bit selection table
				//O EXEMPLO DO VACILAO DA OUTRO VALOR AQUI
				System.out.println("i: "+ i + "secondHalf[i] size: " + secondHalf[i].length());
				expandedR = "" + secondHalf[i].charAt(31) + secondHalf[i].substring(0, 5) 
						+ secondHalf[i].substring(3, 9) + secondHalf[i].substring(7, 13)
						+ secondHalf[i].substring(11, 17) + secondHalf[i].substring(15, 21)
						+ secondHalf[i].substring(19, 25) + secondHalf[i].substring(23, 29)
						+ secondHalf[i].substring(27, 32) + secondHalf[i].charAt(0);
				System.out.println("expandedR[0]: " + expandedR);
				//Exclusive-OR E(secondHalf[i-1]) with K[i]
				String exclusiveResult = exclusiveOr(expandedR, keys.get(i));
				System.out.println("exclusiveResult: " + exclusiveResult);
				ArrayList<String> string6bitsArray = breakMsgInBlocks(exclusiveResult, 6);
				int box = 0;
				for (String string6bits : string6bitsArray) {
					//Calculate which row of the s-box
					String stringRow = "" + string6bits.charAt(0)+string6bits.charAt(5);
					//Convert the binary number to base 10
					int row = Integer.parseInt(stringRow, 2);
					//Divide the result in blocks of 6 bits
					//Calculate which column of the s-box
					String stringColumn = "" + string6bits.substring(1, 5);
					int column = Integer.parseInt(stringColumn, 2);
					sboxResult += convertStringTo4Bits(Sbox[box++][row][column]);
					System.out.println("size sboxResult: " + sboxResult.length()  + " sboxResult: " + sboxResult);
				}
				//Perform FinalPermutation
				String permutedString = permutationAfterSbox(sboxResult);
				System.out.println("permutedString size: " + permutedString.length());
				//Exclusive-OR firstHalf with permutedString
				String xorResult = exclusiveOr(firstHalf[i], permutedString);
				System.out.println("xorResult: " + xorResult + " xorResult size: " + xorResult.length());
				if (i < 15){
					//VERIFICAR SE É ISSO MESMO
					secondHalf[i+1] = xorResult;
					firstHalf[i+1] = secondHalf[i];
				}
			}
			//Perform FinalPermutation on the string formed by the following 
			//concatenation "secondhalf[15]firsthalf[15]"
			String lastRoundResult = secondHalf[15]+firstHalf[15];
			System.out.println("lastRoundResultLength: " + lastRoundResult.length() + " lastRoundResult: " + lastRoundResult);
			String finalString = finalPermutation(lastRoundResult);
			System.out.println(finalString.length() + " " + finalString);
			String encryptedString = convertBinaryToString(finalString);
			System.out.println("encryptedString size: " + encryptedString.length());
			System.out.println("encryptedString: " + encryptedString);
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
	}
	
	public static String finalPermutation(String input){
		String output = "";
		int index = 0;
		//input = "0000010000110100110011000000100101100000101110110000001010001011";
		for (int i =0; i < 8; i++){
			output = output + input.charAt(39-index)+input.charAt(7-index)+input.charAt(47-index)+input.charAt(15-index)
					+ input.charAt(55-index)+input.charAt(23-index)+input.charAt(63-index)+input.charAt(31-index);
			index++;
		}
		return output;
	}
	
	//testada ok
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
	
	//public static String 
	
	public static void main(String args[]){
		DES d = new DES();
		long n1 = System.currentTimeMillis();
		File file = new File("test-msg.txt");
		//File file = new File("HelloWorld.txt");
		String binary = d.readMsgFromTextFile(file);
		//MUDA O BINARY PRA FICAR IGUAL AO EXEMPLO
		//binary = "0101011011101001100111101010110011011110010111111111010010110001";
		ArrayList<String> blocks = d.breakMsgInBlocks(binary, 64);
		ArrayList<String> permutedList = d.initialPermutation(blocks);
		//desKey da animacao. Tudo indica que existem erros no algoritmo de key schedule
		//String desKey = "0011010000101101101101011010100000011101110110111001000000000100";
		
		
		//deskey do exemplo da net. Tudo indica que ha um erro na permutedchoice1, o key schedule esta correto
		String desKey = "1101111000010000100111000101100011101000101001001010011000110000";
		keys = d.generateKeys(blocks, desKey);
		
		d.processInputData(permutedList);
		long n2 = System.currentTimeMillis();
		System.out.println("tempo: " + (n2-n1));

	}
}

