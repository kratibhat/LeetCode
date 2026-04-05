package leetcode.array.Contributiontech;
//Sum of Subarray Minimums
//Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.
//
//
//
//Example 1:
//
//Input: arr = [3,1,2,4]
//Output: 17
//Explanation:
//Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
//Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
//Sum is 17.
//Example 2:
//
//Input: arr = [11,81,94,43,3]
//Output: 444
//
//
//Constraints:
//
//1 <= arr.length <= 3 * 104
//1 <= arr[i] <= 3 * 104
public class SumOfSubArrayMinimums {
    int mod=(int)1e9+7;
    public  int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n+1];
        int[] right = new int[n+1];
        int[] sums = new int[n+1];
        for(int i = 0; i < n ; ++i)
            right[i+1] = arr[i];

        int res = 0;
        for (int i = 1; i < n+1; i++) {
            int cur = right[i];
            int j = i - 1;
            while (right[j] >= cur)
                j = left[j];

            left[i] = j;
            sums[i] = sums[j] + cur * (i - j);
            res = (res + sums[i]) % mod;
        }
        return res;
    }

    public static void main(String[] args) {
        SumOfSubArrayMinimums solution = new SumOfSubArrayMinimums();
        int[] arr1 = {3, 1, 2, 4};
        System.out.println("Sum of subarray minimums: " + solution.sumSubarrayMins(arr1)); // Output: 17

        int[] arr2 = {11, 81, 94, 43, 3};
        System.out.println("Sum of subarray minimums: " + solution.sumSubarrayMins(arr2)); // Output: 444
    }
}
