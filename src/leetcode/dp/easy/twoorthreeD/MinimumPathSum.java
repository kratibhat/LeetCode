package leetcode.dp.easy.twoorthreeD;

import java.util.Arrays;
/// /Given a m x n grid filled with non-negative numbers, find a path from top left to
///  bottom right, which minimizes the sum of all numbers along its path.
///
/// Note: You can only move either down or right at any point in time.
///
///
///
/// Example 1:
///
///
/// Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
/// Output: 7
/// Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
/// Example 2:
///
/// Input: grid = [[1,2,3],[4,5,6]]
/// Output: 12
public class MinimumPathSum {
    /// /4ms
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int[][] dp = new int[row][col];

        // Base Case: Starting point cost is just its own value
        dp[0][0] = grid[0][0];

        // Initialize the first column (can only move down)
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // Initialize the first row (can only move right)
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // Fill the rest of the dp grid
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                // Take the minimum cost from above or left, then add current cell value
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        // Return the bottom-right corner value
        return dp[row - 1][col - 1];
    }
    public int minPathSum1ms(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(dp[i], -1);

        return helper(grid, m - 1, n - 1, dp);
    }

    private int helper(int[][] grid, int i, int j, int[][] dp) {
        if (i == 0 && j == 0)
            return grid[i][j];

        if (dp[i][j] != -1)
            return dp[i][j];

        int minSum = Integer.MAX_VALUE;

        if (i - 1 >= 0) {
            minSum = Math.min(minSum, helper(grid, i - 1, j, dp));
        }


        if (j - 1 >= 0) {
            minSum = Math.min(minSum, helper(grid, i, j - 1, dp));
        }

        dp[i][j] = grid[i][j] + minSum;
        return dp[i][j];
    }
    public static void main(String[] args) {
        MinimumPathSum solution = new MinimumPathSum();
        int[][] grid1 = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(solution.minPathSum(grid1)); // Output: 7

        int[][] grid2 = {{1,2,3},{4,5,6}};
        System.out.println(solution.minPathSum1ms(grid2)); // Output: 12
    }
}
/*
=========================================================
Minimum Path Sum (Dynamic Programming)
=========================================================

PROBLEM
-------
Given an m x n grid where each cell contains a cost,
find the minimum cost path from the top-left corner
to the bottom-right corner.

The robot can move ONLY:
    1. Right  →
    2. Down   ↓

Goal:
Return the minimum total cost to reach the destination.

---------------------------------------------------------
Example
---------------------------------------------------------

Grid:

1 3 1
1 5 1
4 2 1

Possible Path:

1 → 3 → 1
          ↓
          1
          ↓
          1

Total Cost = 1 + 3 + 1 + 1 + 1 = 7

Answer = 7

=========================================================
DP IDEA
=========================================================

Instead of storing the number of paths,

dp[i][j] stores:

"The minimum cost required to reach cell (i,j)."

How can the robot reach any cell?

Only from:
1. Top
2. Left

Therefore,

Minimum Cost(Current Cell)

=

Current Cell Cost

+

Minimum(Top Cost, Left Cost)

Formula:

dp[i][j] = grid[i][j] +
           Math.min(dp[i-1][j], dp[i][j-1])

=========================================================
Code Explanation
=========================================================

---------------------------------------------------------
Step 1
---------------------------------------------------------

int row = grid.length;
int col = grid[0].length;

Find the number of rows and columns.

Example:

1 3 1
1 5 1
4 2 1

row = 3
col = 3

---------------------------------------------------------
Step 2
---------------------------------------------------------

int[][] dp = new int[row][col];

Create another matrix called dp.

Each cell stores:

"The minimum cost required to reach this cell."

Initially,

0 0 0
0 0 0
0 0 0

---------------------------------------------------------
Step 3
---------------------------------------------------------

dp[0][0] = grid[0][0];

Why?

The robot starts at (0,0).

So the minimum cost to reach the starting cell
is simply the value of that cell.

Example:

Grid

1 3 1
1 5 1
4 2 1

DP

1 0 0
0 0 0
0 0 0

---------------------------------------------------------
Step 4
---------------------------------------------------------

for(int i=1;i<row;i++){

    dp[i][0] = dp[i-1][0] + grid[i][0];

}

Initialize the FIRST COLUMN.

Why?

The robot cannot move LEFT.

Therefore every cell in the first column
can only be reached from ABOVE.

Formula:

Current Cost
=
Cost Above
+
Current Cell Value

Example:

Grid

1
1
4

DP

dp[0][0] = 1

dp[1][0] = 1 + 1 = 2

dp[2][0] = 2 + 4 = 6

Result

1
2
6

---------------------------------------------------------
Step 5
---------------------------------------------------------

for(int j=1;j<col;j++){

    dp[0][j] = dp[0][j-1] + grid[0][j];

}

Initialize the FIRST ROW.

Why?

The robot cannot move UP.

Therefore every cell in the first row
can only be reached from the LEFT.

Formula:

Current Cost
=
Left Cost
+
Current Cell Value

Example:

Grid

1 3 1

DP

dp[0][0] = 1

dp[0][1] = 1 + 3 = 4

dp[0][2] = 4 + 1 = 5

Result

1 4 5

---------------------------------------------------------
Step 6
---------------------------------------------------------

for(int i=1;i<row;i++){

    for(int j=1;j<col;j++){

Loop through all remaining cells.

We already calculated

• First Row
• First Column

Now calculate every other cell.

---------------------------------------------------------
Step 7 (Main DP Formula)
---------------------------------------------------------

dp[i][j] =
grid[i][j]
+
Math.min(dp[i-1][j], dp[i][j-1]);

This is the heart of the algorithm.

Why?

Suppose we want the minimum cost
to reach X.

        Top
         |
         v
      A  B
      C  X

The robot can reach X only from:

1. Top
2. Left

Suppose

Top Cost = 8

Left Cost = 5

Current Cell Cost = 2

If we come from Top

8 + 2 = 10

If we come from Left

5 + 2 = 7

Minimum Cost = 7

Therefore,

Current Cost

=

Current Cell Value

+

Minimum(Top Cost, Left Cost)

=========================================================
Dry Run
=========================================================

Grid

1 3 1
1 5 1
4 2 1

---------------------------------------------------------
Initial DP
---------------------------------------------------------

1 0 0
0 0 0
0 0 0

---------------------------------------------------------
Fill First Column
---------------------------------------------------------

1 0 0
2 0 0
6 0 0

---------------------------------------------------------
Fill First Row
---------------------------------------------------------

1 4 5
2 0 0
6 0 0

---------------------------------------------------------
Compute Remaining Cells
---------------------------------------------------------

Cell (1,1)

Current = 5

Top = 4

Left = 2

Answer

5 + min(4,2)

=

7

DP

1 4 5
2 7 0
6 0 0

-------------------------------------

Cell (1,2)

Current = 1

Top = 5

Left = 7

Answer

1 + min(5,7)

=

6

DP

1 4 5
2 7 6
6 0 0

-------------------------------------

Cell (2,1)

Current = 2

Top = 7

Left = 6

Answer

2 + min(7,6)

=

8

DP

1 4 5
2 7 6
6 8 0

-------------------------------------

Cell (2,2)

Current = 1

Top = 6

Left = 8

Answer

1 + min(6,8)

=

7

Final DP

1 4 5
2 7 6
6 8 7

---------------------------------------------------------
Final Answer
---------------------------------------------------------

return dp[row-1][col-1];

The bottom-right cell stores the minimum cost
required to reach the destination.

Answer = 7

=========================================================
Time Complexity
=========================================================

O(row × col)

Every cell is processed exactly once.

=========================================================
Space Complexity
=========================================================

O(row × col)

A DP table of the same size as the grid is used.

=========================================================
*/