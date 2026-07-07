package leetcode.Binary.BSonAnswers;

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

