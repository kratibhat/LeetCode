package leetcode.STRING.EASY.dp;
//Given a string s. In one step you can insert any character at any index of the string.
//
//Return the minimum number of steps to make s palindrome.
//
//A Palindrome String is one that reads the same backward as well as forward.
//
//
//
//Example 1:
//
//Input: s = "zzazz"
//Output: 0
//Explanation: The string "zzazz" is already palindrome we do not need any insertions.
public class MinimumInsertionStepstoMakeaStringPalindrome {
    public int minInsertions(String s) {
        int n = s.length();
        // dp[j] will represent the min insertions for a substring ending at j
        int[] dp = new int[n];

        // We iterate i from right to left (bottom to top in the 2D table)
        for (int i = n - 2; i >= 0; i--) {
            int prev = 0; // This stores the "diagonal" value: dp[i+1][j-1]
            for (int j = i + 1; j < n; j++) {
                int temp = dp[j]; // Store dp[i+1][j] before it gets overwritten

                if (s.charAt(i) == s.charAt(j)) {
                    // If chars match, cost is same as the inner substring (diagonal)
                    dp[j] = prev;
                } else {
                    // Otherwise, 1 + min of (bottom value, left value)
                    // dp[j] is currently the value from row i+1 (bottom)
                    // dp[j-1] is the value from the current row i (left)
                    dp[j] = Math.min(dp[j], dp[j-1]) + 1;
                }

                prev = temp; // Update diagonal for the next iteration of j
            }
        }

        return dp[n - 1];
    }
    public int minInsertions1 (String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        return dp[0][n - 1];
    }
    public static void main(String[] args) {
        MinimumInsertionStepstoMakeaStringPalindrome solution = new MinimumInsertionStepstoMakeaStringPalindrome();
        String s = "abc";
        int result = solution.minInsertions(s);
        System.out.println("Minimum insertions to make the string a palindrome: " + result); // Output: 2
    }
}
////Whenever a problem asks for the minimum or maximum of something (insertions, deletions, length) regarding subsequences
////or substrings, your first thought should be Dynamic Programming.
///
/// Specifically, for palindromes, the signal is:
///
/// Optimal Substructure: Can I solve the problem for a big string if I already know
////the answer for the smaller string inside it?
///
/// Answer: Yes. To solve for s[0...n-1], I just need to know the answer for s[1...n-2] (the middle part).