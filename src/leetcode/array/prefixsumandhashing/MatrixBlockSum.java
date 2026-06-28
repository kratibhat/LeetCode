package leetcode.array.prefixsumandhashing;

import java.util.Arrays;

public class MatrixBlockSum {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        // Step 1: Precompute the 2D Prefix Sum Matrix
        int[][] pref = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pref[i + 1][j + 1] = mat[i][j]
                        + pref[i][j + 1]
                        + pref[i + 1][j]
                        - pref[i][j];
            }
        }

        // Step 2: Use the prefix sum to calculate the block sums
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Find the boundary box coordinates while clamping to matrix limits
                int r1 = Math.max(0, i - k);
                int c1 = Math.max(0, j - k);
                int r2 = Math.min(m - 1, i + k);
                int c2 = Math.min(n - 1, j + k);

                // Apply the 2D Inclusion-Exclusion Formula (adjusted for 1-based indexing in pref)
                ans[i][j] = pref[r2 + 1][c2 + 1]
                        - pref[r1][c2 + 1]
                        - pref[r2 + 1][c1]
                        + pref[r1][c1];
            }
        }

        return ans;
    }
    public static void main(String [] args){
        MatrixBlockSum matrixSum=new MatrixBlockSum();
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int k = 1;
        int[][] result = matrixSum.matrixBlockSum(mat, k);
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}
