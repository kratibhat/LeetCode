package leetcode.array.buyandsell;
//You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
//
//Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
//
//Note:
//
//You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
//The transaction fee is only charged once for each stock purchase and sale.
//
//
//Example 1:
//
//Input: prices = [1,3,2,8,4,9], fee = 2
//Output: 8
//Explanation: The maximum profit can be achieved by:
//- Buying at prices[0] = 1
//- Selling at prices[3] = 8
//- Buying at prices[4] = 4
//- Selling at prices[5] = 9
//The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
public class BestTimeToBuyAndSellStockWithTransactionFee {
    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println("Maximum Profit: " + maxProfit(prices, fee)); // Output: 8
    }
    public static int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;

        int n = prices.length;
        int effectiveBuyPrice = prices[0]; // Maximum profit when holding a stock
        int profit = 0;          // Maximum profit when not holding a stock

        for (int i = 1; i < n; i++) {
            profit = Math.max(profit,  prices[i]-effectiveBuyPrice-fee); // Either keep holding or buy new stock
            effectiveBuyPrice = Math.min(effectiveBuyPrice, prices[i] - profit); // Either keep cash or sell stock
        }

        return profit; // Maximum profit will be in cash state
    }

}
