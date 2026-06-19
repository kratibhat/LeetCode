package BasicCoding.IBM.Hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class IntervalSelection {
    public static int intervalSelection(List<List<Integer>> intervals) {
        // 1. Convert List<List<Integer>> to primitive int[][] for maximum execution speed
        int n = intervals.size();
        int[][] intv = new int[n][2];//since eah row has 2 elements, start and end
        for (int i = 0; i < n; i++) {
            intv[i][0] = intervals.get(i).get(0);
            intv[i][1] = intervals.get(i).get(1);
        }

        // 2. Your optimal end-time sort
        Arrays.sort(intv, Comparator.comparingInt((int[] a) -> a[1]));

        int count = 0;
        int[][] busy = new int[2][2];

        // Safely initialize tracking end boundaries to -1 (assuming points >= 1)
        // If intervals can have 0 or negative bounds, use Integer.MIN_VALUE
        busy[0][1] = -1;
        busy[1][1] = -1;

        // 3. Your lightning-fast linear tracking engine
        for (int[] x : intv) {
            if (x[0] > busy[1][1]) {
                count++;
                busy[1] = x;
            } else if (x[0] > busy[0][1]) {
                count++;
                busy[0] = x;
                if (x[1] > busy[1][1]) {
                    int[] temp = busy[0];
                    busy[0] = busy[1];
                    busy[1] = temp;
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        List<List<Integer>> intervals = new ArrayList<>();
        intervals.add(Arrays.asList(1, 3));
        intervals.add(Arrays.asList(2, 5));
        intervals.add(Arrays.asList(4, 6));
        intervals.add(Arrays.asList(7, 8));
        intervals.add(Arrays.asList(5, 9));

        int result = intervalSelection(intervals);
        System.out.println("Maximum number of non-overlapping intervals: " + result);
    }
}
