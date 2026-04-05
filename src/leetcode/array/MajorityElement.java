package leetcode.array;
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
}
