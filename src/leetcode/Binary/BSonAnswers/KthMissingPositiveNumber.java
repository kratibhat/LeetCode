package leetcode.Binary.BSonAnswers;
//Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
//
//Return the kth positive integer that is missing from this array.
//
//
//
//Example 1:
//
//Input: arr = [2,3,4,7,11], k = 5
//Output: 9
//Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
//Example 2:
//
//Input: arr = [1,2,3,4], k = 2
//Output: 6
//Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
public class KthMissingPositiveNumber {
    public int findKthPositive(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            // Number of missing elements before index 'mid'
            int missing = arr[mid] - (mid + 1);

            if (missing < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // At the end of the loop, left = right + 1
        // The kth missing number is guaranteed to be left + k

        /// Where does left + k come from?
        ///
        /// Suppose the answer is x.
        ///
        /// Before x,
        ///
        /// there are
        ///
        /// x-1
        ///
        /// numbers.
        ///
        /// Among those,
        ///
        /// left numbers exist in the array.
        ///
        /// So missing numbers before x are
        ///
        /// (x - 1) - left
        ///
        /// Since x is the k-th missing number,
        ///
        /// (x - 1) - left = k - 1
        ///
        /// Why k-1?
        ///
        /// Because we're counting the missing numbers before x. The number x itself is the k-th missing.
        ///
        /// Now solve it.
        ///
        /// x - 1 - left = k - 1
        ///
        /// Add 1 to both sides.
        ///
        /// x - left = k
        ///
        /// Therefore
        ///
        /// x = left + k
        ///
        /// Hence
        ///
        /// return left + k;
        return left + k;
    }
    public static void main(String[] args) {
        KthMissingPositiveNumber solution = new KthMissingPositiveNumber();
        int[] arr1 = {2, 3, 4, 7, 11};
        int k1 = 5;
        System.out.println(solution.findKthPositive(arr1, k1)); // Output: 9

        int[] arr2 = {1, 2, 3, 4};
        int k2 = 2;
        System.out.println(solution.findKthPositive(arr2, k2)); // Output: 6
    }

}
