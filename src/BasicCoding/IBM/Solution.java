package BasicCoding.IBM;

import java.util.*;

public class Solution {

    public static int activityNotifications(List<Integer> expenditure, int d) {
        int notifications = 0;
        
        // Frequency array to store counts of expenditures in the current trailing window
        int[] freq = new int[201];
        
        // Initialize the frequency array with the first 'd' days
        for (int i = 0; i < d; i++) {
            freq[expenditure.get(i)]++;
        }
        
        // Iterate through the rest of the days to check for notifications
        for (int i = d; i < expenditure.size(); i++) {
            double median = getMedian(freq, d);
            
            // Trigger warning if current day's spend >= 2 * median
            if (expenditure.get(i) >= 2 * median) {
                notifications++;
            }
            
            // Slide the window: Add new element, remove oldest element
            freq[expenditure.get(i)]++;
            freq[expenditure.get(i - d)]--;
        }
        
        return notifications;
    }

    // Helper method to find the median from the frequency array in O(1) constant time
    private static double getMedian(int[] freq, int d) {
        int count = 0;
        
        // Case 1: Odd window size
        if (d % 2 != 0) {
            int targetIndex = d / 2 + 1; // 1-based middle index
            for (int i = 0; i < freq.length; i++) {
                count += freq[i];
                if (count >= targetIndex) {
                    return i;
                }
            }
        } 
        // Case 2: Even window size
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
        List<Integer> expenditure = Arrays.asList(2, 3, 4, 2, 3, 6, 8, 4, 5);
        int d = 5;
        System.out.println("Total Notifications: " + activityNotifications(expenditure, d)); // Output: 2
    }
}