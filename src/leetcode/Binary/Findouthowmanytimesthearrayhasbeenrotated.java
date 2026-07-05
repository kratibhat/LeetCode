package leetcode.Binary;
/// Problem Statement: Given an integer array arr of size N, sorted in ascending order (with distinct values). Now the array is rotated between 1 to N times which is unknown. Find how many times the array has been rotated.
///
/// Pre-requisites: Find minimum in Rotated Sorted Array,  Search in Rotated Sorted Array II & Binary Search algorithm
///
/// Examples
/// Input : arr = [4,5,6,7,0,1,2,3]
/// Result: 4
/// Explanation: The original array should be [0,1,2,3,4,5,6,7]. So, we can notice that the array has been rotated 4 times.
///
/// Input : arr = [3,4,5,1,2]
/// Output : 3
/// Explanation: The original array should be [1,2,3,4,5]. So, we can notice that the
public class Findouthowmanytimesthearrayhasbeenrotated {
    public static int findRotations(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        // Loop until low meets high
        while (low < high) {
            int mid = low + (high - low) / 2;

            // If mid element is greater than element at high,
            // smallest element lies to the right of mid
            if (arr[mid] > arr[high]) {
                low = mid + 1;
            } else {
                // Else smallest element is at mid or to the left
                high = mid;
            }
        }

        // When low == high, we found the smallest element
        return low;
    }
    public static void main(String[] args) {

        int[] arr = {4,5,6,7,0,1,2,3};
         int [] arr1={15, 18, 2, 3, 6, 12};
         int rot=findRotations(arr1);
         System.out.println(rot);
        int rotations = findRotations(arr);

        System.out.println(rotations);
    }
}
