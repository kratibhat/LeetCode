package leetcode.arraystuf;

import java.util.ArrayList;

class UnionOfTwoSortedArrays {
    public static ArrayList<Integer> findUnion(int a[], int b[]) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        int n = a.length, m = b.length;

        while (i < n && j < m) {
            // Pick the smaller element to maintain sorted order
            if (a[i] < b[j]) {
                addDistinct(result, a[i]);
                i++;
            } else if (b[j] < a[i]) {
                addDistinct(result, b[j]);
                j++;
            } else {
                // Elements are equal, add once and move both
                addDistinct(result, a[i]);
                i++;
                j++;
            }
        }

        // Add remaining elements of array a
        while (i < n) {
            addDistinct(result, a[i]);
            i++;
        }

        // Add remaining elements of array b
        while (j < m) {
            addDistinct(result, b[j]);
            j++;
        }

        return result;
    }

    // Helper method to ensure we only add distinct elements
    private static void addDistinct(ArrayList<Integer> list, int val) {
        if (list.isEmpty() || list.get(list.size() - 1) != val) {
            list.add(val);
        }
    }
    public static void main(String[] args) {
        int[] a = {1, 2, 4, 5, 6};
        int[] b = {2, 3, 5, 7};
        ArrayList<Integer> union = findUnion(a, b);
        System.out.println("Union of the two arrays: " + union); // Output: [1, 2, 3, 4, 5, 6, 7]
    }
}