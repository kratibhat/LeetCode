package leetcode.array.jumpgame;
//You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:
//
//i + minJump <= j <= min(i + maxJump, s.length - 1), and
//s[j] == '0'.
//Return true if you can reach index s.length - 1 in s, or false otherwise.
//
//
//
//Example 1:
//
//Input: s = "011010", minJump = 2, maxJump = 3
//Output: true
//Explanation:
//In the first step, move from index 0 to index 3.
//In the second step, move from index 3 to index 5.
//Example 2:
//
//Input: s = "01101110", minJump = 2, maxJump = 3
//Output: false
public class JumpGame7 {
    public boolean canReach11ms(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        dp[0] = true;
        int reachable = 0;

        for (int i = 1; i < n; i++) {
            if (i >= minJump) {
                reachable += dp[i - minJump] ? 1 : 0;
            }
            if (i > maxJump) {
                reachable -= dp[i - maxJump - 1] ? 1 : 0;
            }
            dp[i] = reachable > 0 && s.charAt(i) == '0';
        }

        return dp[n - 1];
    }
    public boolean canReach5ms(String s, int minJump, int maxJump) {
        if(s == null || s.isEmpty()) return false;
        int n = s.length();
        if(s.charAt(0) == '1' || s.charAt(n - 1) == '1') return false;


        boolean[] dp = new boolean[n];
        dp[0] = true;

        int start = 0;  //the start index of sliding windows to check
        int end = 0;  //the end index of sliding windows to check
        for(int i = 0; i < n; i++){
            if(!dp[i]){    // quick skip, if current position can't reach
                continue;
            }

            start = Math.max(end + 1, i + minJump);  // skip the position already checked
            end = Math.min(n - 1, i + maxJump);  // avoid overflow

            for(int j = start; j <= end; j++){
                if(s.charAt(j) == '0'){
                    dp[j] = true;
                }
            }
            if(dp[n - 1]) return true;
        }
        return dp[n-1];
    }
    public static void main(String[] args) {
        JumpGame7 solution = new JumpGame7();
        String s = "011010";
        int minJump = 2;
        int maxJump = 3;
        boolean canReachEnd = solution.canReach5ms(s, minJump, maxJump);
        System.out.println("Can reach the end: " + canReachEnd); // Output: true
    }

}
