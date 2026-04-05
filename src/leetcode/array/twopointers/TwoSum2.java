package leetcode.array.twopointers;
//Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
//
//Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
//
//The tests are generated such that there is exactly one solution. You may not use the same element twice.
//
//Your solution must use only constant extra space.l
public class TwoSum2 {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1}; // Return 1-indexed positions
            } else if (sum < target) {
                left++; // Move left pointer to the right
            } else {
                right--; // Move right pointer to the left
            }
        }

        return new int[]{-1, -1}; // This line should never be reached due to problem constraints
    }
    public static void main(String[] args) {
        TwoSum2 solution = new TwoSum2();
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum(numbers, target);
        System.out.println("Indices of the two numbers: [" + result[0] + ", " + result[1] + "]");
    }
}
