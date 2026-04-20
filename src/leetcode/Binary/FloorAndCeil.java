package leetcode.Binary;
//Floor and ceil in a sorted array
//
//You are given a sorted list of integers arr and a target integer k. Your task is to determine two values:
//
//The floor of k
//
//k: The largest number in arr
//
//arr that is less than or equal to k
//
//k.
//
//The ceiling of k
//
//k: The smallest number in arr
//
//arr that is greater than or equal to k
//
//k.
//
//If either the floor or the ceiling does not exist, return −1
//
//
//
//−1 for that value.
//
//The solution should be in O(log(N)).
//
//Function Declaration
//
//Function Name
//
//findFloorCeil
//
//
//
//findFloorCeil – This function finds the floor and ceiling of a given target value in a sorted array.
//
//
//
//Parameters
//
//arr
//
//
//
//arr : A reference to a sorted array of strictly increasing integers.
//
//k
//
//
//
//k : The target integer whose floor and ceiling are to be found.
//
//Return Value
//
//Returns a pair (floor,ceil)
//
//
//
//(floor,ceil):floor
//
//
//
//floor – the largest value in arr
//
//
//
//arr less than or equal to k
//
//
//
//k
//
//ceil
//
//
//
//ceil – the smallest value in arr
//
//
//
//arr greater than or equal to k
//
//
//
//k
//
//Returns −1
//
//
//
//−1 for floor or ceil if it does not exist.
public class FloorAndCeil {
    public static int[] findFloorCeil(int[] arr, int k) {
        int floor = -1;
        int ceil = -1;

        // Find Floor
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == k) {
                floor = arr[mid];
                break; // Exact match found
            } else if (arr[mid] < k) {
                floor = arr[mid]; // Potential candidate
                low = mid + 1;    // Try to find a larger value on the right
            } else {
                high = mid - 1;
            }
        }

        // Find Ceiling
        low = 0; high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == k) {
                ceil = arr[mid];
                break; // Exact match found
            } else if (arr[mid] > k) {
                ceil = arr[mid]; // Potential candidate
                high = mid - 1;   // Try to find a smaller value on the left
            } else {
                low = mid + 1;
            }
        }

        return new int[]{floor, ceil};
    }
public static void main(String[] args) {
    int[] arr = {1, 2, 4, 6, 8, 10};
    int k = 5;
    int[] result = findFloorCeil(arr, k);
    System.out.println("Floor: " + result[0] + ", Ceil: " + result[1]);
}
}
