package leetcode.array.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
//
//
//
//Example 1:
//
//Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
//Example 2:
//
//Input: intervals = [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping.
//Example 3:
//
//Input: intervals = [[4,7],[1,4]]
//Output: [[1,7]]
//Explanation: Intervals [1,4] and [4,7] are considered overlapping.
//
//
//Constraints:
//
//1 <= intervals.length <= 104
//intervals[i].length == 2
public class MergeIntervals {
    int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
//logic is to sort the intervals based on start time and then iterate through them to merge overlapping intervals
        // Initialize a list to hold merged intervals
        // Sort the intervals based on the start time
        // Merge overlapping intervals
        // Sort intervals based on the start ti
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
//list<int[]> since arrray is there updation of merged is easy
        List<int[]> merged = new ArrayList<>();
        int[] currentInterval = intervals[0];

        merged.add(currentInterval);
        for (int[] index : intervals) {
            if (index[0] <= currentInterval[1]) {
                //overlap
                currentInterval[1] = Math.max(currentInterval[1], index[1]);

            } else {
                currentInterval = index;
                merged.add(currentInterval);

            }

        }
        //3️⃣ Breaking down new int[merged.size()][]
        //
        //merged.size() → the number of intervals we have.
        //
        //new int[merged.size()][] → creates a 2D array with merged.size() rows, but columns are not specified yet (each row can have a different length).

        return merged.toArray(new int[merged.size()][]);
    }
    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] mergedIntervals = solution.merge(intervals);

        System.out.println("Merged Intervals:");
        for (int[] interval : mergedIntervals) {
            System.out.println(Arrays.toString(interval));
        }
    }
//The key concept: References in Java
//
//In Java, arrays are reference types. This means that when you do:
//
//int[] currentInterval = intervals[0];
//merged.add(currentInterval);
//
//
//currentInterval is not a copy of the array — it is a reference (pointer) to the same array object in memory that intervals[0] points to.
//
//When you later modify currentInterval[1], you are modifying the same array object that is already inside merged.
//
//So, merged doesn’t hold a separate copy — it holds a reference to the same array. That’s why changes to currentInterval are immediately visible inside the merged list.
//
//Visual Example
//
//Suppose:
//
//intervals[0] = [1, 3];
//int[] currentInterval = intervals[0];
//merged.add(currentInterval);
//
//
//Memory-wise:
//
//intervals[0] ---> [1, 3]
//currentInterval ---|
//merged[0] --------|
//
//
//All three point to the same array object [1, 3].
//
//Then, when you do:
//
//currentInterval[1] = 6;
//
//
//It updates that array object:
//
//intervals[0] ---> [1, 6]
//currentInterval ---> [1, 6]
//merged[0] ---> [1, 6]
//
//
//All three “see” the updated value.
//
//✅ That’s why you don’t need to add a new array every time;
// you just keep updating currentInterval until there’s no overlap,
// and then you assign a new array when a new non-overlapping interval begins:
//
//currentInterval = index;
//merged.add(currentInterval);
//
//
//Now currentInterval points to a new array,
// and any further updates won’t affect previous intervals in merged.
}
