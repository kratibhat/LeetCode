package leetcode.array;
//You are given a 0-indexed 2D integer matrix grid of size n * n with values in the range [1, n2]. Each integer appears exactly once except a which appears twice and b which is missing. The task is to find the repeating and missing numbers a and b.
//
//Return a 0-indexed integer array ans of size 2 where ans[0] equals to a and ans[1] equals to b.
//
//
//
//Example 1:
//
//Input: grid = [[1,3],[2,2]]
//Output: [2,4]
//Explanation: Number 2 is repeated and number 4 is missing so the answer is [2,4].
public class FindMissingandRepeatedValues {
    public int[] findMissingAndRepeatedValues(int[][] grid) {

        int n = grid.length;
        int size = n * n;

        int[] freq = new int[size + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                freq[grid[i][j]]++;
            }
        }

        int r = 0;
        int m = 0;

        for (int i = 1; i <= size; i++) {
            if (freq[i] == 2) {
                r = i;
            } else if (freq[i] == 0) {
                m = i;
            }
        }

        return new int[]{r, m};
    }
    public static void main(String []args)
    {
        FindMissingandRepeatedValues solution = new FindMissingandRepeatedValues();
        int[][] grid = {{1, 3}, {2, 2}};
        int[] result = solution.findMissingAndRepeatedValues(grid);
        System.out.println("Repeating number: " + result[0]);
        System.out.println("Missing number: " + result[1]);
    }
}
