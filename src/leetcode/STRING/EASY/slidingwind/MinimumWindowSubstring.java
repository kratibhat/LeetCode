package leetcode.STRING.EASY.slidingwind;
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
//Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
public class MinimumWindowSubstring {
    //
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        int[] dictT = new int[128];
        for (char c : t.toCharArray()) {
            dictT[c]++;
        }

        int required = t.length();
        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0; // Starting index of the minimum window

        while (right < s.length()) {
            char cRight = s.charAt(right);
            if (dictT[cRight] > 0) {
                required--;
            }
            dictT[cRight]--;
            right++;

            while (required == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    minLeft = left;
                }
                char cLeft = s.charAt(left);
                dictT[cLeft]++;
                if (dictT[cLeft] > 0) {
                    required++;
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
    public static void main(String[] args) {
        MinimumWindowSubstring solution = new MinimumWindowSubstring();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = solution.minWindow(s, t);
        System.out.println("Minimum window substring: " + result); // Output: "BANC"
    }

}
//Choosing the Right Sliding Window Pattern
//
//(The “Balance Sheet” / “Negative Count” Method)
//
//This method is the gold standard for Minimum Window Substring–type problems.
//
//1. Constraint vs. Optimization Check
//
//Ask yourself this key question:
//
//“Am I looking for a window that must contain a specific set of items?”
//
//If yes:
//
//The problem has constraints
//
//Example: the window must contain all characters of string T.
//
//This method works perfectly because:
//
//required → tracks whether constraints are satisfied.
//
//while (required == 0) → handles optimization by shrinking the window to minimum size.
//
//2. The “At Least” Rule
//
//This approach is designed for “at least” problems.
//
//Problem type
//
//“Find a window that has at least these characters.”
//
//Method
//
//Expand the window until all requirements are met.
//
//Shrink the window until it almost breaks the requirement.
//
//Contrast
//
//If the problem was:
//
//“Find a window with exactly these characters”
//
//Then a fixed-size sliding window might be more suitable.
//
//3. Efficiency Requirement (O(N))
//
//When input size N is large (for example, 100,000 or more):
//
//Nested loops with O(N²) are too slow.
//
//Why this method is efficient:
//
//The right pointer moves from start to end once.
//
//The left pointer also moves from start to end once.
//
//Total operations: O(2N) → O(N)
//
//Conclusion:
//
//If you need to find a substring and N is large,
//
//A two-pointer sliding window is almost always the correct choice.
//
//4. How to Recognize This Pattern in an Interview
//
//If you see these three keywords together, use this approach:
//
//Substring
//
//Indicates a contiguous window.
//
//Minimum
//
//Indicates the window must be shrunk once it becomes valid.
//
//All characters of T
//
//Indicates the need for a frequency map or counter.
//
//Final Takeaway
//
//Constraints → tracked by required
//
//Optimization → handled by shrinking the window
//
//Large input size → demands O(N)
//
//These signals together strongly point to the
//Sliding Window + Negative Count (Balance Sheet) pattern


//---------------------------



//. The Setup (Preparation)
//
//dictT
//
//An array that stores the frequency of characters in string t.
//
//As the window moves:
//
//Positive values → characters still needed
//
//Zero → exactly satisfied
//
//Negative values → extra or useless characters in the window
//
//required
//
//Tracks the total number of characters still needed to make the current window valid.
//
//When required == 0, the window contains all characters of t.
//
//2. The Right Pointer (Expanding the Window)
//
//As the right pointer moves forward, it consumes characters:
//
//Check if useful
//
//If dictT[cRight] > 0, the character is needed.
//
//Decrement required.
//
//Update balance
//
//Always decrement:
//
//dictT[cRight]--
//
//
//Effects:
//
//Characters in t move toward zero.
//
//Characters not in t become negative, marking them as extra.
//
//3. The Left Pointer (Shrinking the Window)
//
//The loop while (required == 0) runs when the window is valid.
//
//Record minimum window
//
//If (right - left) is smaller than minLen, update:
//
//minLen
//
//minLeft
//
//Release the left character
//
//Increment:
//
//dictT[cLeft]++
//
//
//Check if vital
//
//If dictT[cLeft] > 0 after incrementing:
//
//A required character was removed.
//
//Increment required.
//
//Exit the loop and resume expanding the window.
//
//4. Why the dictT Logic Works (The “Negative” Trick)
//
//Characters in t start with positive counts.
//
//Characters not in t start at 0.
//
//When right passes:
//
//A needed character → count moves toward zero.
//
//A non-needed character → count becomes negative.
//
//When left passes:
//
//Negative values move back toward zero.
//
//The condition:
//
//if (dictT[cLeft] > 0)
//
//
//becomes true only for characters originally required by t.
//
//5. Example Trace
//
//s = "ADOBE", t = "ABC"
//
//Initial state:
//
//dictT: A=1, B=1, C=1
//
//required = 3
//
//Right → 'A'
//
//dictT[A] = 0
//
//required = 2
//
//Right → 'D'
//
//dictT[D] = -1
//
//required = 2 (not in t)
//
//Right → 'B'
//
//dictT[B] = 0
//
//required = 1
//
//Right → 'C'
//
//dictT[C] = 0
//
//required = 0 (window is valid)
//
//Left moves to shrink:
//
//Remove 'A'
//
//dictT[A] = 1
//
//Since value > 0, required = 1
//
//Stop shrinking and expand again
//
//6. Complexity Analysis
//
//Time Complexity
//
//O(S + T)
//
//Each character in s is processed at most twice (once by right, once by left).
//
//Space Complexity
//
//O(1)
//
//The dictT array has a fixed size (128 characters).
//
//Key Takeaway
//
//Positive → needed
//
//Zero → satisfied
//
//Negative → extra
//
//required controls when the window is valid
//
//The negative-count trick enables an efficient sliding window solution