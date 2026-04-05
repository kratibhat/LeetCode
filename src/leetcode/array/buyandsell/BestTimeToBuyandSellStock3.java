package leetcode.array.buyandsell;
//You are given an array prices where prices[i] is the price of a given stock on the ith day.
//
//Find the maximum profit you can achieve. You may complete at most two transactions.
//
//Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
//
//
//
//Example 1:
//
//Input: prices = [3,3,5,0,0,3,1,4]
//Output: 6
//Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
//Example 2:
//
//Input: prices = [1,2,3,4,5]
//Output: 4
//Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
//Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
public class BestTimeToBuyandSellStock3 {
    public int maxProfit(int[] prices) {
        int firstBuy = Integer.MAX_VALUE, firstSell = 0;
        int secondBuy = Integer.MAX_VALUE, secondSell = 0;

        for (int price : prices) {
            firstBuy = Math.min(firstBuy, price);
            firstSell = Math.max(firstSell,  price- firstBuy);
            secondBuy = Math.min(secondBuy, price- firstSell);
            secondSell = Math.max(secondSell, price - secondBuy);
        }

        return secondSell;
    }
//method 2
    public int maxProfit1(int[] prices) {
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;

        for (int price : prices) {
            firstBuy = Math.max(firstBuy, -price);
            firstSell = Math.max(firstSell, firstBuy + price);
            secondBuy = Math.max(secondBuy, firstSell - price);
            secondSell = Math.max(secondSell, secondBuy + price);
        }

        return secondSell;


}

    public static void main(String[] args) {
        BestTimeToBuyandSellStock3 solution = new BestTimeToBuyandSellStock3();
        int[] prices = {3,3,5,0,0,3,1,4};
        int maxProfit = solution.maxProfit(prices);
        System.out.println("Maximum Profit: " + maxProfit); // Output: 6
    }
    // The idea is to track the minimum price for the first and second buys
    // and the maximum profit for the first and second sells.

}
