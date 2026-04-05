package leetcode.STRING.EASY.dp;
//Given a string s, find the longest palindromic subsequence's length in s.
//
//A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
//
//
//
//Example 1:
//
//Input: s = "bbbab"
//Output: 4
//Explanation: One possible longest palindromic subsequence is "bbbb".
public class LongestPalindromeSubSequence {
    //Recursive Step for a Substring s[i…j]
    ///
    // If s[i] == s[j]:
    ///
    /// The characters at positions i and j match.
    ///
    /// These two characters can be included at both ends of a palindromic subsequence.
    ///
    /// The length increases by 2 (one for s[i] and one for s[j]).
    ///
    // Add this to the longest palindromic subsequence found in the substring s[i+1…j-1].
    ///
    /// Formula:
    ///
    // dp[i][j] = dp[i + 1][j-1] + 2
    ///
    ///
    // If s[i] != s[j]:
    ///
    /// The characters at positions i and j do not match.
    ///
    /// Both characters cannot be part of the same palindromic subsequence.
    ///
    /// We have two choices:
    ///
    //Exclude s[i] and consider substring s[i+1…j]
    ///
    // Exclude s[j] and consider substring s[i…j-1]
    ///
    /// Take the maximum of these two results.
    ///
    /// Formula:
    ///
    // dp[i][j] = Math.max(dp[i + 1][j], dp[i][j-1])
    public int longestPalindromeSubseq(String s) {

        int n = s.length();
        int[][] dp = new int[n][n];

        // Base case: single characters are palindromes of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1; //coz single char is palindrome
        }
        //j<=n - 1
        //i+length - 1<= n-1
        //i<= n-length
        // Fill the DP table
        //sliding window approach
        //i=start of the window
        //j=end of the window
        //length=size of the window
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    //
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
//bottom-up approach
        return dp[0][n - 1]; //beacause coming from i+1 row to i
    }
    public int longestPalindromeSubseq1(String s) {
        int n = s.length();
        int[] dp = new int[n];
// DP table for string "bbbab"
// i \ j   0(b)  1(b)  2(b)  3(a)  4(b)
// ----------------------------------
// 0 (b)    1     2     3     3     4
// 1 (b)          1     2     2     3
// 2 (b)                1     1     3
// 3 (a)                      1     1
// 4 (b)                            1

        //TABLE--> IF MATCH IS FOUND THN ADD 2 TO DIAGONAL VALUE
        // ELSE TAKE MAX OF LEFT AND BOTTOM
        //DP[1][4] MEANS 1 INDEX TO 4 INDEX OF STRING
        // i moves backwards (from bottom row to top row in 2D)
        for (int i = n - 1; i >= 0; i--) { //here n-1 coz we are starting from last row
            //n-1 to handle base case
            dp[i] = 1; // Base case: dp[i][i] = 1
            int prev = 0; // Represents the diagonal: dp[i+1][j-1]

            for (int j = i + 1; j < n; j++) {
                int temp = dp[j]; // Store dp[i+1][j] before we overwrite it

                if (s.charAt(i) == s.charAt(j)) {
                    // dp[i][j] = dp[i+1][j-1] + 2
                    dp[j] = prev + 2;
                } else {
                    // dp[i][j] = max(dp[i+1][j], dp[i][j-1])
                    // dp[j] is currently dp[i+1][j]
                    // dp[j-1] is the newly calculated dp[i][j-1]
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }

                // The old dp[j] becomes the diagonal for the next 'j'
                prev = temp;
            }
        }

        return dp[n - 1];
    }
    public static void main(String[] args) {
        LongestPalindromeSubSequence lp = new LongestPalindromeSubSequence();
        String input = "bbbab";
        int result = lp.longestPalindromeSubseq(input);
        System.out.println("Longest Palindromic Subsequence length of " + input + " is: " + result);
    }
}
//WHY MATH.MAX(dp[i + 1][j], dp[i][j - 1]) IS USED WHEN CHARACTERS DO NOT MATCH?
//1. The “Left” Value — dp[i][j - 1]
//
//What it represents:
//
//The longest palindromic subsequence in the substring from index i to j - 1.
//
//This means we ignore the character at index j (right side).
//
//Logic:
//
//“If I cannot use the character at position j, what is the longest palindrome I can form from s[i … j-1]?”
//
//2. The “Bottom” Value — dp[i + 1][j]
//
//What it represents:
//
//The longest palindromic subsequence in the substring from index i + 1 to j.
//
//This means we ignore the character at index i (left side).
//
//Logic:
//
//“If I cannot use the character at position i, what is the longest palindrome I can form from s[i+1 … j]?”

//1. Why i <= n - length Is Used
//
//This approach uses length-based iteration instead of simple index-based looping.
//
//2. Length-Based (Sliding Window) Iteration
//
//To solve the problem for a string of length n, we must first know the answers for:
//
//All substrings of length 1
//
//Then length 2
//
//Then length 3
//
//And so on, up to length n
//
//This is called the sliding window approach.
//
//Definitions:
//
//length → size of the current window (substring)
//
//i → starting index of the window
//
//j → ending index of the window
//
//3. How i and j Are Related
//
//If a window starts at index i and has size length, then:
//
//j = i + length - 1
//
//
//For the window to be valid, j must stay within the string:
//
//j < n
//
//
//Substituting the value of j:
//
//i + length - 1 < n
//
//
//Rearranging:
//
//i <= n - length
//
//
//This condition ensures the substring does not go out of bounds.
//
//4. Example
//
//Let:
//
//n = 5
//
//length = 2
//
//Possible values of i:
//
//i = 0 → j = 1
//
//i = 1 → j = 2
//
//i = 2 → j = 3
//
//i = 3 → j = 4 (maximum allowed, since n - length = 3)
//
//If:
//
//i = 4 → j = 5 → out of bounds ❌
//
//5. How the DP Table Is Filled
//
//The DP table is filled diagonally, not row by row.
//
//Order of filling:
//
//First, all substrings of length 1 (main diagonal)
//
//Then, all substrings of length 2 (diagonal above the main one)
//
//Then, length 3, length 4, and so on
//
//Each diagonal depends on results from previous smaller diagonals.
//
//The final answer is stored at:
//
//dp[0][n - 1]
//
//
//This represents the longest palindromic subsequence of the entire string.