package leetcode.array.next.MontonicSTack;

import java.util.Stack;
//You are given an integer array prices where prices[i] is the price of the ith item in a shop.
//
//There is a special discount for items in the shop. If you buy the ith item, then you will receive a discount equivalent to prices[j] where j is the minimum index such that j > i and prices[j] <= prices[i]. Otherwise, you will not receive any discount at all.
//
//Return an integer array answer where answer[i] is the final price you will pay for the ith item of the shop, considering the special discount.
public class FinalPricesWithASpecialDiscountInAShop {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek();
            stack.push(prices[i]);
        }

        return result;
    }
    public int[] finalPricesOptimal(int[] prices) {
        /// /0ms
        int[] result = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            result[i] = min(prices, i);
        }
        return result;
    }

    int min(int[] ps, int i) {
        for (int j = i + 1; j < ps.length; j++) {
            if (ps[j] <= ps[i]) {
                return ps[i] - ps[j];
            }
        }
        return ps[i];
    }
    public static void main(String[] args) {
        FinalPricesWithASpecialDiscountInAShop solution = new FinalPricesWithASpecialDiscountInAShop();

        int[] prices = {8, 4, 6, 2, 3};
        int[] result = solution.finalPrices(prices);
        System.out.print("Final Prices after Discount: ");
        for (int price : result) {
            System.out.print(price + " ");
        }
    }
}
