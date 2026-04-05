package leetcode.SlidngWindow;
//You are given a string 'str' and an integer ‘K’. Your task is to find the length of the largest substring with at most ‘K’ distinct characters.
//
//For example:
//
//You are given ‘str’ = ‘abbbbbbc’ and ‘K’ = 2, then the substrings that can be formed are [‘abbbbbb’, ‘bbbbbbc’]. Hence the answer is 7.
public class LongestSubstringwithAtMostKUniqueCharecters {
    public static int kDistinctChars(int k, String str) {
        int[] freq = new int[128]; // To store frequency of characters
        int left = 0, maxLen = 0, distinct = 0;

        for (int right = 0; right < str.length(); right++) {
            char rightChar = str.charAt(right);

            // If this is a new character, increment distinct count
            if (freq[rightChar] == 0) {
                distinct++;
            }
            freq[rightChar]++;

            // Shrink window if distinct characters > k
            while (distinct > k) {
                char leftChar = str.charAt(left);
                freq[leftChar]--;
                if (freq[leftChar] == 0) {
                    distinct--;
                }
                left++;
            }

            // Update maximum length found
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
    public static void main(String args[])
    {
        String str ="aa";
        int K=2;
        int s=LongestSubstringwithAtMostKUniqueCharecters.kDistinctChars(K,str);
        System.out.println("Length of the longest substring with at most " + K + " distinct characters: " + s);

    }
}
