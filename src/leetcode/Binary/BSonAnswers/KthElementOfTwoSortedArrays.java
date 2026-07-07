package leetcode.Binary.BSonAnswers;

// Find k-th element of two sorted arrays (1-based k)
public class KthElementOfTwoSortedArrays {
    public int kthElement(int[] a, int[] b, int k) {
        int n = a.length;
        int m = b.length;

        // Always binary search on the smaller array
        if (n > m) {
            return kthElement(b, a, k);
        }

        int low = Math.max(0, k - m);
        int high = Math.min(k, n);

        while (low <= high) {
            int cut1 = low + (high - low) / 2;
            int cut2 = k - cut1;

            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : a[cut1 - 1];
            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : b[cut2 - 1];

            int r1 = (cut1 == n) ? Integer.MAX_VALUE : a[cut1];
            int r2 = (cut2 == m) ? Integer.MAX_VALUE : b[cut2];

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6};
        KthElementOfTwoSortedArrays sol = new KthElementOfTwoSortedArrays();
        System.out.println(sol.kthElement(a, b, 1)); // 1
        System.out.println(sol.kthElement(a, b, 4)); // 4
        System.out.println(sol.kthElement(a, b, 6)); // 6
    }
}

