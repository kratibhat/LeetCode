package BasicCoding.IBM.Hackerrank;

import java.util.*;
///*
//PROBLEM: Query Types Within a 600-Second Window
//
//You are given n query logs.
//
//Each log entry contains:
//
//1. timestamps[i]
//   - The time (in seconds) at which a query occurred.
//   - The array is sorted in non-decreasing order.
//
//2. queryTypes[i]
//   - The query type recorded at timestamps[i].
//
//A query type should be included in the result if:
//
//There exists at least one 600-second window in which that query type
//appears at least 'threshold' times.
//
//Return:
//- All query types that satisfy the condition.
//- The result must be sorted in increasing (lexicographical) order.
//- Return an empty array if none qualify.
//
//
//Example:
//
//threshold = 2
//
//timestamps = [100, 150, 200, 250, 700]
//
//queryTypes = ["Q1", "Q1", "Q1", "Q2", "Q1"]
//
//
//Explanation:
//
//Q1 occurs at:
//100, 150, 200, 700
//
//700 - 100 = 600
//
//Within this 600-second window, Q1 appears 4 times.
//
//Since 4 >= threshold (2), Q1 qualifies.
//
//
//Q2 occurs only once at:
//250
//
//Since 1 < threshold, Q2 does not qualify.
//
//
//Output:
//
//["Q1"]
//
//
//Constraints:
//
//1 <= n <= 2 * 10^5
//
//1 <= threshold <= 2 * 10^5
//
//1 <= queryTypes[i].length() <= 10
//
//0 <= timestamps[i] <= 10^9
//
//timestamps is sorted in non-decreasing order.
//
//
//Hint (Efficient Approach):
//
//1. Group timestamps by query type.
//2. For each query type, use a sliding window (two pointers).
//3. Check whether there are at least 'threshold' occurrences
//   within any 600-second interval.
//4. Add qualifying query types to the answer.
//5. Sort the final answer lexicographically.
//
//Time Complexity: O(n)
//Space Complexity: O(n)
//*/
public class FrequencyCount {
    public static List<String> getFrequentQueries(int threshold, List<Integer> timestamps, List<String> queryTypes) {
        Map<String, Integer> freqMap = new HashMap<>();
        Set<String> qualifiedQueries = new HashSet<>();

        int left = 0;
        int n = timestamps.size();

        for (int right = 0; right < n; right++) {
            String rightQuery = queryTypes.get(right);
            freqMap.put(rightQuery, freqMap.getOrDefault(rightQuery, 0) + 1);

            while (timestamps.get(right) - timestamps.get(left) > 600) {
                String leftQuery = queryTypes.get(left);
                freqMap.put(leftQuery, freqMap.get(leftQuery) - 1);
                if (freqMap.get(leftQuery) == 0) {
                    freqMap.remove(leftQuery);
                }
                left++;
            }

            if (freqMap.get(rightQuery) >= threshold) {
                qualifiedQueries.add(rightQuery);
            }
        }

        List<String> result = new ArrayList<>(qualifiedQueries);
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        // Sample input from the prompt:
        // Q1 occurs at: 100, 150, 200, 700  (700 - 100 = 600) -> appears 4 times -> qualifies for threshold = 2
        // Q2 occurs at: 250 -> does not qualify
        int threshold = 2;
        List<Integer> timestamps = Arrays.asList(100, 150, 200, 250, 700);
        List<String> queryTypes = Arrays.asList("Q1", "Q1", "Q1", "Q2", "Q1");

        List<String> result = getFrequentQueries(threshold, timestamps, queryTypes);
        System.out.println(result); // Expected output: [Q1]
    }
}

