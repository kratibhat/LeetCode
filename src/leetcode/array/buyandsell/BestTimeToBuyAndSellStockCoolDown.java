package leetcode.array.buyandsell;
//You are given an array prices where prices[i] is the price of a given stock on the ith day.
//
//Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
//
//After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
//Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
//
//
//
//Example 1:
//
//Input: prices = [1,2,3,0,2]
//Output: 3
//Explanation: transactions = [buy, sell, cooldown, buy, sell]


//solution

//u can either be in 3 states
//1. hold - holding a stock
//2. sold - just sold a stock
//3. rest - in cooldown or doing nothing
//in held state, you can either continue holding or buy a stock from rest state
//in sold state, you can only come from held state by selling the stock
//in rest state, you can either continue resting or come from sold state
//the transitions can be represented as:
//hold[i] = max(hold[i-1], rest[i-1] - prices[i])
//sold[i] = hold[i-1] + prices[i]
//rest[i] = max(rest[i-1], sold[i-1])
//the base cases are:
//hold[0] = -prices[0]
//sold[0] = 0
//rest[0] = 0
//the answer will be max(sold[n-1], rest[n-1]) because we can't end with holding a stock
//here's the implementation:
public class BestTimeToBuyAndSellStockCoolDown {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int n = prices.length;
        int hold = -prices[0]; //here -prices[0] because we are buying the stock
        int sold = 0;
        int rest = 0;

        for (int i = 1; i < n; i++) {
            int prevHold = hold;
            hold = Math.max(hold, rest - prices[i]);
            rest = Math.max(rest, sold); //this is max of rest and previous sold so dont mess order
            sold = prevHold + prices[i];//prevhold not needed u can use hold but soln is more optimized this way
        }

        return Math.max(sold, rest);
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockCoolDown solution = new BestTimeToBuyAndSellStockCoolDown();
        int[] prices = {1, 2, 3, 0, 2};
        int maxProfit = solution.maxProfit(prices);
        System.out.println("Maximum Profit: " + maxProfit); // Output: 3
    }
}
