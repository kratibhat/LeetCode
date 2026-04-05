package leetcode.STRING.EASY.slidingwind;

import java.util.HashMap;
import java.util.Map;
//3. Longest Substring Without Repeating Characters
//Medium
//Topics
//premium lock icon
//Companies
//Hint
//Given a string s, find the length of the longest substring without duplicate characters.
//
//
//
//Example 1:
//
//Input: s = "abcabcbb"
//Output: 3
//Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
//Example 2:
//
//Input: s = "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.
//Example 3:
//
//Input: s = "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3.
//Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
public class LongestSubstringWithoutRepeatingCharecters {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int left = 0;
       Map<Character, Integer> charIndexMap = new HashMap<>();

        for (int right = 0; right < n; right++) {
            char currentChar = s.charAt(right);
            if (charIndexMap.containsKey(currentChar)) {
                left = Math.max(charIndexMap.get(currentChar) + 1, left);
            }
            charIndexMap.put(currentChar, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharecters solution = new LongestSubstringWithoutRepeatingCharecters();
        String s = "abcabcbb";
        int length = solution.lengthOfLongestSubstring(s);
        System.out.println("Length of Longest Substring Without Repeating Characters: " + length); // Output: 3
    }
}
