/*
 * 
 * COMP 460 - Algorithms and Complexity
 * Midterm - Question 1 - Dynamic Programming Solution to Change Problem
 * Author: Lailson Lima Nogueira
 * 
 */

import java.util.ArrayList;
import java.util.Scanner;

public class ChangeProblem {
	
	private static Scanner keyboard;

	public static void menu(){
		keyboard = new Scanner(System.in);
		int coin;
		ArrayList<Integer> denominationsList = new ArrayList<Integer>();
		do{
			System.out.println("Type one denomination coin (integer > 0) or -1 to finish.");
			coin = keyboard.nextInt();
			if (coin != -1)
				denominationsList.add(coin);
		}while(coin != -1);
		System.out.println("Type the total number of coins allowed (integer > 0.)");
		int coinsAllowed = keyboard.nextInt();
		int denominations[] = new int[denominationsList.size()];
		for (int i = 0; i < denominations.length; i++) {
			denominations[i] = denominationsList.get(i);
		}
		int maxCoverage = change(denominations, coinsAllowed);
		System.out.println("maxCoverage = " + maxCoverage);
	}
	
	public static int change(int[] coins, int coinsAllowed) {
		boolean gapNotFound = true;
		int value=0;
		//Keeps calculating the number of coins until founds a gap
	    while (gapNotFound){
	    	//Starts with value = 1 and keeps increasing until coinsAllowed is reached
	    	value++;
	    	//minCoins is the table where we store the number of coins used
	    	//We initialize its value with a large integer value
	    	int minCoins[] = new int[value+1];
	    	for (int i = 1; i <= value; i++) 
	    		minCoins[i] = Integer.MAX_VALUE-1;
	    	//populates minCoins with the min value of coins used in for each value 
	    	for (int i = 1; i <= value; i++) {
	    		for (int coin : coins) {
	    			if (i >= coin) 
	    				minCoins[i] = Math.min(minCoins[i], 1+minCoins[i-coin]);
	    		}
	    	}
	    	//We stop increasing the value when we found a gap
	    	if (minCoins[value] > coinsAllowed)
	    		gapNotFound = false;
	    }
	    return value-1;
	}
	
	public static void main(String args[]){
		menu();
	}
}
