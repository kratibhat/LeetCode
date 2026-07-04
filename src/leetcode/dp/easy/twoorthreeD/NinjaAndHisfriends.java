package leetcode.dp.easy.twoorthreeD;
/// /Problem Statement:  We are given an ‘N*M’ matrix. Every cell of the matrix has some
//chocolates on it, mat[i][j] gives us the number of chocolates. We have two friends ‘Alice’
// and ‘Bob’. initially, Alice is standing on the cell(0,0) and Bob is standing on the cell(0, M-1).
// Both of them can move only to the cells below them in these three directions: to the bottom
// cell (↓), to the bottom-right cell(↘), or to the bottom-left cell(↙).
// When Alica and Bob visit a cell, they take all the chocolates from that cell with them.
// It can happen that they visit the same cell, in that case, the chocolates need to be considered
// only once. They cannot go out of the boundary of the given matrix, we need to return the maximum
// number of chocolates that Bob and Alice can together collect.
    /// /Example 1:
    /// Input: ‘R’ = 3, ‘C’ = 4
    /// ‘GRID’ = [[2, 3, 1, 2], [3, 4, 2, 2], [5, 6, 3, 5]]
    /// Output: 21
    ///
    /// Example 2:
    /// Input: ‘R’ = 2, ‘C’ = 3
    /// ‘GRID’ = [[4, 1, 2], [7, 3, 5]]
    /// Output: 22
    import java.util.*;
public class NinjaAndHisfriends {

/// ////SAME AS CHERRY PICK2
// Class to solve Ninja and his friends using space optimization

    int maximumChocolates(int n, int m, int[][] grid) {
        // Next row dp array
        int[][] next = new int[m][m];
        // Current row dp array
        int[][] curr = new int[m][m];

        // Base case: last row
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                if (j1 == j2) next[j1][j2] = grid[n - 1][j1];
                else next[j1][j2] = grid[n - 1][j1] + grid[n - 1][j2];
            }
        }

        // Fill DP table bottom-up
        for (int i = n - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    int maxi = (int) (-1e9);
                    int currChoco = (j1 == j2) ? grid[i][j1]
                            : grid[i][j1] + grid[i][j2];
                    // Try all 9 moves
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int newJ1 = j1 + dj1;
                            int newJ2 = j2 + dj2;
                            if (newJ1 >= 0 && newJ1 < m &&
                                    newJ2 >= 0 && newJ2 < m) {
                                maxi = Math.max(maxi, currChoco +
                                        next[newJ1][newJ2]);
                            } else {
                                maxi = Math.max(maxi, (int) (-1e9));
                            }
                        }
                    }
                    curr[j1][j2] = maxi;
                }
            }
            // Move current row to next row
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    next[j1][j2] = curr[j1][j2];
                }
            }
        }
        // Answer is starting position
        return next[0][m - 1];
    }

// Driver class

    public static void main(String[] args) {
        int[][] grid = {
                {2, 3, 1, 2},
                {3, 4, 2, 2},
                {5, 6, 3, 5}
        };
        int[][] grid2 = {
                {4,1,2},
                {7,3,5}
        };
        int n2= grid2.length, m2 = grid2[0].length;
        int n = grid.length, m = grid[0].length;
        NinjaAndHisfriends obj = new NinjaAndHisfriends();
        System.out.println(obj.maximumChocolates(n, m, grid));
        System.out.println(obj.maximumChocolates(n2, m2, grid2));
    }
}
