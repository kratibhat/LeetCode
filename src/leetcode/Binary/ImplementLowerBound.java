package leetcode.Binary;
//Implement Lower Bound
//Difficulty: EasyAccuracy: 50.04%Submissions: 84K+Points: 2
//Given a sorted array arr[] (following 0-based indexing) and a number target, find the lower bound of the target in this given array. The lower bound of a number is defined as the smallest index in the sorted array where the element is greater than or equal to the given number.
//
//Note: If all the elements in the given array are smaller than the target, the lower bound will be the length of the array.
//
//Examples :
//
//Input:  arr[] = [2, 3, 7, 10, 11, 11, 25], target = 9
//Output: 3
//Explanation: 3 is the smallest index in arr[] where element (arr[3] = 10) is greater than or equal to 9.
public class ImplementLowerBound {
    static int lowerBound(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1;
        int ans = n; // Default if no element is >= target

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= target) {
                // Potential answer found, look for smaller index on the left
                ans = mid;
                high = mid - 1;
            } else {
                // Element is smaller than target, look on the right
                low = mid + 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr = {2, 3, 7, 10, 11, 11, 25};
        int target = 9;
        System.out.println(lowerBound(arr, target)); // Output: 3
    }
}
