package BasicCoding.IBM.Hackerrank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountTriplets {
    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        long totalTriplets = 0;

        // Maps to track frequencies of elements before and after the current element
        Map<Long, Long> leftMap = new HashMap<>();
        Map<Long, Long> rightMap = new HashMap<>();

        // Step 1: Pre-populate rightMap with the frequencies of all elements in the array
        for (long num : arr) {
            rightMap.put(num, rightMap.getOrDefault(num, 0L) + 1L);
        }

        // Step 2: Iterate through each element acting as the potential middle element
        for (long mid : arr) {
            // Evict the current element from the right side pool
            rightMap.put(mid, rightMap.get(mid) - 1L);

            // Check if 'mid' can validly form a geometric triplet center point
            if (mid % r == 0) {
                long prev = mid / r;
                long next = mid * r;

                // If both required neighbors exist in their respective pools, calculate combinations
                if (leftMap.containsKey(prev) && rightMap.containsKey(next)) {
                    totalTriplets += leftMap.get(prev) * rightMap.get(next);
                }
            }

            // Add the current element to the left side pool for future center point checks
            leftMap.put(mid, leftMap.getOrDefault(mid, 0L) + 1L);
        }

        return totalTriplets;
    }

    public static void main(String[] args) {
        List<Long> arr = List.of(1L, 2L, 2L, 4L);
        long r = 2;
        long result = countTriplets(arr, r);
        System.out.println("Total Triplets: " + result); // Output: Total Triplets: 2
    }

}
