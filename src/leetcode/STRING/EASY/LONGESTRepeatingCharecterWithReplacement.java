package leetcode.STRING.EASY;
//You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
//
//Return the length of the longest substring containing the same letter you can get after performing the above operations.
//
//
//
//Example 1:
//
//Input: s = "ABAB", k = 2
//Output: 4
//Explanation: Replace the two 'A's with two 'B's or vice versa.
//Example 2:
//
//Input: s = "AABABBA", k = 1
//Output: 4
//Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
//The substring "BBBB" has the longest repeating letters, which is 4.
//There may exists other ways to achieve this answer too.
public class LONGESTRepeatingCharecterWithReplacement {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int maxCount = 0; // Max count of a single character in the current window
        int left = 0;
        int result = 0;

        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'A']++;
            maxCount = Math.max(maxCount, count[s.charAt(right) - 'A']);

            // Current window size is (right - left + 1)
            // If we need to replace more than k characters, shrink the window
            if (right - left + 1 - maxCount > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            result = Math.max(result, right - left + 1);
        }

        return result;
    }
    public static void main(String[] args) {
        LONGESTRepeatingCharecterWithReplacement solution = new LONGESTRepeatingCharecterWithReplacement();
        String s = "AABABBA";
        int k = 1;
        int result = solution.characterReplacement(s, k);
        System.out.println("Length of longest substring after replacements: " + result); // Output: 4
    }
}
////1. Core Idea of the Sliding Window
///
/// The goal is to find the longest substring that can be converted into a string of all the same characters using at most k replacements.
///
/// The sliding window tries to become an “ideal” window where every character is identical.
///
/// 2. The “Ideal” Window Concept
///
/// Window size
///
/// right - left + 1
///
/// Total number of characters currently inside the window.
///
/// maxCount
///
/// The count of the most frequent character in the current window.
///
/// Target
///
/// Make the entire window consist of the character with frequency maxCount.
///
/// 3. Why We Subtract maxCount
///
/// Characters that are not the majority character must be replaced.
///
/// Number of replacements needed:
///
/// replacements = (right - left + 1) - maxCount
///
///
/// This gives the count of mismatched (minority) characters in the window.
///
/// 4. Why We Compare With k
///
/// If replacements ≤ k
///
/// We have enough replacement budget.
///
/// All minority characters can be converted to the majority character.
///
/// The window is valid and can expand.
///
/// If replacements > k
///
/// We do not have enough replacements.
///
/// The window cannot be made uniform.
///
/// We shrink the window from the left.
///
/// 5. Example Trace
///
/// s = "AABABBA", k = 1
///
/// Window: "AABA" (indices 0 to 3)
///
/// Window size: 4
///
/// Character counts:
///
/// A = 3
///
/// B = 1
///
/// maxCount = 3
///
/// Replacements needed:
///
/// 4 - 3 = 1
///
///
/// Since 1 ≤ k, the window is valid.
///
/// Window: "AABAB" (indices 0 to 4)
///
/// Window size: 5
///
/// Character counts:
///
/// A = 3
///
/// B = 2
///
/// maxCount = 3
///
/// Replacements needed:
///
/// 5 - 3 = 2
///
///
/// Since 2 > k, the window is invalid.
///
/// The algorithm shrinks the window from the left.
///
/// 6. Why We Don’t Update maxCount When Shrinking
///
/// maxCount stores the maximum frequency seen so far.
///
/// Shrinking the window cannot produce a larger valid window.
///
/// A larger answer is only possible when:
///
/// The window expands, and
///
/// A character’s frequency exceeds the current maxCount.
///
/// Recalculating maxCount during shrinking is unnecessary and costly.
///
/// This optimization keeps the algorithm O(n).
///
/// 7. Key Takeaway
///
/// (window size - maxCount) = number of replacements needed.
///
/// Compare this value with k to decide:
///
/// Expand the window, or
///
/// Shrink it from the left.
///
/// This logic is the heart of the sliding window solution.