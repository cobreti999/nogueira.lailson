import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;


public class ChangeProblem {
	
	
	public static void menu(){
		Scanner keyboard = new Scanner(System.in);
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
	
	/*
	 * 
	 * The greedy algorithm always provides a solution but doesn’t guarantee
the smallest number of coins used. The greedy algorithm takes O(nk ) for any
kind of coin set denomination, where k is the number of different coins in a
particular set. 
	 * 
	 */
	
	public static int runAlgorithm(int[] coins, int coinsAllowed){

		int num;
		boolean changeIsPossible = true;
		int amount=0;
		int value = amount;
		int sum;
		while(changeIsPossible){
			value++;
			sum=0;
			amount = value + amount;
			for (int i =0; i < coins.length; i++){
				//System.out.println("coins[" + i + "]: " + coins[i]);
				//System.out.println("amount: " + amount);
				if (coins[i] <= amount){ 
					num = amount/coins[i];
					sum = sum + num;
					System.out.println("value: " + value + " " + num + " $" + coins[i]);
					amount = amount - (num*coins[i]);
				}
				
				if (sum > coinsAllowed){					
					changeIsPossible = false;
				}
			}
			System.out.println("# moedas usadas: " + sum);
			System.out.println();
			if (amount != 0){
				changeIsPossible = false;
				// Arriving here means it's impossible to make change
				// using this greedy algorithm, this amount of change,
				// and this set of coins.
				System.out.print("Cannot make change; ");
				System.out.println("cents remaining: " + amount);
			}
		}
		return value-1;
	}
	
	public static void runAlgorithm2(int[] coins, int coinsAllowed){
		int num;
		int amount=56;
		
			for (int i =0; i < coins.length; i++){
				if (coins[i] <= amount){ 
					num = amount/coins[i];
					System.out.println(num + " $" + coins[i]);
					amount = amount - (num*coins[i]);
				}
			}
			if (amount != 0){
				//changeIsPossible = false;
				// Arriving here means it's impossible to make change
				// using this greedy algorithm, this amount of change,
				// and this set of coins.
				System.out.print("Cannot make change; ");
				System.out.println("cents remaining: " + amount);
			}

	}
	
	static int makeChangeGreedyStyle(int amount, int[] coins) {

		// Check if there is no more change to make.
		if (amount == 0) {
			System.out.println("");
			return 0;
		}
		// Loop over the change in order of greatest to smallest.
		for (int i = coins.length; i > 0; i--) {
			int coin = coins[i - 1];
			// If the next largest coin is found, print out its value.
			if (amount >= coin) {
				System.out.print(coin + " ");
				return 1+makeChangeGreedyStyle(amount - coin, coins);
			}
		}
		// Arriving here means it's impossible to make change
		// using this greedy algorithm, this amount of change,
		// and this set of coins.
		System.out.print("Cannot make change; ");
		System.out.println("cents remaining: " + amount);
		return 0;
	}
	
	public static int change(int[] coins, int coinsAllowed) {
		boolean gapNotFound = true;
		int value=0;
	    while (gapNotFound){
	    	value++;
	    	int minCoins[] = new int[value+1];
	    	for (int i = 1; i <= value; i++) 
	    		minCoins[i] = Integer.MAX_VALUE-1;
	    	for (int i = 1; i <= value; i++) {
	    		for (int coin : coins) {
	    			if (i >= coin) 
	    				minCoins[i] = Math.min(minCoins[i], 1+minCoins[i-coin]);
	    		}
	    	}
	    	//System.out.println("value: " + value + ". # min moedas usadas " + minCoins[value]);
	    	if (minCoins[value] > coinsAllowed)
	    		gapNotFound = false;
	    }
	    return value-1;
	}
	
	public static void main(String args[]){
		menu();
		//int denominations[] = {2,5,20};
		//makeChangeGreedyStyle(56, denominations);
		int den[] = {20,5,2};
		int amount = 12;
		//runAlgorithm2(den, amount);
	}
}
