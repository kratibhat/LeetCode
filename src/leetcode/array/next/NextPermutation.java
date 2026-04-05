package leetcode.array.next;
/// /A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
///
/// For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
/// The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
///
/// For example, the next permutation of arr = [1,2,3] is [1,3,2].
/// Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
/// While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
/// Given an array of integers nums, find the next permutation of nums.
///
/// The replacement must be in place and use only constant extra memory.
///
///
///
/// Example 1:
///
/// Input: nums = [1,2,3]
/// Output: [1,3,2]
/// Example 2:
///
/// Input: nums = [3,2,1]
/// Output: [1,2,3]
/// Example 3:
///
/// Input: nums = [1,1,5]
/// Output: [1,5,1]
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // Find the first decreasing element from the end
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = n - 1;
            // Find the first element larger than nums[i]
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            // Swap elements at i and j
            swap(nums, i, j);
        }
        // Reverse the elements after index i
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
    public static void main(String[] args) {
        NextPermutation solution = new NextPermutation();
        int[] nums = {1, 2, 3};
        solution.nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
///*
//========================================
//NEXT PERMUTATION — STEP-BY-STEP LOGIC
//========================================
//
//----------------------------------------
//STEP 1: FIND THE PIVOT (First Decreasing Element)
//----------------------------------------
//• Traverse the array from right to left.
//• Find the first index i such that:
//      nums[i] < nums[i + 1]
//
//WHY THIS WORKS:
//• A suffix that is strictly decreasing is already the highest permutation.
//• We cannot increase the number by rearranging only that suffix.
//• The first drop (nums[i] < nums[i+1]) is the position where a larger permutation
//  becomes possible.
//
//----------------------------------------
//STEP 2: FIND THE SUCCESSOR (Smallest Larger Element)
//----------------------------------------
//• If a pivot is found at index i:
//• Search again from right to left for index j such that:
//      nums[j] > nums[i]
//
//WHY FROM THE RIGHT?
//• The suffix is in descending order.
//• The first element greater than the pivot is the smallest possible successor.
//• This guarantees the next permutation is just slightly larger.
//
//ACTION:
//• Swap nums[i] and nums[j].
//
//----------------------------------------
//STEP 3: REVERSE THE SUFFIX
//----------------------------------------
//• After swapping, the suffix (i+1 to end) is still in descending order.
//• To get the smallest lexicographical permutation:
//      Reverse the suffix.
//
//LOGIC:
//• Reversing a descending sequence produces ascending order.
//• This makes the permutation the immediate next one.
//
//----------------------------------------
//EXAMPLE TRACE
//----------------------------------------
//nums = [1, 5, 8, 4, 7, 6, 5, 3, 1]
//
//1) Find Pivot:
//   • From right, first drop occurs at 4 (index 3)
//   • Suffix: [7, 6, 5, 3, 1] (strictly decreasing)
//   • Pivot index i = 3
//
//2) Find Successor:
//   • Smallest element > 4 in suffix is 5 (index 6)
//   • j = 6
//
//3) Swap:
//   • Swap nums[3] and nums[6]
//   • Result: [1, 5, 8, 5, 7, 6, 4, 3, 1]
//
//4) Reverse Suffix (index 4 to end):
//   • Reverse [7, 6, 4, 3, 1]
//   • Result: [1, 5, 8, 5, 1, 3, 4, 6, 7]
//
//----------------------------------------
//SPECIAL CASE: NO PIVOT FOUND
//----------------------------------------
//• If no index i satisfies nums[i] < nums[i+1]:
//• The array is completely descending (e.g., [3, 2, 1])
//• This is the last permutation.
//
//ACTION:
//• Reverse the entire array.
//• Example: [3, 2, 1] → [1, 2, 3]
//
//----------------------------------------
//FINAL NOTES
//----------------------------------------
//• Time Complexity: O(n)
//• Space Complexity: O(1)
//• In-place modification
//• Produces the lexicographically next permutation
//========================================


//*/1. The "Peak" Observation (Where can I change things?)
//If you have a sequence like 5, 4, 3, 2, 1, you cannot make it any bigger. It is already at its maximum.
//
//The Rule: Any sequence that is strictly decreasing is already at its "last" permutation.
//
//The Conclusion: To make a number bigger, you must find the first place where it stops decreasing as you look from right to left. That is your Pivot.
//
//2. The "Smallest Increase" Strategy
//Once you find your Pivot (let's say it's a 3 in ...3, 7, 6, 5, 1), you know you need to replace that 3 with a larger number to make the total value grow.
//
//The Choice: You have 7, 6, 5, 1 to choose from.
//
//The Logic: To make the number the next (smallest possible increase) permutation, you shouldn't jump to the biggest number (7). You should pick the smallest number that is still larger than 3.
//
//In this case, that is 5. Replacing 3 with 5 is the smallest possible "step up" for that digit position.
//
//3. The "Reset" Logic
//After you swap the 3 and the 5, your suffix looks like ...5, [7, 6, 3, 1].
//
//The Problem: The suffix 7, 6, 3, 1 is still in descending order, which makes it a very "large" value.
//
//The Goal: You want the smallest possible permutation for the rest of the numbers.
//
//The Solution: The smallest version of any set of numbers is ascending order. Since the suffix is currently descending, the fastest way to make it ascending is to simply reverse it.
//
//How to recognize this problem in the future
//You should use this logic when the problem involves:
//
//Lexicographical Order: Any time you see "next largest," "lexicographically next," or "dictionary order."
//
//Rearranging to find a bound: Problems like "Find the smallest number larger than N using the same digits."
//
//Combinatorics without recursion: When you need to iterate through permutations but don't want to use a heavy backtracking/recursion depth.