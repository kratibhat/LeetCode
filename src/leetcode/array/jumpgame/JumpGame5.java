package leetcode.array.jumpgame;
//Given an array of integers arr and an integer d. In one step you can jump from index i to index:
//
//i + x where: i + x < arr.length and  0 < x <= d.
//i - x where: i - x >= 0 and  0 < x <= d.
//In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).
//
//You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.
//
//Notice that you can not jump outside of the array at any time.
//
//
//
//Example 1:
//
//
//Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
//Output: 4
//Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as shown.
//Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 > 9. You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 > 9.
//Similarly You cannot jump from index 3 to index 2 or index 1.
public class JumpGame5 {
    int[] dp;
    public int maxJumps1(int[] arr, int d) {
        ////10ms
        int max = 0;
        dp = new int[arr.length];
        for(int i = 0;i<arr.length;i++){
            max = Math.max(max,helper(arr,d,i));
        }

        return max;
    }

    private int helper(int[] arr,int d, int index){

        if(dp[index]>0){
            return dp[index];
        }

        int result = 1;
        //index - 1  ----- index - d
        for(int j = index - 1; j>= Math.max(index - d,0) && arr[index]>arr[j]; j--){
            result = Math.max(result, 1 + helper(arr,d,j));
        }

        //index + 1 ------- index + d
        for(int j = index + 1; j<= Math.min(index + d,arr.length-1) && arr[index]>arr[j] ; j++){
            result = Math.max(result, 1 + helper(arr,d,j));
        }

        dp[index] = result;
        return result;
    }
    int n;

    public int maxJumps(int[] arr, int d) {
        /// /8ms
        n = arr.length;
        int[] memo = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (memo[i] == 0) {
                int temp = maxFinder(i, arr, memo, d);
                max = Math.max(max, temp);
            }
        }
        return max;
    }

    public int maxFinder(int ind, int[] arr, int[] memo, int d) {
        if (memo[ind] != 0)
            return memo[ind];
        int max = 0;
        for (int i = ind + 1; i <= (ind + d) && i < n; i++) {
            if (arr[i] >= arr[ind])
                break;
            int temp = maxFinder(i, arr, memo, d);
            max = Math.max(max, temp);
        }
        for (int i = ind - 1; i >= (ind - d) && i >= 0; i--) {
            if (arr[i] >= arr[ind])
                break;
            int temp = maxFinder(i, arr, memo, d);
            max = Math.max(max, temp);
        }
        memo[ind] = max + 1;
        return memo[ind];
    }

    public static void main(String[] args) {
        JumpGame5 solution = new JumpGame5();
        int[] arr = {6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12};
        int d = 2;
        int result = solution.maxJumps(arr, d);
        System.out.println("Maximum Jumps: " + result); // Output: 4
    }
}
