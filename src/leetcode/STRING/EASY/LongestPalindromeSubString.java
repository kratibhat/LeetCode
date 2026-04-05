package leetcode.STRING.EASY;
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
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            if (expandAroundCenter(s, i, i).length() > result.length()) {
                result = expandAroundCenter(s, i, i);
            }
        }
        return result;
    }

        private String expandAroundCenter (String s,int left, int right){
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            return s.substring(left + 1, right); // Length of the palindrome
        }
        public static void main(String[] args) {
            LongestPalindromeSubString lp = new LongestPalindromeSubString();
            String input = "bababab";
            String result = lp.longestPalindrome(input);
            System.out.println("Longest Palindromic Substring of " + input + " is: " + result);
        }
    }

