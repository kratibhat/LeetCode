package leetcode.STRING.EASY;
//76. Minimum Window Substring
//Hard
//Topics
//premium lock icon
//Companies
//Hint
//Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
//
//The testcases will be generated such that the answer is unique.
//
//
//
//Example 1:
//
//Input: s = "ADOBECODEBANC", t = "ABC"
//Output: "BANC"
//Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t




//Build a frequency count of characters needed from t.
//
//Expand window with right until all characters are found.
//
//Shrink window with left to find the smallest valid window.
//
//Track minimum window boundaries.
//
//Return the smallest valid substring.
public class SmallestWindowContainingAllCharecters {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        int[] tFreq = new int[128];
        for (char c : t.toCharArray()) {
            tFreq[c]++;
        }
//What’s happening:
//
//If the character at right is needed (tFreq > 0), then we successfully match one more required character → decrease required.
//
//Regardless, we decrement tFreq[rChar] because it is now in our window.
//
//This allows tFreq to go negative if extra unneeded characters appear, which is useful.
        int left = 0, right = 0, required = t.length();
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;
//Before expanding the window:
//
//tFreq[c] > 0 means:
//→ We still need this character.
//
//tFreq[c] == 0 means:
//→ We have exactly enough of this character.
//
//tFreq[c] < 0 means:
//→ We have more than needed (extra copies).
        while (right < s.length()) {
            char rChar = s.charAt(right);
            if (tFreq[rChar] > 0) {
                required--;
            }
            tFreq[rChar]--;
            right++;
//✔ If tFreq[lChar] > 0 after incrementing:
//
//We just removed a needed character,
//meaning the window is no longer valid.
            while (required == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    minStart = left;
                }

                char lChar = s.charAt(left);
                tFreq[lChar]++;
                if (tFreq[lChar] > 0) {
                    required++;
                }
                left++;
            }
        }
//When sliding right:
//
//tFreq[c]--
//→ we are using characters to satisfy requirements.
//
//When sliding left:
//
//tFreq[c]++
//→ we are giving back characters.
//
//After giving back:
//
//If the frequency becomes > 0
//→ we now need this character again
//→ The window is not valid anymore
//
//So we stop shrinking and continue expanding.
//
//🖼 Mini Visual Example
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
    public static void main(String[] args) {
        SmallestWindowContainingAllCharecters solution = new SmallestWindowContainingAllCharecters();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = solution.minWindow(s, t);
        System.out.println("Minimum window substring: " + result); // Output: "BANC"
    }
}
