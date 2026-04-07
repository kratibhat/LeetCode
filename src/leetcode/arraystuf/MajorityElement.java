package leetcode.arraystuf;
//Given an array nums of size n, return the majority element.
//
//The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
//
// Example 1:
//
//Input: nums = [3,2,3]
//Output: 3
//Example 2:
//
//Input: nums = [2,2,1,1,1,2,2]
//Output: 2
public class MajorityElement {
    public int majorityElement(int[] nums) {
        return helper(nums,0,nums[0]);
    }
    public int helper(int nums[], int si, int ref){
        if(si >= nums.length){
            return -1;
        }
        int count = 0;
        for(int i=si; i<nums.length; i++){
            if(nums[i] == ref){
                count ++;
            }
            else{
                count --;
            }

            if(count < 0){
                return helper(nums,i,nums[i]);
            }
        }
        return ref;
    }
    public int majorityElement1(int[] nums) {
        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            // When count reaches zero, we pick a new potential candidate
            if (count == 0) {
                candidate = num;
            }

            // If num is our candidate, they "gain a vote"
            // If not, they "lose a vote"
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
    public static void main(String[] args){
        MajorityElement solution = new MajorityElement();
        int[] nums1 = {3,2,3};
        int[] nums2 = {2,2,1,1,1,2,2};
        System.out.println("Majority Element in nums1: " + solution.majorityElement(nums1)); // Output: 3
        System.out.println("Majority Element in nums2: " + solution.majorityElement1(nums2)); // Output: 2
    }
}
