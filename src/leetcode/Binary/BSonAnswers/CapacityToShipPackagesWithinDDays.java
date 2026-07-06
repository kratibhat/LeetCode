package leetcode.Binary.BSonAnswers;
//A conveyor belt has packages that must be shipped from one port to another within days days.
//
//The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
//
//Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.
//
//
//
//Example 1:
//
//Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
//Output: 15
//Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
//1st day: 1, 2, 3, 4, 5
//2nd day: 6, 7
//3rd day: 8
//4th day: 9
//5th day: 10
//
//Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
//Example 2:
//
//Input: weights = [3,2,2,4,1,4], days = 3
//Output: 6
//Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
//1st day: 3, 2
//2nd day: 2, 4
//3rd day: 1, 4
//Example 3:
//
//Input: weights = [1,2,3,1,1], days = 4
//Output: 3
//Explanation:
//1st day: 1
//2nd day: 2
//3rd day: 3
//4th day: 1, 1
public class CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int days) {

        int low = 0;
        int high = 0;

        // Search range:
        // low = heaviest package
        // high = total weight of all packages
        for (int weight : weights) {
            low = Math.max(low, weight);
            high += weight;
        }

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (canShip(weights, days, mid)) {
                high = mid;          // capacity works, try smaller
            } else {
                low = mid + 1;       // capacity too small
            }
        }

        return low;
    }

    private boolean canShip(int[] weights, int days, int capacity) {

        int requiredDays = 1;
        int currentWeight = 0;

        for (int weight : weights) {

            // If adding this package exceeds capacity,
            // start a new day.
            if (currentWeight + weight > capacity) {
                requiredDays++;
                currentWeight = weight;
            } else {
                currentWeight += weight;
            }
        }

        return requiredDays <= days;
    }
    public static void main(String [] args){
        int days=5;
        int [] weights={1,2,3,4,5,6,7,8,9,10};
        CapacityToShipPackagesWithinDDays solution = new CapacityToShipPackagesWithinDDays();
        System.out.println(solution.shipWithinDays(weights, days));

    }

}

//
//class Solution {
//
//    public int shipWithinDays(int[] weights, int days) {
//
//        // ============================================================
//        // PROBLEM:
//        // We need to find the MINIMUM ship capacity such that
//        // all packages can be shipped within the given number of days.
//        //
//        // We cannot rearrange packages.
//        // Packages must be loaded in the SAME ORDER.
//        //
//        // ------------------------------------------------------------
//        // Binary Search on Answer
//        //
//        // We are NOT searching inside the array.
//        // We are searching for the answer (ship capacity).
//        //
//        // Why Binary Search?
//        //
//        // Suppose:
//        //
//        // Capacity = 10 -> Cannot ship within given days ❌
//        // Capacity = 11 -> Cannot ship ❌
//        // Capacity = 12 -> Cannot ship ❌
//        // Capacity = 13 -> Can ship ✅
//        // Capacity = 14 -> Can ship ✅
//        // Capacity = 15 -> Can ship ✅
//        //
//        // Notice the monotonic pattern:
//        //
//        // ❌ ❌ ❌ ✅ ✅ ✅
//        //
//        // Once a capacity works,
//        // every larger capacity will also work because
//        // a larger ship can always carry at least as much.
//        //
//        // Binary Search finds the FIRST TRUE.
//        // ============================================================
//
//        int low = 0;
//        int high = 0;
//
//        // ============================================================
//        // Determine the Binary Search range.
//        //
//        // LOWEST possible capacity:
//        //
//        // Ship must be able to carry the heaviest package.
//        //
//        // Example:
//        // weights = [3,5,9,2]
//        //
//        // Capacity = 8 ?
//        // Impossible because package 9 alone cannot fit.
//        //
//        // Therefore:
//        //
//        // low = maximum element
//        //
//        //
//        // HIGHEST possible capacity:
//        //
//        // If ship capacity equals the sum of all weights,
//        // everything fits in ONE DAY.
//        //
//        // Example:
//        // weights = [1,2,3,4]
//        //
//        // Capacity = 10
//        //
//        // Ship carries every package together.
//        //
//        // Therefore:
//        //
//        // high = total weight
//        // ============================================================
//
//        for (int weight : weights) {
//
//            // Find the heaviest package
//            low = Math.max(low, weight);
//
//            // Compute total weight
//            high += weight;
//        }
//
//        // ============================================================
//        // Binary Search starts.
//        //
//        // Search between:
//        //
//        // low  = max(weights)
//        // high = sum(weights)
//        // ============================================================
//
//        while (low < high) {
//
//            // Middle capacity to test.
//            int mid = low + (high - low) / 2;
//
//            // ========================================================
//            // Ask the helper function:
//            //
//            // If ship capacity = mid,
//            //
//            // Can we ship ALL packages within 'days' days?
//            //
//            // Returns:
//            // true  -> capacity works
//            // false -> capacity too small
//            // ========================================================
//
//            if (canShip(weights, days, mid)) {
//
//                // ====================================================
//                // Current capacity works.
//                //
//                // Example:
//                //
//                // Capacity = 15
//                //
//                // Ships everything in 5 days.
//                //
//                // But maybe
//                //
//                // Capacity = 14
//                // Capacity = 13
//                //
//                // also works.
//                //
//                // Since we need MINIMUM capacity,
//                // search LEFT HALF.
//                // ====================================================
//
//                high = mid;
//
//            } else {
//
//                // ====================================================
//                // Capacity is too small.
//                //
//                // Example:
//                //
//                // Capacity = 9
//                //
//                // Needs 8 days.
//                //
//                // Required = 5 days.
//                //
//                // Capacity failed.
//                //
//                // Even smaller capacities will also fail.
//                //
//                // Therefore search RIGHT HALF.
//                // ====================================================
//
//                low = mid + 1;
//            }
//        }
//
//        // ============================================================
//        // Binary Search ends when
//        //
//        // low == high
//        //
//        // This is the minimum capacity that works.
//        // ============================================================
//
//        return low;
//    }
//
//    private boolean canShip(int[] weights, int days, int capacity) {
//
//        // ============================================================
//        // This helper function SIMULATES loading packages.
//        //
//        // It answers one question:
//        //
//        // If ship capacity = capacity,
//        //
//        // How many days are required?
//        //
//        // If requiredDays <= givenDays
//        //
//        // return true
//        //
//        // else
//        //
//        // return false
//        // ============================================================
//
//        // We always start shipping on Day 1.
//        int requiredDays = 1;
//
//        // Current weight loaded on today's ship.
//        int currentWeight = 0;
//
//        // ============================================================
//        // Traverse packages one by one.
//        //
//        // IMPORTANT:
//        //
//        // Packages CANNOT be rearranged.
//        //
//        // Must load exactly in the given order.
//        // ============================================================
//
//        for (int weight : weights) {
//
//            // ========================================================
//            // Check if current package fits in today's ship.
//            //
//            // Example:
//            //
//            // Capacity = 10
//            //
//            // Current load = 7
//            //
//            // Next package = 5
//            //
//            // 7 + 5 = 12
//            //
//            // Ship exceeds capacity.
//            //
//            // Cannot load this package today.
//            // ========================================================
//
//            if (currentWeight + weight > capacity) {
//
//                // ====================================================
//                // Start a NEW DAY.
//                //
//                // Increase number of shipping days.
//                // ====================================================
//
//                requiredDays++;
//
//                // ====================================================
//                // Today's ship starts with this package.
//                //
//                // Why NOT currentWeight = 0 ?
//                //
//                // Because this package still needs shipping.
//                //
//                // Example:
//                //
//                // Ship currently has:
//                //
//                // 7
//                //
//                // Next package:
//                //
//                // 5
//                //
//                // Doesn't fit.
//                //
//                // Tomorrow's ship immediately carries 5.
//                // ====================================================
//
//                currentWeight = weight;
//
//            } else {
//
//                // ====================================================
//                // Package fits.
//                //
//                // Simply add it to today's ship.
//                //
//                // Example:
//                //
//                // Current load = 6
//                //
//                // Next package = 3
//                //
//                // Capacity = 10
//                //
//                // 6 + 3 = 9
//                //
//                // Still fits.
//                // ====================================================
//
//                currentWeight += weight;
//            }
//        }
//
//        // ============================================================
//        // After simulation,
//        // compare required days with allowed days.
//        //
//        // Example:
//        //
//        // Allowed = 5
//        //
//        // Simulation used = 4
//        //
//        // Great!
//        //
//        // Capacity works.
//        //
//        // Example:
//        //
//        // Allowed = 5
//        //
//        // Simulation used = 7
//        //
//        // Capacity too small.
//        // ============================================================
//
//        return requiredDays <= days;
//    }
//}
