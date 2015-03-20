public class Coins {

	public static void main(String[] args) {
		int coins[] = {1,5,7,13};
		System.out.println(change(coins, 500));
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
}
