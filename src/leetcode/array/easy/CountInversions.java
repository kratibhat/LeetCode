package leetcode.array.easy;
//Given an integer array arr[] of size n, find the inversion
// count in the array. Two array elements arr[i] and arr[j] form an
// \inversion if arr[i] > arr[j] and i < j.
//
//Note: Inversion Count for an array indicates that how far (or close)
// the array is from being sorted. If the array is already sorted,
// then the inversion count is 0, but if the array is sorted in reverse order,
// the inversion count is maximum.
//
//Examples:
//
//Input: arr[] = [4, 3, 2, 1]
//Output: 6
//Explanation:
public class CountInversions {
    public int countInversions(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CountInversions solution = new CountInversions();
        int[] nums = {2, 4, 1, 3, 5};
        int result = solution.countInversions(nums);
        System.out.println("Number of inversions: " + result); // Output: 3
    }
}
