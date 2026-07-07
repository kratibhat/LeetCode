package leetcode.Binary.BSonAnswers;
//Problem Statement: You are given a sorted array ‘arr’ of length ‘n’,
// which contains positive integer positions of ‘n’ gas stations on the X-axis. \
// You are also given an integer ‘k’. You have to place 'k' new gas stations on the X-axis.
// You can place them anywhere on the non-negative side of the X-axis, even on non-integer
// positions. Let 'dist' be the maximum value of the distance between adjacent gas stations after
// adding k new gas stations. Find the minimum value of ‘dist’.
//
//Examples
//Example 1:
//Input Format: N = 5, arr[] = {1,2,3,4,5}, k = 4
//Result: 0.5
//Explanation: One of the possible ways to place 4 gas stations is {1,1.5,2,2.5,3,3.5,4,4.5,5}.
// Thus the maximum difference between adjacent gas stations is 0.5.
// Hence, the value of ‘dist’ is 0.5. It can be shown that there is no possible way to add
// 4 gas stations in such a way that the value of ‘dist’ is lower than this.
//
//Example 2:
//Input Format: N = 10, arr[] = {1,2,3,4,5,6,7,8,9,10}, k = 1
//Result: 1
//Explanation: One of the possible ways to place 1 gas station is {1,1.5,2,3,4,5,6,7,8,9,10}. Thus the maximum difference between adjacent gas stations is still 1. Hence, the value of ‘dist’ is 1. It can be shown that there is no possible way to add 1 gas station in such a way that the value of ‘dist’ is lower than this.
// LeetCode 774 - Minimize Max Distance to Gas Station
// Given positions of gas stations and K extra stations to add, minimize the maximum distance
// between adjacent stations after adding K stations. Return minimized maximum distance.
public class MinimizeMaxDistanceToGasStation {
    public double minimizeMaxDistance(int[] stations, int K) {
        double low = 0;
        double high = 0;
        for (int i = 1; i < stations.length; i++) {
            high = Math.max(high, stations[i] - stations[i - 1]);
        }

        double eps = 1e-6;
        while (high - low > eps) {
            double mid = (low + high) / 2.0;
            if (requiredStations(stations, mid) > K) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return high;
    }

    // Count how many stations are required so that every gap <= D
    private int requiredStations(int[] stations, double D) {
        int cnt = 0;
        for (int i = 1; i < stations.length; i++) {
            double gap = stations[i] - stations[i - 1];
            cnt += (int) Math.floor(gap / D) - 1 + 1; // simplified below
            // safer computation:
        }
        // The above line was a short attempt — replace with exact computation
        cnt = 0;
        for (int i = 1; i < stations.length; i++) {
            double gap = stations[i] - stations[i - 1];
            cnt += (int) Math.floor(gap / D);
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] stations = {1, 2, 3, 100};
        int []s2={1,2,3,4,5};
        int []s3={1,2,3,4,5,6,7,8,9,10};
        int k2=4;
        int k3=1;
        int K = 1;
        MinimizeMaxDistanceToGasStation sol = new MinimizeMaxDistanceToGasStation();
        System.out.println(sol.minimizeMaxDistance(stations, K));
        System.out.println(sol.minimizeMaxDistance(s2, k2));
        System.out.println(sol.minimizeMaxDistance(s3, k3));
    }
}

