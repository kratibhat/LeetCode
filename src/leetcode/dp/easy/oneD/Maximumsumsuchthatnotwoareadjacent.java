package leetcode.dp.easy.oneD;
//Given an array of positive numbers, find the maximum sum of a subsequence such that
// no two numbers in the subsequence should be adjacent in the array.
/// Given an array of positive numbers, find the maximum sum of a subsequence such that no two numbers in the subsequence should be adjacent in the array.
///
/// Examples:
///
/// Input: arr[] = {5, 5, 10, 100, 10, 5}
/// Output: 110
/// Explanation: Pick the subsequence {5, 100, 5}.
/// The sum is 110 and no two elements are adjacent. This is the highest possible sum.
///
/// Input: arr[] = {3, 2, 10}
/// Output: 13
/// Explanation: The subsequence is {3, 10}. This gives the highest possible sum = 13.
///
/// Input: arr[] = {3, 2, 5, 10, 7}
/// Output: 15
/// Explanation: Pick the subsequence {3, 5, 7}. The sum is 15.
public class Maximumsumsuchthatnotwoareadjacent {
    // Function to calculate the maximum Sum value using bottom-up DP
    static int maxSum(int[] arr) {
        int n = arr.length;

        // Create a dp array to store the maximum sum at each element
        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 0;
        dp[1] = arr[0];

        // Fill the dp array using the bottom-up approach
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(arr[i - 1] + dp[i - 2], dp[i - 1]);
        }
        return dp[n];
    }
    // Function to calculate the maximum Sum value
    static int maxSumOptmal(int[] arr) {
        int n = arr.length;

        if (n == 0)

            return 0;
        if (n == 1)
            return arr[0];

        // Set previous 2 values
        int secondLast = 0, last = arr[0];

        // Compute current value using previous
        // two values. The final current value
        // would be our result
        int res = 0;
        for (int i = 1; i < n; i++) {
            res = Math.max(arr[i] + secondLast, last);
            secondLast = last;
            last = res;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {6, 7, 1, 3, 8, 2, 4};
        System.out.println(maxSum(arr));
    }
}
