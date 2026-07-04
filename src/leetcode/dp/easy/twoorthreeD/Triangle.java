package leetcode.dp.easy.twoorthreeD;

import java.util.Arrays;
import java.util.List;

/// /Given a triangle array, return the minimum path sum from top to bottom.
///
/// For each step, you may move to an adjacent number of the row below. More formally,
///  if you are on index i on the current row, you may move to either index i or index i + 1
/// on the next row.
///
///
///
/// Example 1:
///
/// Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
/// Output: 11
/// Explanation: The triangle looks like:
///    2
///   3 4
///  6 5 7
/// 4 1 8 3
/// The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
/// Example 2:
///
/// Input: triangle = [[-10]]
/// Output: -10
///
///
/// Constraints:
///
/// 1 <= triangle.length <= 200
/// triangle[0].length == 1
// triangle[i].length == triangle[i-1].length + 1
// -104 <= triangle[i][j] <= 104
public class Triangle {
    public static void main(String[] args) {
        Triangle solution = new Triangle();
        List<List<Integer>> triangle = Arrays.asList(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        );
        int result = solution.minimumTotal(triangle);
        System.out.println("Minimum path sum: " + result); // Output: 11
    }

    public int f(List<List<Integer>> triangle, int[][] dp, int i, int j) {
        int n = triangle.size();

        if (i == n - 1) return triangle.get(i).get(j);
        if (dp[i][j] != Integer.MAX_VALUE) return dp[i][j];//indicates this box has been filled
        int down = triangle.get(i).get(j) + f(triangle, dp, i + 1, j);
        int dg = triangle.get(i).get(j) + f(triangle, dp, i + 1, j + 1);
        dp[i][j] = Math.min(down, dg);
        return dp[i][j];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // int m = triangle[0].size();
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        return f(triangle, dp, 0, 0);
    }
}
