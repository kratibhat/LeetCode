package leetcode.dp.easy.twoorthreeD;

import java.util.Scanner;

/// /Ninja is planing this ‘N’ days-long training schedule. Each day, he can perform any one of these three activities. (Running, Fighting Practice or Learning New Moves). Each activity has some merit points on each day. As Ninja has to improve all his skills, he can’t do the same activity in two consecutive days. Can you help Ninja find out the maximum merit points Ninja can earn?
///
/// You are given a 2D array of size N*3 ‘POINTS’ with the points corresponding to each day and activity. Your task is to calculate the maximum number of merit points that Ninja can earn.
///
/// For Example
/// If the given ‘POINTS’ array is [[1,2,5], [3,1 ,1] ,[3,3,3] ],the answer will be 11 as 5 + 3 + 3.
/// Detailed explanation ( Input/output format, Notes, Images )
/// Constraints:
/// 1 <= T <= 10
/// 1 <= N <= 100000.
/// 1 <= values of POINTS arrays <= 100 .
///
/// Time limit: 1 sec
/// Sample Input 1:
/// 2
/// 3
/// 1 2 5
/// 3 1 1
/// 3 3 3
/// 3
/// 10 40 70
/// 20 50 80
/// 30 60 90
/// Sample Output 1:
/// 11
/// 210
/// Explanation of sample input 1:
/// For the first test case,
/// One of the answers can be:
/// On the first day, Ninja will learn new moves and earn 5 merit points.
/// On the second day, Ninja will do running and earn 3 merit points.
/// On the third day, Ninja will do fighting and earn 3 merit points.
/// The total merit point is 11 which is the maximum.
/// Hence, the answer is 11.
///
/// For the second test case:
/// One of the answers can be:
/// On the first day, Ninja will learn new moves and earn 70 merit points.
/// On the second day, Ninja will do fighting and earn 50 merit points.
/// On the third day, Ninja will learn new moves and earn 90 merit points.
/// The total merit point is 210 which is the maximum.
/// Hence, the answer is 210.
/// Sample Input 2:
/// 2
/// 3
/// 18 11 19
/// 4 13 7
/// 1 8 13
/// 2
/// 10 50 1
/// 5 100 11
/// Sample Output 2:
/// 45
/// 110
public class NinjaTraining {
    public static int ninjaTraining(int n, int[][] points) {
        // Base case: On day 0, the max points are just the points of each activity
        int[] dp = new int[3];
        dp[0] = points[0][0];
        dp[1] = points[0][1];
        dp[2] = points[0][2];

        // Iterate through day 1 to day n-1
        for (int i = 1; i < n; i++) {
            int[] nextDp = new int[3];

            // If we choose activity 0 today, we must look at max of activity 1 and 2 from yesterday
            nextDp[0] = points[i][0] + Math.max(dp[1], dp[2]);

            // If we choose activity 1 today, we must look at max of activity 0 and 2 from yesterday
            nextDp[1] = points[i][1] + Math.max(dp[0], dp[2]);

            // If we choose activity 2 today, we must look at max of activity 0 and 1 from yesterday
            nextDp[2] = points[i][2] + Math.max(dp[0], dp[1]);

            // Move forward to the next day
            dp = nextDp;
        }

        // The answer will be the maximum value obtained on the final day
        return Math.max(dp[0], Math.max(dp[1], dp[2]));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int t = sc.nextInt(); // Number of test cases
            while (t-- > 0) {
                int n = sc.nextInt(); // Number of days
                int[][] points = new int[n][3];
                for (int i = 0; i < n; i++) {
                    points[i][0] = sc.nextInt();
                    points[i][1] = sc.nextInt();
                    points[i][2] = sc.nextInt();
                }
                System.out.println(ninjaTraining(n, points));
            }
        }
        sc.close();
    }

}
