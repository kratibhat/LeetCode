package leetcode.Binary.BSonAnswers;
import java.util.*;
// Painter's Partition problem (minimize the maximum amount of board painted by any painter)
// This is equivalent to split array largest sum (binary search + greedy check).
//Given an array arr[] and an integer k, where the array represents the boards and each element denotes the length of a board, and k painters are available to paint these boards. Each unit length of a board takes 1 unit of time to paint. Find the minimum time required to paint all the boards such that each painter paints only contiguous sections of the array. A painter can paint boards like [2, 3, 4], [1], or even no board, but cannot paint non-contiguous boards like [2, 4, 5].
//
//Examples:
//
//Input: arr[] = [5, 10, 30, 20, 15], k = 3
//Output: 35
//Explanation: The most optimal way will be: Painter 1 allocation : [5,10], Painter 2 allocation : [30], Painter 3 allocation : [20, 15], Job will be done when all painters finish i.e. at time = max(5 + 10, 30, 20 + 15) = 35
//
//Input: arr[] = [10, 20, 30, 40], k = 2
//Output: 60
//Explanation: The most optimal way to paint: Painter 1 allocation : [10, 20, 30], Painter 2 allocation : [40], Job will be complete at time = 60
public class PaintersPartition {
    //has part 2
    public long paintersPartition(int[] boards, int painters) {
        long low = 0, high = 0;
        for (int b : boards) {
            low = Math.max(low, b);
            high += b;
        }

        long ans = high;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canPaint(boards, painters, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean canPaint(int[] boards, int painters, long maxLoad) {
        int required = 1;
        long curr = 0;
        for (int b : boards) {
            if (curr + b <= maxLoad) {
                curr += b;
            } else {
                required++;
                curr = b;
                if (required > painters) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] boards = {10, 20, 30, 40};
        int []b2={5, 10, 30, 20, 15};
        int []b3={5, 5, 5, 5};
        int k3=2;
        int k2=3;
        int painters = 2;
        PaintersPartition sol = new PaintersPartition();
        System.out.println(sol.paintersPartition(boards, painters)); // expected 60
        System.out.println(sol.paintersPartition(b2, k2));
        System.out.println(sol.paintersPartition(b3, k3));

    }
    // by dividing the work among at most k painters
    static boolean isPossible(int maxTime, int[] arr, int k) {
        int painters = 1;
        int currSum = 0;

        for (int length : arr) {

            // if a board is longer than maxTime,
            // it's impossible to assign
            if (length > maxTime)
                return false;

            // if assigning this board exceeds maxTime,
            // give it to a new painter
            if (currSum + length > maxTime) {
                painters++;
                currSum = length;
            }

            // otherwise, continue adding to the current
            // painter's workload
            else {
                currSum += length;
            }
        }

        // return true if total painters used is
        // within the allowed k
        return painters <= k;
    }

    static int minTime(int[] arr, int k) {
        int low = Arrays.stream(arr).max().getAsInt();
        int high = Arrays.stream(arr).sum();
        int result = high;

        while (low <= high) {
            int mid = (low + high) / 2;

            // if this time allows us to paint
            // with k painters or fewer
            if (isPossible(mid, arr, k)) {
                result = mid;
                high = mid - 1;
            }

            // if not possible, we need to allow
            // more time
            else {
                low = mid + 1;
            }
        }

        return result;
    }
}

