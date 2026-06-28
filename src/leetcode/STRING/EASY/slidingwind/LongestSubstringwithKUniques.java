package leetcode.STRING.EASY.slidingwind;
//You are given a string s consisting only lowercase alphabets and an integer k.
// Your task is to find the length of the longest substring that contains exactly k
// distinct characters.
//
//Note : If no such substring exists, return -1.
//
//Examples:
//
//Input: s = "aabacbebebe", k = 3
//Output: 7
//Explanation: The longest substring with exactly 3 distinct characters is "cbebebe",
// which includes 'c', 'b', and 'e'.
//Input: s = "aaaa", k = 2
//Output: -1
//Explanation: There's no substring with 2 distinct characters.
public class LongestSubstringwithKUniques {
    public int longestKSubstr(String s, int k) {
        int n = s.length();
        int maxLen = -1;

        int left = 0;
        int distinctCount = 0;
        int[] count = new int[26];

        for (int right = 0; right < n; right++) {
            // Add the character entering the window on the right
            char rightChar = s.charAt(right);
            if (count[rightChar - 'a'] == 0) {
                distinctCount++; // New unique character discovered
            }
            count[rightChar - 'a']++;

            // Shrink the window from the left if unique characters exceed k
            while (distinctCount > k) {
                char leftChar = s.charAt(left);
                count[leftChar - 'a']--;
                if (count[leftChar - 'a'] == 0) {
                    distinctCount--; // An entire character type left the window
                }
                left++;
            }

            // If our window matches the exact constraint, record its length
            if (distinctCount == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen;
    }
    public static void main(String [] args){
        String s="aabacbebebe";
        LongestSubstringwithKUniques l=new LongestSubstringwithKUniques();
        int k=3;
        System.out.println(l.longestKSubstr(s,k));
    }
}
