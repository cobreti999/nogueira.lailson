/*
 * 
 * Fibonacci
 * author: Lailson Lima Nogueira
 * COMP 460 Algorithms and Complexity
 * Ms in Computer Science
 * Loyola University Chicago
 * Spring 2015
 * 
 */

import java.math.BigInteger;
import java.util.Scanner;



public class Main {
	
	/*
	 * 
	 * Iterative naive implementation of the nth term of the fibonacci sequence
	 * 
	 */
	
	public static BigInteger fibonacci(int n){
		BigInteger x = new BigInteger("0");
		BigInteger y = new BigInteger("1");
		BigInteger z = new BigInteger("1");
		for (int i = 0; i<n ; i++) {
			x = y;
			y = z;
			//The following term is the sum of the previous two
			z = x.add(y);
		}
		return x;
	}
	
	
	/*
	 * 
	 * Gives the nth term of the fibonacci sequence using matrix calculations
	 * 
	 */
	
	public static BigInteger fibonacciMatrix(int n) {
		//The first term
        if (n == 0) {
            return new BigInteger("0");
        }
        
        BigInteger base[][] = new BigInteger[2][2];
		base[0][0] = new BigInteger("0");
		base[0][1] = new BigInteger("1");
		base[1][0] = new BigInteger("1");
		base[1][1] = new BigInteger("1");
		BigInteger result[][] = new BigInteger[2][2];
		result[0][0] = new BigInteger("0");
		result[0][1] = new BigInteger("1");
		result[1][0] = new BigInteger("1");
		result[1][1] = new BigInteger("1");
		
		//knowing that baseˆpower is the same as (base^(power/2))ˆ2 we 
		//can reduce the number of power calculations resulting in O(log n) time
		
		//we start by n (power) and keep dividing n by 2  
        while (n > 0) {
            if (n%2 == 1){ 
            	multiplyMatrices(result,base);
            }
            //square the base
            multiplyMatrices(base,base);
            n = n/2;
        }
        //The first term of the result matrix is the nth term of the sequence
        return result[0][0];
    }
	
	/*
	 * 
	 * auxiliar method to calculate the multiplication of two matrices
	 * 
	 */
	
		
	private static void multiplyMatrices(BigInteger m1[][], BigInteger m2[][]){
		//Multiply each line of m1 with each column of m2
		BigInteger x = (m1[0][0].multiply(m2[0][0])).add(m1[0][1].multiply(m2[1][0]));
		BigInteger y = (m1[0][0].multiply(m2[0][1])).add(m1[0][1].multiply(m2[1][1]));
		BigInteger z = (m1[1][0].multiply(m2[0][0])).add(m1[1][1].multiply(m2[1][0]));
		BigInteger w = (m1[1][0].multiply(m2[0][1])).add(m1[1][1].multiply(m2[1][1]));
		
		//The result is stored at m1
		m1[0][0] = x;
		m1[0][1] = y;
		m1[1][0] = z;
		m1[1][1] = w;
	}
	
	
	public static void main(String args[]){
		System.out.println("Fibonacci calculator!");
		System.out.println("Choose a number:");
		Scanner keyboard = new Scanner(System.in);
		int number = keyboard.nextInt();
		System.out.println("Calculating the " + number + "th term of the fibonacci sequence"
				+ " using the matrix method");
		long t1 = System.currentTimeMillis();
		BigInteger result1 = fibonacciMatrix(number);
		long t2 = System.currentTimeMillis();
		System.out.println("Result: " + result1);
		System.out.println("Time elapsed: " + (t2-t1) + " miliseconds");
		System.out.println();
		System.out.println("Calculating the " + number + "th term of the fibonacci sequence"
				+ " using the naive implementation");
		long t3 = System.currentTimeMillis();
		BigInteger result2 = fibonacci(number);
		long t4 = System.currentTimeMillis();
		System.out.println("Result: " + result2);
		System.out.println("Time elapsed: " + (t4-t3) + " miliseconds");
	}
}
