package leetcode.Binary;
//Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
//
//[4,5,6,7,0,1,2] if it was rotated 4 times.
//[0,1,2,4,5,6,7] if it was rotated 7 times.
//Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
//
//Given the sorted rotated array nums of unique elements, return the minimum element of this array.
//
//You must write an algorithm that runs in O(log n) time.
//
//
//
//Example 1:
//
//Input: nums = [3,4,5,1,2]
//Output: 1
//Explanation: The original array was [1,2,3,4,5] rotated 3 times.
//Example 2:
//
//Input: nums = [4,5,6,7,0,1,2]
//Output: 0
//Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
//Example 3:
//
//Input: nums = [11,13,15,17]
//Output: 11
//Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
//
public class FindMinimuminRotatedSortedArray {
    public int findMin(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return nums[low];
    }
    public static void main(String[] args) {
        FindMinimuminRotatedSortedArray solution = new FindMinimuminRotatedSortedArray();
        int[] nums1 = {3, 4, 5, 1, 2};
        int result1 = solution.findMin(nums1);
        System.out.println("Minimum in [3, 4, 5, 1, 2]: " + result1); // Output: 1

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int result2 = solution.findMin(nums2);
        System.out.println("Minimum in [4, 5, 6, 7, 0, 1, 2]: " + result2); // Output: 0

        int[] nums3 = {11, 13, 15, 17};
        int result3 = solution.findMin(nums3);
        System.out.println("Minimum in [11, 13, 15, 17]: " + result3); // Output: 11
    }
}
/*
=========================================
BINARY SEARCH LOOP CONDITIONS
=========================================

1. while (low <= high)

Use when:
- Searching for an exact element.
- Need to check every possible index.
- The last remaining element (low == high) must also be examined.

Examples:
- Binary Search
- Search Insert Position
- Search in Rotated Sorted Array
- First Occurrence
- Last Occurrence

Template:

while (low <= high) {
    int mid = low + (high - low) / 2;

    if (nums[mid] == target)
        return mid;

    // Move left or right
}

-----------------------------------------

2. while (low < high)

Use when:
- NOT searching for an exact value.
- Shrinking the search space until only ONE candidate remains.
- When low == high, that index is the answer.

Examples:
- Find Minimum in Rotated Sorted Array
- Find Peak Element (common solution)
- Lower/Upper bound variants (some implementations)

Template:

while (low < high) {
    int mid = low + (high - low) / 2;

    if (condition)
        low = mid + 1;
    else
        high = mid;   // or high = mid - 1 depending on the problem
}

return nums[low]; // or return low

=========================================
MEMORY TRICK
=========================================

Searching for a VALUE?
    -> while (low <= high)

Searching for a POSITION or narrowing to ONE answer?
    -> while (low < high)
*/
///Instead of thinking only "position", think:
///
/// Need to compare nums[mid] with a target? → while (low <= high)
/// Need to narrow the range until only one candidate remains? → while (low < high)