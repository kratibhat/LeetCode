package leetcode.Binary;
//Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
//
//You must write an algorithm with O(log n) runtime complexity.
//
//
//
//Example 1:
//
//Input: nums = [-1,0,3,5,9,12], target = 9
//Output: 4
//Explanation: 9 exists in nums and its index is 4
//Example 2:
//
//Input: nums = [-1,0,3,5,9,12], target = 2
//Output: -1
//Explanation: 2 does not exist in nums so return -1
public class BinarySearch {
    public int search(int[] nums, int target) {
        int n=nums.length;
        int low=0;
        int high=n-1;
        while(low<=high)
        {
            int mid=(low+high)/2;
            if(nums[mid]==target) return mid;
            if(nums[mid] < target)
            {
                low=mid+1;
            }
            else
            {
                high=mid-1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        BinarySearch solution = new BinarySearch();
        int[] nums1 = {-1,0,3,5,9,12};
        int target1 = 9;
        int result1 = solution.search(nums1, target1);
        System.out.println("Index of " + target1 + ": " + result1); // Output: 4

        int[] nums2 = {5};
        int target2 = 5;
        int result2 = solution.search(nums2, target2);
        System.out.println("Index of " + target2 + ": " + result2); // Output: -1
    }
}
