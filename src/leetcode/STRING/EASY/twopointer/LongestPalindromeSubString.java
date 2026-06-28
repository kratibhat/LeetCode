package leetcode.STRING.EASY.twopointer;
//consider ABABA here consider the center A traverse
// through left and right to find the longest palindrome
//Is the palindrome at the current center longer than the best one we've found so far?-->if loop
//
//If yes, update:
//Given a string s, return the longest palindromic substring in s.
//
//
//
//Example 1:
//
//Input: s = "babad"
//Output: "bab"
//Explanation: "aba" is also a valid answer.
//Example 2:
//
//Input: s = "cbbd"
//Output: "bb
public class LongestPalindromeSubString {
    private int start = 0;
    private int maxLen = 1;

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        for (int i = 0; i < s.length(); i++) {
            // Odd length palindrome
            expand(s, i, i);

            // Even length palindrome
            expand(s, i, i + 1);
        }

        return s.substring(start, start + maxLen);
    }

    private void expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() &&
                s.charAt(left) == s.charAt(right)) {

            if (right - left + 1 > maxLen) {
                maxLen = right - left + 1;
                start = left;
            }

            left--;
            right++;
        }
    }
        public static void main(String[] args) {
            LongestPalindromeSubString lp = new LongestPalindromeSubString();
            String input = "bababab";
            String result = lp.longestPalindrome(input);
            System.out.println("Longest Palindromic Substring of " + input + " is: " + result);
        }
    }

