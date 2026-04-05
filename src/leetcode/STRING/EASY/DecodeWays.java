package leetcode.STRING.EASY;
//You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:
//
//"1" -> 'A'
//
//"2" -> 'B'
//
//...
//
//"25" -> 'Y'
//
//"26" -> 'Z'
//
//However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").
//
//For example, "11106" can be decoded into:
//
//"AAJF" with the grouping (1, 1, 10, 6)
//"KJF" with the grouping (11, 10, 6)
//The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
//Note: there may be strings that are impossible to decode.
//
//Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.
//
//The test cases are generated so that the answer fits in a 32-bit integer.
//
//
//
//Example 1:
//
//Input: s = "12"
//
//Output: 2
//
//Explanation:
public class DecodeWays {
    public static void main(String[] args) {
        DecodeWays decoder = new DecodeWays();
        String s = "226";
        int ways = decoder.numDecodings(s);
        System.out.println("Number of ways to decode \"" + s + "\": " + ways); // Output: 3
    }
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // An empty string has one way to decode
        dp[1] = 1; // A single character (not '0') has one way to decode

        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));

            // Check for valid one-digit decode
            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] += dp[i - 1];
            }

            // Check for valid two-digit decode
            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
