package leetcode.dp.easy.twoorthreeD;

import java.util.Arrays;
//You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number of cherries that you can collect from the (i, j) cell.
///
/// You have two robots that can collect cherries for you:
///
/// Robot #1 is located at the top-left corner (0, 0), and
/// Robot #2 is located at the top-right corner (0, cols - 1).
/// Return the maximum number of cherries collection using both robots by following the rules below:
///
/// From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
/// When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
/// When both robots stay in the same cell, only one takes the cherries.
/// Both robots cannot move outside of the grid at any moment.
/// Both robots should reach the bottom row in grid.
///
///
/// Example 1:
///
///
/// Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
/// Output: 24
/// Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
/// Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
/// Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
/// Total of cherries: 12 + 12 = 24.
/// Example 2:
///
///
/// SAME PROBLEM AS CHOCLATE PICK IN GFG
public class CherryPick2 {
    public int cherryPickup(int[][] grid) {
        /// /SAME AS NINJA AND HIS FRIENDS
        int rows = grid.length;
        int cols = grid[0].length;

        // memo[r][c1][c2] initialized to -1
        int[][][] memo = new int[rows][cols][cols];
        for (int[][] layer : memo) {
            for (int[] row : layer) {
                Arrays.fill(row, -1);
            }
        }

        // Robot 1 starts at (0, 0), Robot 2 starts at (0, cols - 1)
        return dfs(0, 0, cols - 1, grid, rows, cols, memo);
    }

    private int dfs(int r, int c1, int c2, int[][] grid, int rows, int cols, int[][][] memo) {
        // Base Case 1: Out of bounds check
        if (c1 < 0 || c1 >= cols || c2 < 0 || c2 >= cols) {
            return Integer.MIN_VALUE;
        }

        // Base Case 2: Already calculated state
        if (memo[r][c1][c2] != -1) {
            return memo[r][c1][c2];
        }

        // Collect cherries at current positions
        int cherries = (c1 == c2) ? grid[r][c1] : grid[r][c1] + grid[r][c2];

        // Base Case 3: Reached the bottom row
        if (r == rows - 1) {
            return cherries;
        }

        int maxNext = 0;

        // Explore all 9 combinations of movements for the next row
        for (int i = -1; i <= 1; i++) {      // Robot 1 delta adjustment
            for (int j = -1; j <= 1; j++) {  // Robot 2 delta adjustment
                int nextVal = dfs(r + 1, c1 + i, c2 + j, grid, rows, cols, memo);
                maxNext = Math.max(maxNext, nextVal);
            }
        }

        // Cache the result before returning
        return memo[r][c1][c2] = cherries + maxNext;
    }
    public static void main(String []args){
        CherryPick2 solution = new CherryPick2();
        int[][] grid = {
                {3, 1, 1},
                {2, 5, 1},
                {1, 5, 5},
                {2, 1, 1}
        };
        int[][] grid1 = {
                {4, 1, 2},
                {3, 6, 1},
                {1, 6, 6},
                {3, 1, 2}
        };
        int[][] grid2 = {
                {2, 3, 1, 2},
                {3, 4, 2, 2},
                {5, 6, 3, 5}
        };
        int[][] grid3 = {
                {4,1,2},
                {7,3,5}
        };
        int res2=solution.cherryPickup(grid3);
        int result = solution.cherryPickup(grid);
        int res=solution.cherryPickup(grid2);
        int res1=solution.cherryPickup(grid1);
        System.out.println("Maximum cherries collected: " + result); // Output: 24
        System.out.println("Maximum cherries collected in grid1: " + res1); // Output: 24
        System.out.println("Maximum cherries collected in grid2: " + res); // Output: 24
        System.out.println("Maximum cherries collected in grid3: " + res2); // Output: 24
    }
}

