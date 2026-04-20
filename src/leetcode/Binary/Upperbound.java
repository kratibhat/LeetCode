package leetcode.Binary;
//Given a sorted array arr[] and a number target, the task is to find the upper bound of the target in this given array.
//The upper bound of a number is defined as the smallest index in the sorted array where the element is greater than the given number.
//
//Note: If all the elements in the given array are smaller than or equal to the target, the upper bound will be the length of the array.
//
//Examples :
//
//Input: arr[] = [2, 3, 7, 10, 11, 11, 25], target = 9
//Output: 3
//Explanation: 3 is the smallest index in arr[], at which element (arr[3] = 10) is larger than 9.
//Input: arr[] = [2, 3, 7, 10, 11, 11, 25
public class Upperbound {
    int upperBound(int[] arr, int target) {
        // code here
        int l=0; int r=arr.length-1;
        int ans=arr.length;
        while(l<=r)
        {
            int mid=l+(r-l)/2;
            if(arr[mid]>target)
            {
                ans=mid;

                r=mid-1;
            }
            else
            {
                l=mid+1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Upperbound ub = new Upperbound();
        int[] arr = {1, 2, 4, 4, 5, 6};
        int target = 4;
        int result = ub.upperBound(arr, target);
        System.out.println("Upper bound index: " + result);
    }
}
