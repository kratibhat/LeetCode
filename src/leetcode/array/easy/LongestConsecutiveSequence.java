package leetcode.array.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
//
//You must write an algorithm that runs in O(n) time.
//
//
//
//Example 1:
//
//Input: nums = [100,4,200,1,3,2]
//Output: 4
//Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
//Example 2:
//
//Input: nums = [0,3,7,2,5,8,4,6,0,1]
//Output: 9
public class LongestConsecutiveSequence {
    public int longestConsecutive1(int[] nums) { //22ms
        if(nums.length == 0) return 0;

        Arrays.sort(nums);
        int count = 1;
        int maxCount = 1;

        for(int i = 1; i < nums.length; i++)
        {
            if(nums[i] == nums[i-1] + 1) count++;
            else if (nums[i] != nums[i-1])
            {
                maxCount = Math.max(maxCount, count);
                count = 1;
            }
        }
        maxCount = Math.max(maxCount, count);
        System.gc();
        return maxCount;
    }

    public int longestConsecutive(int[] nums) { //31 ms
        HashSet<Integer> set = new HashSet<>();
        for(int x : nums) set.add(x);

        int max = 0;

        for(int x : set){
            if(!set.contains(x - 1)){
                int curr = x;
                int len = 1;

                while(set.contains(curr+1)){
                    curr++;
                    len++;
                }
                if(len > max) max = len;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        LongestConsecutiveSequence solution = new LongestConsecutiveSequence();
        int[] nums = {100, 4, 200, 1, 3, 2};
        int result = solution.longestConsecutive1(nums);
        System.out.println("Length of Longest Consecutive Sequence: " + result); // Output: 4
    }

}
