package leetcode.array.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//Given two strings s and p, return an array of all the start indices of p's anagrams in s.
// You may return the answer in any order.
//
//
//
//Example 1:
//
//Input: s = "cbaebabacd", p = "abc"
//Output: [0,6]
//Explanation:
//The substring with start index = 0 is "cba", which is an anagram of "abc".
//The substring with start index = 6 is "bac", which is an anagram of "abc".
public class FindAnagramsinAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        int pLength = p.length();

        for (int i = 0; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++;

            if (i >= pLength) {
                sCount[s.charAt(i - pLength) - 'a']--;
            }

            if (Arrays.equals(sCount, pCount)) {
                result.add(i - pLength + 1);
            }
        }

        return result;
    }
}
