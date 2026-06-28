package leetcode.STRING.EASY.slidingwind;
//Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
//
//In other words, return true if one of s1's permutations is the substring of s2.
//
//
//
//Example 1:
//
//Input: s1 = "ab", s2 = "eidbaooo"
//Output: true
//Explanation: s2 contains one permutation of s1 ("ba").
//Example 2:
//
//Input: s1 = "ab", s2 = "eidboaoo"
//Output: false
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        // If s1 is longer than s2, s2 cannot possibly contain a permutation of s1
        if (len1 > len2) {
            return false;
        }

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        // Step 1: Initialize frequencies for s1 and the first window of s2
        for (int i = 0; i < len1; i++) {
            s1Count[s1.charAt(i) - 'a']++;
            s2Count[s2.charAt(i) - 'a']++;
        }

        // If the first window matches, we are done
        if (matches(s1Count, s2Count)) {
            return true;
        }

        // Step 2: Slide the window across the rest of s2
        for (int i = len1; i < len2; i++) {
            // Add the character entering the window on the right
            s2Count[s2.charAt(i) - 'a']++;

            // Remove the character leaving the window on the left
            s2Count[s2.charAt(i - len1) - 'a']--;

            // Check if the current window matches s1's profile
            if (matches(s1Count, s2Count)) {
                return true;
            }
        }

        return false;
    }

    // Helper method to compare two frequency arrays of size 26
    private boolean matches(int[] arr1, int[] arr2) {
        for (int i = 0; i < 26; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
    public static void main(String []args){
        PermutationInString p =new PermutationInString();
        String s1="ab";
        String s2="eidbaooo";
        System.out.println(p.checkInclusion(s1,s2));
    }

}
