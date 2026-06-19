package BasicCoding.IBM.Hackerrank;


import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FraudulentActivityNotifications {
    /*
     * Complete the 'activityNotifications' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. INTEGER_ARRAY expenditure
     * 2. INTEGER d
     */

    public static int activityNotifications(List<Integer> expenditure, int d) {
        int notifications = 0;

        // Count array since max expenditure value is strictly <= 200
        int[] freq = new int[201];

        // Step 1: Pre-populate the frequency array with the first 'd' tracking days
        for (int i = 0; i < d; i++) {
            freq[expenditure.get(i)]++;
        }

        // Step 2: Slide across the remaining transaction data stream
        for (int i = d; i < expenditure.size(); i++) {
            double median = getMedian(freq, d);

            // Send warning if current day's value is >= 2 * trailing median
            if (expenditure.get(i) >= 2 * median) {
                notifications++;
            }

            // Advance sliding window boundary elements in O(1) time
            freq[expenditure.get(i)]++;       // Include new day
            freq[expenditure.get(i - d)]--;   // Evict oldest day
        }

        return notifications;
    }

    // Accumulate frequencies to determine median position without re-sorting
    private static double getMedian(int[] freq, int d) {
        int count = 0;

        // Handle odd lookback windows
        if (d % 2 != 0) {
            int targetIndex = (d / 2) + 1;
            for (int i = 0; i < freq.length; i++) {
                count += freq[i];
                if (count >= targetIndex) {
                    return i;
                }
            }
        }
        // Handle even lookback windows (average of two center values)
        else {
            int target1 = d / 2;
            int target2 = target1 + 1;
            int m1 = -1, m2 = -1;

            for (int i = 0; i < freq.length; i++) {
                count += freq[i];
                if (m1 == -1 && count >= target1) {
                    m1 = i;
                }
                if (count >= target2) {
                    m2 = i;
                    return (m1 + m2) / 2.0;
                }
            }
        }
        return 0.0;
    }



    public static void main(String[] args) {


        int n = 9;
        int d = 5;
        List<Integer> expenditure = List.of(2, 3, 4, 2, 3, 6, 8, 4, 5);

        int result = FraudulentActivityNotifications.activityNotifications(expenditure, d);
        System.out.println(result);

    }
}


