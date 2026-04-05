package leetcode.dp.easy;

import java.util.Arrays;

//There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
//
//Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
//
//The test cases are generated so that the answer will be less than or equal to 2 * 109.
//
//
//
//Example 1:
//
//
//Input: m = 3, n = 7
//Output: 28
//Example 2:
//
//Input: m = 3, n = 2
//Output: 3
//Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
//1. Right -> Down -> Down
//2. Down -> Down -> Right
//3. Down -> Right -> Down
//
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[] row = new int[n];
        Arrays.fill(row, 1);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // New value = current value (from top) + previous value (from left)
                row[j] = row[j] + row[j - 1];
            }
        }
        return row[n - 1];
    }
    public static void main(String[] args) {
        UniquePaths solution = new UniquePaths();
        int m1 = 3, n1 = 7;
        System.out.println("Unique paths from top-left to bottom-right in a " + m1 + "x" + n1 + " grid: " + solution.uniquePaths(m1, n1)); // Output: 28

        int m2 = 3, n2 = 2;
        System.out.println("Unique paths from top-left to bottom-right in a " + m2 + "x" + n2 + " grid: " + solution.uniquePaths(m2, n2)); // Output: 3
    }
}
