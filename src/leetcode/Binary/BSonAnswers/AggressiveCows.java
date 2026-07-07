package leetcode.Binary.BSonAnswers;
import java.util.*;
//Given an integer array arr[], which denotes the positions of stalls. All the positions are distinct. There are k aggressive cows.
//
//Assign the cows to the stalls such that the minimum distance between any two cows is maximized.
//
//Examples:
//
//Input: arr[] = [1, 2, 4, 8, 9], k = 3
//Output: 3
//Explanation: The first cow can be placed at arr[0], the second at arr[2], and the third at arr[3]. The minimum distance between any two cows is 3 (between arr[0] and arr[2]), which is the maximum possible among all valid arrangements.
//Input: arr[] = [10, 1, 2, 7, 5], k = 3
//Output: 4
//Explanation: The first cow can be placed at arr[0], the second at arr[1], and the third at arr[4]. In this arrangement, the minimum distance between any two cows is 4 (between arr[1] and arr[4]), which is the maximum possible among all valid arrangements.
public class AggressiveCows {
    public int aggressiveCows(int[] arr, int k) {
//same as magentic forc between balls

        // Step 1: Sort stall positions
        Arrays.sort(arr);

        // Step 2: Binary Search range
        int low = 1;
        int high = arr[arr.length - 1] - arr[0];

        while (low < high) {

            // Take upper mid because we're searching for the MAXIMUM
            int mid = low + (high - low + 1) / 2;

            if (canPlace(arr, k, mid)) {

                // Distance works.
                // Try an even larger minimum distance.
                low = mid;

            } else {

                // Distance too large.
                // Try smaller distances.
                high = mid - 1;
            }
        }

        return low;
    }

    private boolean canPlace(int[] arr, int cows, int distance) {

        // First cow always goes into first stall.
        int placed = 1;

        int lastPosition = arr[0];

        for (int i = 1; i < arr.length; i++) {

            // Can we place another cow here?
            if (arr[i] - lastPosition >= distance) {

                placed++;
                lastPosition = arr[i];

                // All cows placed successfully.
                if (placed == cows) {
                    return true;
                }
            }
        }

        return false;
    }
    public static void main(String []args)
    {
       int k=3;
       int [] arr={1, 2, 4, 8, 9};
       int m=3;
       int[] pos={1,2,3,4,7};
       int m1=2;
       int[]pos1={5,4,3,2,1,1000000000};

       AggressiveCows solution = new AggressiveCows();
       System.out.println(solution.aggressiveCows(pos, m));
       System.out.println(solution.aggressiveCows(pos1, m1));
       System.out.println(solution.aggressiveCows(arr, k));
    }
    public int aggressiveCowsBeginnerFriendly(int[] arr, int k) {

        // Step 1: Sort the stall positions
        Arrays.sort(arr);

        // Step 2: Minimum possible distance
        int low = 1;

        // Step 3: Maximum possible distance
        int high = arr[arr.length - 1] - arr[0];

        // Stores the best (maximum) valid distance found so far
        int answer = 0;

        // Binary Search on the answer
        while (low <= high) {

            int mid = low + (high - low) / 2;

            // Can we place all cows with at least 'mid' distance?
            if (canPlace(arr, k, mid)) {

                // Yes, this distance is possible
                answer = mid;

                // Try to find an even larger minimum distance
                low = mid + 1;

            } else {

                // Distance is too large
                // Try smaller distances
                high = mid - 1;
            }
        }

        return answer;
    }
}
