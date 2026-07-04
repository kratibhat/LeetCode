package leetcode.dp.easy.twoorthreeD;

//You are given an m x n integer array grid. There is a robot initially located at the
// top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner
// (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
//
//An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes
// cannot include any square that is an obstacle.
//
//Return the number of possible unique paths that the robot can take to reach the bottom-right
// corner.
//
//The testcases are generated so that the answer will be less than or equal to 2 * 109.

/// Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
/// Output: 2
/// Explanation: There is one obstacle in the middle of the 3x3 grid above.
/// There are two ways to reach the bottom-right corner:
/// 1. Right -> Right -> Down -> Down
/// 2. Down -> Down -> Right -> Right
public class UniquePaths2 {
    public static void main(String[] args) {
        UniquePaths2 solution = new UniquePaths2();
        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int result = solution.uniquePathsWithObstacles(obstacleGrid);
        System.out.println("Number of unique paths: " + result); // Output: 2
    }

    public int uniquePathsWithObstacles(int[][] og) {
        int row = og.length;
        int col = og[0].length;
        if (og[0][0] == 1 || og[row - 1][col - 1] == 1) return 0;
        int[][] dp = new int[row][col];
        dp[0][0] = 1;
        for (int i = 1; i < row; i++) {
            if (og[i][0] == 0) {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int i = 1; i < col; i++) {
            if (og[0][i] == 0) {
                dp[0][i] = dp[0][i - 1];
            }
        }
        return grid(og, row, col, dp);
    }

    public static int grid(int[][] og, int row, int col, int[][] dp) {
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (og[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[row - 1][col - 1];
    }
}
//////*
/// =========================================================
/// Unique Paths II (Dynamic Programming)
/// =========================================================
///
/// PROBLEM
/// -------
/// A robot is placed at the top-left corner of a grid.
///
/// The robot can move ONLY:
///     1. Right  →
///     2. Down   ↓
///
/// Some cells contain obstacles (represented by 1).
///
/// The robot CANNOT move through an obstacle.
///
/// 0 = Empty Cell
/// 1 = Obstacle
///
/// We need to find the total number of unique paths
/// from the top-left corner to the bottom-right corner.
///
/// ---------------------------------------------------------
/// Example
/// ---------------------------------------------------------
///
/// Input:
///
/// 0 0 0
/// 0 1 0
/// 0 0 0
///
/// S = Start
/// E = End
/// X = Obstacle
///
/// S  .  .
/// .  X  .
/// .  .  E
///
/// Possible Paths:
///
/// Path 1:
/// S → → ↓ ↓
///
/// Path 2:
/// S ↓ ↓ → →
///
/// Answer = 2
///
/// =========================================================
/// DP IDEA
/// =========================================================
///
/// For every cell,
///
/// How can the robot reach this cell?
///
/// It can come only from
///
/// 1. Top (Down move)
/// 2. Left (Right move)
///
/// So,
///
/// Ways(Current Cell)
/// =
/// Ways(Top Cell)
/// +
/// Ways(Left Cell)
///
/// Formula:
///
/// dp[i][j] = dp[i-1][j] + dp[i][j-1]
///
/// If the current cell is an obstacle,
///
/// Ways(Current Cell) = 0
///
/// because the robot cannot stand there.
///
/// =========================================================
/// Code Explanation
/// =========================================================
///
/// ---------------------------------------------------------
/// Step 1
/// ---------------------------------------------------------
///
/// int row = og.length;
/// int col = og[0].length;
///
/// Find number of rows and columns.
///
/// Example:
///
/// 0 0 0
/// 0 1 0
/// 0 0 0
///
/// row = 3
/// col = 3
///
/// ---------------------------------------------------------
/// Step 2
/// ---------------------------------------------------------
///
/// if (og[0][0] == 1 || og[row-1][col-1] == 1)
///     return 0;
///
/// If the START or END contains an obstacle,
///
/// there is no possible path.
///
/// Return 0 immediately.
///
/// ---------------------------------------------------------
/// Step 3
/// ---------------------------------------------------------
///
/// int[][] dp = new int[row][col];
///
/// Create DP table.
///
/// Each cell stores:
///
/// "Number of ways to reach this cell."
///
/// Initially,
///
/// 0 0 0
/// 0 0 0
/// 0 0 0
///
/// ---------------------------------------------------------
/// Step 4
/// ---------------------------------------------------------
///
/// dp[0][0] = 1;
///
/// Robot is already standing on the starting cell.
///
/// Therefore,
///
/// there is exactly ONE way to reach it.
///
/// DP becomes
///
/// 1 0 0
/// 0 0 0
/// 0 0 0
///
/// ---------------------------------------------------------
/// Step 5
/// ---------------------------------------------------------
///
/// for (int i = 1; i < row; i++) {
///
///     if (og[i][0] == 0)
///         dp[i][0] = dp[i-1][0];
///
/// }
///
/// Initialize the FIRST COLUMN.
///
/// Reason:
///
/// Robot can move only DOWN in the first column.
///
/// Example:
///
/// 0
/// 0
/// 0
///
/// DP
///
/// 1
/// 1
/// 1
///
/// If obstacle exists,
///
/// 0
/// 1
/// 0
///
/// DP
///
/// 1
/// 0
/// 0
///
/// because obstacle blocks every cell below it.
///
/// ---------------------------------------------------------
/// Step 6
/// ---------------------------------------------------------
///
/// for (int i = 1; i < col; i++) {
///
///     if (og[0][i] == 0)
///         dp[0][i] = dp[0][i-1];
///
/// }
///
/// Initialize the FIRST ROW.
///
/// Reason:
///
/// Robot can move only RIGHT.
///
/// Example:
///
/// 0 0 0
///
/// DP
///
/// 1 1 1
///
/// If obstacle exists,
///
/// 0 1 0
///
/// DP
///
/// 1 0 0
///
/// Obstacle blocks every cell after it.
///
/// ---------------------------------------------------------
/// Step 7
/// ---------------------------------------------------------
///
/// return grid(og,row,col,dp);
///
/// After initialization,
///
/// calculate the remaining cells.
///
/// =========================================================
/// Helper Function
/// =========================================================
///
/// for (int i = 1; i < row; i++) {
///
///     for (int j = 1; j < col; j++) {
///
/// Process every remaining cell.
///
/// ---------------------------------------------------------
/// Obstacle Check
/// ---------------------------------------------------------
///
/// if (og[i][j] == 0)
///
/// If current cell is NOT an obstacle,
///
/// calculate number of paths.
///
/// ---------------------------------------------------------
/// Main DP Formula
/// ---------------------------------------------------------
///
/// dp[i][j] = dp[i-1][j] + dp[i][j-1];
///
/// Meaning:
///
/// Ways(Current)
/// =
/// Ways(Top)
/// +
/// Ways(Left)
///
/// Example:
///
/// Current DP
///
/// 1 1 1
/// 1 ? ?
/// 1 ? ?
///
/// Compute dp[1][1]
///
/// Top = 1
/// Left = 1
///
/// Answer
///
/// 1 + 1 = 2
///
/// Grid becomes
///
/// 1 1 1
/// 1 2 ?
/// 1 ? ?
///
/// Next cell
///
/// Top = 1
///
/// Left = 2
///
/// Answer
///
/// 3
///
/// Grid
///
/// 1 1 1
/// 1 2 3
/// 1 ? ?
///
/// ---------------------------------------------------------
/// Obstacle Case
/// ---------------------------------------------------------
///
/// else {
///
///     dp[i][j] = 0;
///
/// }
///
/// Obstacle means
///
/// Robot cannot stand here.
///
/// Therefore,
///
/// Number of ways = 0
///
/// ---------------------------------------------------------
/// Final Answer
/// ---------------------------------------------------------
///
/// return dp[row-1][col-1];
///
/// The bottom-right cell stores
///
/// the total number of unique paths.
///
/// Return it.
///
/// =========================================================
/// Dry Run
/// =========================================================
///
/// Input
///
/// 0 0 0
/// 0 1 0
/// 0 0 0
///
/// Step 1
///
/// 1 0 0
/// 0 0 0
/// 0 0 0
///
/// Initialize First Column
///
/// 1 0 0
/// 1 0 0
/// 1 0 0
///
/// Initialize First Row
///
/// 1 1 1
/// 1 0 0
/// 1 0 0
///
/// Compute Remaining Cells
///
/// Cell (1,1)
///
/// Obstacle
///
/// 1 1 1
/// 1 0 0
/// 1 0 0
///
/// Cell (1,2)
///
/// Top = 1
/// Left = 0
///
/// 1
///
/// 1 1 1
/// 1 0 1
/// 1 0 0
///
/// Cell (2,1)
///
/// Top = 0
/// Left = 1
///
/// 1
///
/// 1 1 1
/// 1 0 1
/// 1 1 0
///
/// Cell (2,2)
///
/// Top = 1
/// Left = 1
///
/// 2
///
/// Final DP
///
/// 1 1 1
/// 1 0 1
/// 1 1 2
///
/// Answer = 2
///
/// =========================================================
/// Time Complexity
/// =========================================================
///
/// O(row × col)
///
/// Every cell is visited exactly once.
///
/// =========================================================
/// Space Complexity
/// =========================================================
///
/// O(row × col)
///
/// DP table stores one value for every cell.
///
/// =========================================================
/// */


////*
/// =========================================================
/// Unique Paths II (Dynamic Programming)
/// =========================================================
///
/// PROBLEM
/// -------
/// A robot is placed at the top-left corner of a grid.
///
/// The robot can move ONLY:
///     1. Right  →
///     2. Down   ↓
///
/// Some cells contain obstacles (represented by 1).
///
/// The robot CANNOT move through an obstacle.
///
/// 0 = Empty Cell
/// 1 = Obstacle
///
/// We need to find the total number of unique paths
/// from the top-left corner to the bottom-right corner.
///
/// ---------------------------------------------------------
/// Example
/// ---------------------------------------------------------
///
/// Input:
///
/// 0 0 0
/// 0 1 0
/// 0 0 0
///
/// S = Start
/// E = End
/// X = Obstacle
///
/// S  .  .
/// .  X  .
/// .  .  E
///
/// Possible Paths:
///
/// Path 1:
/// S → → ↓ ↓
///
/// Path 2:
/// S ↓ ↓ → →
///
/// Answer = 2
///
/// =========================================================
/// DP IDEA
/// =========================================================
///
/// For every cell,
///
/// How can the robot reach this cell?
///
/// It can come only from
///
/// 1. Top (Down move)
/// 2. Left (Right move)
///
/// So,
///
/// Ways(Current Cell)
/// =
/// Ways(Top Cell)
/// +
/// Ways(Left Cell)
///
/// Formula:
///
/// dp[i][j] = dp[i-1][j] + dp[i][j-1]
///
/// If the current cell is an obstacle,
///
/// Ways(Current Cell) = 0
///
/// because the robot cannot stand there.
///
/// =========================================================
/// Code Explanation
/// =========================================================
///
/// ---------------------------------------------------------
/// Step 1
/// ---------------------------------------------------------
///
/// int row = og.length;
/// int col = og[0].length;
///
/// Find number of rows and columns.
///
/// Example:
///
/// 0 0 0
/// 0 1 0
/// 0 0 0
///
/// row = 3
/// col = 3
///
/// ---------------------------------------------------------
/// Step 2
/// ---------------------------------------------------------
///
/// if (og[0][0] == 1 || og[row-1][col-1] == 1)
///     return 0;
///
/// If the START or END contains an obstacle,
///
/// there is no possible path.
///
/// Return 0 immediately.
///
/// ---------------------------------------------------------
/// Step 3
/// ---------------------------------------------------------
///
/// int[][] dp = new int[row][col];
///
/// Create DP table.
///
/// Each cell stores:
///
/// "Number of ways to reach this cell."
///
/// Initially,
///
/// 0 0 0
/// 0 0 0
/// 0 0 0
///
/// ---------------------------------------------------------
/// Step 4
/// ---------------------------------------------------------
///
/// dp[0][0] = 1;
///
/// Robot is already standing on the starting cell.
///
/// Therefore,
///
/// there is exactly ONE way to reach it.
///
/// DP becomes
///
/// 1 0 0
/// 0 0 0
/// 0 0 0
///
/// ---------------------------------------------------------
/// Step 5
/// ---------------------------------------------------------
///
/// for (int i = 1; i < row; i++) {
///
///     if (og[i][0] == 0)
///         dp[i][0] = dp[i-1][0];
///
/// }
///
/// Initialize the FIRST COLUMN.
///
/// Reason:
///
/// Robot can move only DOWN in the first column.
///
/// Example:
///
/// 0
/// 0
/// 0
///
/// DP
///
/// 1
/// 1
/// 1
///
/// If obstacle exists,
///
/// 0
/// 1
/// 0
///
/// DP
///
/// 1
/// 0
/// 0
///
/// because obstacle blocks every cell below it.
///
/// ---------------------------------------------------------
/// Step 6
/// ---------------------------------------------------------
///
/// for (int i = 1; i < col; i++) {
///
///     if (og[0][i] == 0)
///         dp[0][i] = dp[0][i-1];
///
/// }
///
/// Initialize the FIRST ROW.
///
/// Reason:
///
/// Robot can move only RIGHT.
///
/// Example:
///
/// 0 0 0
///
/// DP
///
/// 1 1 1
///
/// If obstacle exists,
///
/// 0 1 0
///
/// DP
///
/// 1 0 0
///
/// Obstacle blocks every cell after it.
///
/// ---------------------------------------------------------
/// Step 7
/// ---------------------------------------------------------
///
/// return grid(og,row,col,dp);
///
/// After initialization,
///
/// calculate the remaining cells.
///
/// =========================================================
/// Helper Function
/// =========================================================
///
/// for (int i = 1; i < row; i++) {
///
///     for (int j = 1; j < col; j++) {
///
/// Process every remaining cell.
///
/// ---------------------------------------------------------
/// Obstacle Check
/// ---------------------------------------------------------
///
/// if (og[i][j] == 0)
///
/// If current cell is NOT an obstacle,
///
/// calculate number of paths.
///
/// ---------------------------------------------------------
/// Main DP Formula
/// ---------------------------------------------------------
///
/// dp[i][j] = dp[i-1][j] + dp[i][j-1];
///
/// Meaning:
///
/// Ways(Current)
/// =
/// Ways(Top)
/// +
/// Ways(Left)
///
/// Example:
///
/// Current DP
///
/// 1 1 1
/// 1 ? ?
/// 1 ? ?
///
/// Compute dp[1][1]
///
/// Top = 1
/// Left = 1
///
/// Answer
///
/// 1 + 1 = 2
///
/// Grid becomes
///
/// 1 1 1
/// 1 2 ?
/// 1 ? ?
///
/// Next cell
///
/// Top = 1
///
/// Left = 2
///
/// Answer
///
/// 3
///
/// Grid
///
/// 1 1 1
/// 1 2 3
/// 1 ? ?
///
/// ---------------------------------------------------------
/// Obstacle Case
/// ---------------------------------------------------------
///
/// else {
///
///     dp[i][j] = 0;
///
/// }
///
/// Obstacle means
///
/// Robot cannot stand here.
///
/// Therefore,
///
/// Number of ways = 0
///
/// ---------------------------------------------------------
/// Final Answer
/// ---------------------------------------------------------
///
/// return dp[row-1][col-1];
///
/// The bottom-right cell stores
///
/// the total number of unique paths.
///
/// Return it.
///
/// =========================================================
/// Dry Run
/// =========================================================
///
/// Input
///
/// 0 0 0
/// 0 1 0
/// 0 0 0
///
/// Step 1
///
/// 1 0 0
/// 0 0 0
/// 0 0 0
///
/// Initialize First Column
///
/// 1 0 0
/// 1 0 0
/// 1 0 0
///
/// Initialize First Row
///
/// 1 1 1
/// 1 0 0
/// 1 0 0
///
/// Compute Remaining Cells
///
/// Cell (1,1)
///
/// Obstacle
///
/// 1 1 1
/// 1 0 0
/// 1 0 0
///
/// Cell (1,2)
///
/// Top = 1
/// Left = 0
///
/// 1
///
/// 1 1 1
/// 1 0 1
/// 1 0 0
///
/// Cell (2,1)
///
/// Top = 0
/// Left = 1
///
/// 1
///
/// 1 1 1
/// 1 0 1
/// 1 1 0
///
/// Cell (2,2)
///
/// Top = 1
/// Left = 1
///
/// 2
///
/// Final DP
///
/// 1 1 1
/// 1 0 1
/// 1 1 2
///
/// Answer = 2
///
/// =========================================================
/// Time Complexity
/// =========================================================
///
/// O(row × col)
///
/// Every cell is visited exactly once.
///
/// =========================================================
/// Space Complexity
/// =========================================================
///
/// O(row × col)
///
/// DP table stores one value for every cell.
///
/// =========================================================
/// */