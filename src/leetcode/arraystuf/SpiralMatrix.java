package leetcode.arraystuf;

import java.util.ArrayList;
import java.util.List;

//Given an m x n matrix, return all elements of the matrix in spiral order.
//
//
//
//Example 1:
//
//
//Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
//Output: [1,2,3,6,9,8,7,4,5]
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int top = 0, left = 0;
        int bottom = matrix.length - 1;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // 1. Traverse Right (across the top row)
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            // 2. Traverse Down (down the right column)
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // 3. Traverse Left (across the bottom row)
            // Check if top <= bottom to handle non-square matrices
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // 4. Traverse Up (up the left column)
            // Check if left <= right to handle non-square matrices
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        SpiralMatrix solution = new SpiralMatrix();
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<Integer> result = solution.spiralOrder(matrix);
        System.out.println("Spiral Order: " + result); // Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]
    }
}
