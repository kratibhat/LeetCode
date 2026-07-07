package leetcode.Binary.BSonAnswers;

// LeetCode 4 - Median of Two Sorted Arrays
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
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
        int[] a = {1, 3};
        int[] b = {2};
        MedianOfTwoSortedArrays sol = new MedianOfTwoSortedArrays();
        System.out.println(sol.findMedianSortedArrays(a, b)); // 2.0
    }
    public double findMedianSortedArrays1BINARY(int[] A, int[] B) {
        if (A.length > B.length) return findMedianSortedArrays1BINARY(B, A);

        int m = A.length, n = B.length;
        int imin = 0, imax = m, half = (m + n + 1) / 2;
        while (imin <= imax) {
            int i = (imin + imax) / 2;
            int j = half - i;
            if (i < m && B[j - 1] > A[i]) {
                imin = i + 1;
            } else if (i > 0 && A[i - 1] > B[j]) {
                imax = i - 1;
            } else {
                int maxLeft;
                if (i == 0) maxLeft = B[j - 1];
                else if (j == 0) maxLeft = A[i - 1];
                else maxLeft = Math.max(A[i - 1], B[j - 1]);

                if ((m + n) % 2 == 1) return maxLeft;

                int minRight;
                if (i == m) minRight = B[j];
                else if (j == n) minRight = A[i];
                else minRight = Math.min(A[i], B[j]);

                return (maxLeft + minRight) / 2.0;
            }
        }

        return 0.0;
    }
}

