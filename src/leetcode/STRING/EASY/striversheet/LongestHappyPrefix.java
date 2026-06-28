package leetcode.STRING.EASY.striversheet;
//A string is called a happy prefix if is a non-empty prefix which is also a
// suffix (excluding itself).
//
//Given a string s, return the longest happy prefix of s.
// Return an empty string "" if no such prefix exists.
//
//
//
//Example 1:
//
//Input: s = "level"
//Output: "l"
//Explanation: s contains 4 prefix excluding itself ("l", "le", "lev", "leve"),
// and suffix ("l", "el", "vel", "evel"). The largest prefix which is also suffix is given by "l".
//Example 2:
//
//Input: s = "ababab"
//Output: "abab"
//Explanation: "abab" is the largest prefix which is also suffix.
// They can overlap in the original string.
public class LongestHappyPrefix {
    public static String longestPrefix(String s) {
        int n = s.length();
        int[] lps = new int[n];

        int len = 0; // Length of the previous longest happy prefix
        int i = 1;   // Loops through the string from left to right

        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                // Mismatch found
                if (len != 0) {
                    // Fall back to the previous longest matching prefix length
                    len = lps[len - 1];
                } else {
                    // No matching prefix possible, move forward
                    lps[i] = 0;
                    i++;
                }
            }
        }

        // The last value in the lps array gives the length of the longest happy prefix
        int longestHappyPrefixLength = lps[n - 1];

        return s.substring(0, longestHappyPrefixLength);
    }
    public static void main(String []args)
    {
        String s = "level";
        System.out.println(longestPrefix(s));


    }
}
