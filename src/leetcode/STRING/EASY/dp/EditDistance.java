package leetcode.STRING.EASY.dp;
//Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
//
//You have the following three operations permitted on a word:
//
//Insert a character
//Delete a character
//Replace a character
//
//
//Example 1:
//
//Input: word1 = "horse", word2 = "ros"
//Output: 3
//Explanation:
//horse -> rorse (replace 'h' with 'r')
//rorse -> rose (remove 'r')
//rose -> ros (remove 'e')
//Example 2:
//
//Input: word1 = "intention", word2 = "execution"
//Output: 5
//Explanation:
//intention -> inention (remove 't')
//inention -> enention (replace 'i' with 'e')
//enention -> exention (replace 'n' with 'x')
//exention -> exection (replace 'n' with 'c')
//exection -> execution (insert 'u')
public class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Initialize base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // Deleting all characters from word1 IF word2 is empty
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // Inserting all characters to form word2 IF word1 is empty
        }
//// Edit Distance DP table (word1 = "horse", word2 = "ros")
////
////        ""   r    o    s
////      -------------------
//// ""     0    1    2    3
//// h      1    1    2    3
//// o      2    2    1    2
//// r      3    2    2    2
//// s      4    3    3    2
//// e      5    4    4    3
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // No operation needed
                } else {
                    dp[i][j] = Math.min(Math.min(
                            dp[i - 1][j] + 1,    // Deletion
                            dp[i][j - 1] + 1),   // Insertion
                            dp[i - 1][j - 1] + 1 // Substitution
                    );
                }
            }
        }

        return dp[m][n];
    }
    public static void main(String[] args) {
        EditDistance solution = new EditDistance();
        String word1 = "horse";
        String word2 = "ros";
        int result = solution.minDistance(word1, word2);
        System.out.println("Minimum edit distance: " + result); // Output: 3
    }
}
////1. Base Cases (Initialization)
///
/// Before comparing characters, we handle cases where one of the strings is empty.
///
/// When word2 is empty
///
/// dp[i][0] = i
///
/// To convert the first i characters of word1 into an empty string, we must delete all i characters.
///
/// When word1 is empty
///
/// dp[0][j] = j
///
/// To convert an empty string into the first j characters of word2, we must insert all j characters.
///
/// 2. Iterative Logic (Filling the DP Table)
///
/// We fill the table by comparing characters from both strings, one by one.
///
/// For each position dp[i][j], there are two main cases:
///
/// Case A: Characters Match
///
/// word1[i-1] == word2[j-1]
///
/// The current characters are the same.
///
/// No new operation is needed.
///
/// The cost remains the same as converting the previous prefixes.
///
/// Formula:
///
/// dp[i][j] = dp[i - 1][j-1]
///
///
/// Interpretation:
///
/// Move diagonally in the DP table.
///
/// Case B: Characters Do NOT Match
///
/// word1[i-1] != word2[j-1]
///
/// An operation is required.
///
/// We consider the three possible operations.
///
/// Choose the minimum cost among them and add 1.
///
/// 1. Deletion
///
/// dp[i - 1][j] + 1
///
/// Look at the cell above.
///
/// Logic:
///
/// You have matched word1 up to i - 1 with word2 up to j.
///
/// The extra character in word1 is deleted.
///
/// 2. Insertion
///
/// dp[i][j-1] + 1
///
/// Look at the cell to the left.
///
/// Logic:
///
/// You have matched word1 up to i with word2 up to j - 1.
///
/// Insert the character word2[j-1] into word1.
///
/// 3. Substitution (Replace)
///
/// dp[i - 1][j-1] + 1
///
/// Look at the diagonal cell.
///
/// Logic:
///
/// Replace the character word1[i-1] with word2[j-1].