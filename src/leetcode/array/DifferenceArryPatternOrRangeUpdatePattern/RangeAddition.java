package leetcode.array.DifferenceArryPatternOrRangeUpdatePattern;
//given an integer length and an array updates where updates[i] = [starti, endi, inci], increments each element from starti to endi (inclusive) with inci and returns the modified array after all updates were applied.
//Example 1:
//
//Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
//Output: [-2,0,3,3,3]
//Explanation: Initial array = [0,0,0,0,0]
//After applying [1,3,2], the array becomes [0,2,2,2,0]
//After applying [2,4,3], the array becomes [0,2,5,5,3]
//After applying [0,2,-2], the array becomes [-2,0,3,5,3]
//Example 2:
//
//Input: length = 10, updates = [[2,4,6],[5,6,8],[1,9,2],[0,3,1]]
//Output: [1,3,7,9,8,10,10,2,2,2]
//Explanation: Initial array = [0,0,0,0,0,0,0,0,0,0]
//After applying [2,4,6], the array becomes [0,0,6,6,6,0,0,0,0,0]
//After applying [5,6,8], the array becomes [0,0,6,6,6,8,8,0,0,0]
//After applying [1,9,2], the array becomes [0,2,8,8,8,10,10,2,2,2]
//After applying [0,3,1], the array becomes [1,3,9,9,8,10,10,2,2,2]       
public class RangeAddition {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] result = new int[length];
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int increment = update[2];
            result[start] += increment;
            if (end + 1 < length) {
                result[end + 1] -= increment;
            }
        }
        for (int i = 1; i < length; i++) {
            result[i] += result[i - 1];
        }
        return result;
    }
     public static void main(String[] args) {
         RangeAddition solution = new RangeAddition();
         int length = 5;
         int[][] updates = {{1, 3, 2}, {2, 4, 3}, {0, 2, -2}};
         int[] modifiedArray = solution.getModifiedArray(length, updates);
         System.out.print("Modified Array: ");
         for (int num : modifiedArray) {
             System.out.print(num + " ");
         }
     }
}
