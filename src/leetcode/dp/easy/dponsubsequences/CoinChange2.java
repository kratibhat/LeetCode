package leetcode.dp.easy.dponsubsequences;

public class CoinChange2 {
    public int change(int amount, int[] coins) {
        // dp[i] will store the number of ways to make amount i
        int[] dp = new int[amount + 1];

        // Base case: 1 way to make amount 0
        dp[0] = 1;

        // Loop through each coin first to prevent duplicate permutations
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }
    public static void main(String [] args){
        int []coins={1,2,5};
        int amount=5;
        CoinChange2 cc = new CoinChange2();
        int res=cc.change(amount,coins);
        System.out.println(res);
    }
}
