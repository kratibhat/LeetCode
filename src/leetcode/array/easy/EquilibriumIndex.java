package leetcode.array.easy;
//Find Pivot Index
//Given an array of integers nums, calculate the pivot index of this array.
//
//The pivot index is the index where the sum of all the numbers
// strictly to the left of the index is equal to the sum of all
// the numbers strictly to the index's right.
//
//If the index is on the left edge of the array, then the left
// sum is 0 because there are no elements to the left. This also
// applies to the right edge of the array.
//
//Return the leftmost pivot index. If no such index exists, return -1.
//
//
//
//Example 1:
//
//Input: nums = [1,7,3,6,5,6]
//Output: 3
//Explanation:
//The pivot index is 3.
//Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
//Right sum = nums[4] + nums[5] = 5 + 6 = 11
//Example 2:
//
//Input: nums = [1,2,3]
//Output: -1
//Explanation:
//There is no index that satisfies the conditions in the problem statement.
//Example 3:
public class EquilibriumIndex {
    public int findEquilibriumIndex(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum -= nums[i]; // Now totalSum is the right sum for index i

            if (leftSum == totalSum) {
                return i; // Equilibrium index found
            }

            leftSum += nums[i];
        }

        return -1; // No equilibrium index found
    }
    public static void main(String[] args) {
        EquilibriumIndex solution = new EquilibriumIndex();
        int[] nums = {1, 3, 5, 2, 2};
        int index = solution.findEquilibriumIndex(nums);
        System.out.println("Equilibrium Index: " + index); // Output: Equilibrium Index: 2
    }
}
