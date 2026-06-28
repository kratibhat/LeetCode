package leetcode.STRING.EASY.striversheet;
/// Count Substrings with a, b and c
/// Difficulty: MediumAccuracy: 51.61%Submissions: 17K+Points: 4Average Time: 20m
/// Given a string s consisting only a, b, c. return the number of substrings
/// containing at least one occurrence of all characters a, b, and c.
///
/// Examples:
///
/// Input: s = "abcabc"
/// Output: 10
/// Explanation: The required substrings  are "abc", "abca", "abcab", "abcabc",
/// "bca", "bcab", "bcabc", "cab", "cabc" and "abc".
/// Input: s = "aaacb"
/// Output: 3
/// Explanation: The required substring are "acb", "aacb", "aaacb".
public class CountSubstringswithABandC {
    public static int countSubstring(String s) {
        int[] lastSeen = {-1, -1, -1};
        int totalSubstrings = 0;
        int n = s.length();

        for (int right = 0; right < n; right++) {
            // Update the last seen position of the current character
            lastSeen[s.charAt(right) - 'a'] = right;

            // If we have seen all three characters at least once
            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
                // Find the oldest character's position
                int oldestIndex = Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));

                // All substrings starting from index 0 up to oldestIndex are valid
                totalSubstrings += (oldestIndex + 1);
            }
        }

        return totalSubstrings;   // code here

    }
    public static void main(String []args){
        String s="abcabc";
        System.out.println(countSubstring(s));
    }
}
