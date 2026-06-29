package leetcode.dp.easy.oneD;
//You are climbing a staircase. It takes n steps to reach the top.
//
//Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
//
//
//
//Example 1:
//
//Input: n = 2
//Output: 2
//Explanation: There are two ways to climb to the top.
//1. 1 step + 1 step
//2. 2 steps
//count of (n-1)th and (n-2)th stairs
public class ClimbingStairs {
    public static void main(String[] args){
        int n = 5;
        System.out.println("Number of ways to climb " + n + " stairs: " + climbStairs(n));
    }
    public static int climbStairs(int n) {
        if (n == 1) return 1;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }


}
//dfrnt approach

//class GfG {
//    static int countWays(int n) {
//
//        // Base cases: If there are 0 or 1 stairs,
//        // there is only one way to reach the top.
//        if (n == 0 || n == 1)
//            return 1;
//
//        return countWays(n - 1) + countWays(n - 2);
//    }
//
//    public static void main(String[] args) {
//        int n = 4;
//        System.out.println(countWays(n));
//    }
//}