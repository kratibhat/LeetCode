package leetcode.Greedy;
//You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
//
//Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.
//
//
//
//Example 1:
//
//Input: flowerbed = [1,0,0,0,1], n = 1
//Output: true
//Example 2:
//
//Input: flowerbed = [1,0,0,0,1], n = 2
//Output: false
//
public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            // Check if current plot is empty
            if (flowerbed[i] == 0) {
                // Check if left and right plots are empty or out of bounds
                boolean emptyLeft = (i == 0) || (flowerbed[i - 1] == 0);
                boolean emptyRight = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);

                if (emptyLeft && emptyRight) {
                    flowerbed[i] = 1; // Plant a flower
                    count++;

                    // Optimization: If we've already planted enough, stop early
                    if (count >= n) return true;
                }
            }
        }
        return count >= n;
    }
    public static void main(String[] args) {
       int[] flowerbed = {1, 0, 0, 0, 1};
       int n = 1;
       CanPlaceFlowers c = new CanPlaceFlowers();
       System.out.println(c.canPlaceFlowers(flowerbed, n));
    }
}
//By planting a flower as soon as we find a valid spot (the "Greedy" choice), we don't negatively impact future possibilities. Because we check both neighbors, planting a flower at index $i$ only "blocks" indices $i-1$ and $i+1$, which is the minimum possible restriction required by the rules.