package leetcode.array.sum;

import java.util.Arrays;

//Given an integer array nums of length n and an integer target,
// find three integers in nums such that the sum is closest to target.
//
//Return the sum of the three integers.
//
//You may assume that each input would have exactly one solution.
//
//
//
//Example 1:
//
//Input: nums = [-1,2,1,-4], target = 1
//Output: 2
//Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
public class ThreeSumClosest {
    public  int threeSumClosest(int[] arr, int target) {

        // Sort the elements
        Arrays.sort(arr);
        int resultSum = arr[0] + arr[1] + arr[2];
        int minDifference = Integer.MAX_VALUE;

        // Now fix the first element and find the other two elements
        for (int i = 0; i < arr.length - 2; i++) {
            // Find other two elements using Two Sum approach
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (sum == target)
                    return target;
                if (sum < target)
                    left++;
                else
                    right--;
//
                int diffToTarget = Math.abs(sum - target);
                if (diffToTarget < minDifference) {
                    // update the result sum
                    resultSum = sum;
                    minDifference = diffToTarget;
                }
            }
        }
        return resultSum;
    }

    public static void main(String[] args) {
        ThreeSumClosest solution = new ThreeSumClosest();
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        int result = solution.threeSumClosest(nums, target);
        System.out.println("Closest Sum: " + result); // Output: 2
    }
}
//Here is the same explanation without $ symbols and written point-wise for clarity:
//
//In mathematics, the distance between two numbers a and b is calculated using the formula |a − b|.
//
//The absolute value is important because distance cannot be negative.
//
//If we remove the absolute value, the calculation may give incorrect results when comparing distances.
//
//Scenario:
//
//Target value = 10
//
//Sum A = 8
//
//Difference = 10 − 8 = 2
//
//Sum B = 13
//
//Difference = 10 − 13 = −3
//
//Problem without absolute value:
//
//If we directly compare the raw differences (2 and −3),
//
//The value −3 appears smaller than 2,
//
//This may cause the code to incorrectly assume that 13 is closer to 10.