package leetcode.array.easy;
//Given two sorted arrays a[] and b[] of size n and m respectively, merge both the arrays and rearrange the elements such that the smallest n elements are in a[] and the remaining m elements are in b[]. All elements in a[] and b[] should be in sorted order.
//
//Examples:
//
//Input: a[] = [2, 4, 7, 10], b[] = [2, 3]
//Output: a[] = [2, 2, 3, 4], b[] = [7, 10]
//Explanation: Combined sorted array = [2, 2, 3, 4, 7, 10], array a[] contains smallest 4 elements: 2, 2, 3 and 4, and array b[] contains remaining 2 elements: 7, 10.
public class MergeSortedArraysWithoutExtraSpace {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // Pointer for the end of nums1's valid elements
        int j = n - 1; // Pointer for the end of nums2
        int k = m + n - 1; // Pointer for the end of nums1

        // Merge in reverse order
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // If there are remaining elements in nums2, copy them
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 4, 6};
        int n = 3;

        merge(nums1, m, nums2, n);

        System.out.print("Merged array: ");
        for (int num : nums1) {
            System.out.print(num + " ");
        }
    }
}
