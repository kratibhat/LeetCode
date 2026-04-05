package leetcode.array.buyandsell;
//You are given an integer k and an array prices where prices[i] is the price of a given stock on the ith day.
//You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
//
//Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.
//
//Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
//
//
//
//Example 1:
//
//Input: k = 2, prices = [2,4,1]
//Output: 2
//Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
//Example 2:
//
//Input: k = 2, prices = [3,2,6,5,0,3]
//Output: 7
//Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//
public class BestTimeToBuyAndSellStock4 {
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock4 solution = new BestTimeToBuyAndSellStock4();
        int k = 2;
        int[] prices = {3, 2, 6, 5, 0, 3};
        int maxProfit = solution.maxProfit(k, prices);
        System.out.println("Maximum Profit: " + maxProfit); // Output: 7
    }

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k <= 0) return 0;

        int n = prices.length;
         if(n<2)return 0;

        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int effectiveBuyPrice = prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] - effectiveBuyPrice);
                effectiveBuyPrice = Math.min(effectiveBuyPrice, prices[j] - dp[i -1][j]);

            }
        }

        return dp[k][n - 1];
    }
}
