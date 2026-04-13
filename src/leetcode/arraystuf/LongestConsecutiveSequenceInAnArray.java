package leetcode.arraystuf;

import java.util.HashSet;
import java.util.Set;

//Given an array nums of n integers.
//
//Return the length of the longest sequence of consecutive integers.
// The integers in this sequence can appear in any order.
public class LongestConsecutiveSequenceInAnArray {
    public static void main(String[] args) {
        LongestConsecutiveSequenceInAnArray solution = new LongestConsecutiveSequenceInAnArray();
        int[] nums = {100, 4, 200, 1, 3, 2};
        int result = solution.longestConsecutive(nums);
        System.out.println("Longest consecutive sequence length: " + result); // Output: 4
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longestStreak = 0;

        for (int num : set) {
            // Logic: Only start a streak if 'num' is the start of a sequence
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // Count how many consecutive numbers follow
                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
