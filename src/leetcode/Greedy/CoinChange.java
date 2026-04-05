package leetcode.Greedy;

import java.util.Arrays;

//You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
//
//Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
//
//You may assume that you have an infinite number of each kind of coin.
//
//
//
//Example 1:
//
//Input: coins = [1,2,5], amount = 11
//Output: 3
//Explanation: 11 = 5 + 5 + 1
//Example 2:
//
//Input: coins = [2], amount = 3
//Output: -1
//Example 3:
//
//Input: coins = [1], amount = 0
//Output: 0
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        // Base case: if amount is 0, no coins are needed
        if (amount == 0) return 0;

        // Initialize DP array with a value larger than any possible answer
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);

        // Base case for DP: 0 coins needed for amount 0
        dp[0] = 0;

        // Bottom-up DP: iterate through each coin
        for (int coin : coins) {
            // Update all reachable amounts using this coin
            for (int i = coin; i <= amount; i++) {
                // The minimum coins for amount 'i' is the best of:
                // 1. Not using this coin (current dp[i])
                // 2. Using this coin (1 + dp[i - coin])
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        // If dp[amount] wasn't updated, the amount is unreachable
        return dp[amount] > amount ? -1 : dp[amount];
    }
    public static void main(String [] args)
    {
    int [] a={1,2,5};
    int b=11;
    System.out.println(new CoinChange().coinChange(a,b));
    }
}
