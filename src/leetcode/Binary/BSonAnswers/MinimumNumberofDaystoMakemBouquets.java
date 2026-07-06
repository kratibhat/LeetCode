package leetcode.Binary.BSonAnswers;
//You are given an integer array bloomDay, an integer m and an integer k.
//
//You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
//
//The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.
//
//Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets return -1.
//
//
//
//Example 1:
//
//Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
//Output: 3
//Explanation: Let us see what happened in the first three days. x means flower bloomed and _ means flower did not bloom in the garden.
//We need 3 bouquets each should contain 1 flower.
//After day 1: [x, _, _, _, _]   // we can only make one bouquet.
//After day 2: [x, _, _, _, x]   // we can only make two bouquets.
//After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
public class MinimumNumberofDaystoMakemBouquets {
    public static int minDays(int[] bloomDay, int m, int k) {

        // Impossible case:
        // We need m * k flowers in total.
        if ((long) m * k > bloomDay.length) {
            return -1;
        }

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        // Find minimum and maximum bloom day
        for (int day : bloomDay) {
            low = Math.min(low, day);
            high = Math.max(high, day);
        }

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (canMake(mid, bloomDay, m, k)) {
                high = mid;          // mid works, try smaller day
            } else {
                low = mid + 1;       // mid doesn't work
            }
        }

        return low;
    }

  public static boolean canMake(int day, int[] bloomDay, int m, int k) {

        int flowers = 0;
        int bouquets = 0;

        for (int bloom : bloomDay) {

            if (bloom <= day) {
                flowers++;
            } else {
                bouquets += flowers / k;
                flowers = 0;
            }
        }

        // Last consecutive block
        bouquets += flowers / k;

        return bouquets >= m;
    }
    public static void main(String []args){
        int []arr={1,10,3,10,2};
        int m=3;
        int k=1;
        int a=minDays(arr,m,k);
        System.out.println(a);
    }
}
