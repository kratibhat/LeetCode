package leetcode.array.easy;
//Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
//
//The overall run time complexity should be O(log (m+n)).
//
//
//
//Example 1:
//
//Input: nums1 = [1,3], nums2 = [2]
//Output: 2.00000
//Explanation: merged array = [1,2,3] and median is 2.
//Example 2:
//
//Input: nums1 = [1,2], nums2 = [3,4]
//Output: 2.50000
//Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
public class MedianOfSortedArraysOfSameSize {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array to optimize binary search runtime to O(log(min(m, n)))
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int low = 0, high = m;

        while (low <= high) {
            int i = low + (high - low) / 2;
            int j = ((m + n + 1) / 2) - i;

            // Edge cases: handling boundaries when partitions are empty
            int maxLeft1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int minRight1 = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int maxLeft2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRight2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // Check if we found the correct partition
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // If total elements are odd
                if ((m + n) % 2 != 0) {
                    return Math.max(maxLeft1, maxLeft2);
                }
                // If total elements are even
                return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
            }
            else if (maxLeft1 > minRight2) {
                // Too many elements from nums1 on the left side, look left
                high = i - 1;
            }
            else {
                // Too few elements from nums1 on the left side, look right
                low = i + 1;
            }
        }

        return 0.0;
    }
    public double findMedianSortedArraysLEARNT(int[] nums1, int[] nums2) {
        int[] smaller = nums1.length > nums2.length ? nums2 : nums1;
        int[] larger = nums1.length > nums2.length ? nums1 : nums2;
        int totalLength = nums1.length + nums2.length;

        int low = 0, high = smaller.length;

        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (totalLength + 1) / 2 - partitionX;

            int l1 = partitionX == 0 ? Integer.MIN_VALUE : smaller[partitionX - 1];
            int r1 = partitionX == smaller.length ? Integer.MAX_VALUE : smaller[partitionX];

            int l2 = partitionY == 0 ? Integer.MIN_VALUE : larger[partitionY - 1];
            int r2 = partitionY == larger.length ? Integer.MAX_VALUE : larger[partitionY];

            if (l1 <= r2 && l2 <= r1)
                // means this is a valid partition
                if ((totalLength) % 2 == 0)
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                else
                    return Math.max(l1, l2);

            if (l1 > r2) high = partitionX - 1;
            else low = partitionX + 1;
        }

        return 0;
    }
    public static void main(String[] args) {
        MedianOfSortedArraysOfSameSize solution = new MedianOfSortedArraysOfSameSize();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        double median = solution.findMedianSortedArraysLEARNT(nums1, nums2);
        System.out.println("Median: " + median); // Output: 2.0
    }
}
