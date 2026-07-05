package leetcode.Binary;
/// A peak element is an element that is strictly greater than its neighbors.
///
/// Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
///
//You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
///
/// You must write an algorithm that runs in O(log n) time.
///
///
///
/// Example 1:
///
/// Input: nums = [1,2,3,1]
/// Output: 2
/// Explanation: 3 is a peak element and your function should return the index number 2.
/// Example 2:
///
/// Input: nums = [1,2,1,3,5,6,4]
/// Output: 5
/// Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
///
///
/// Constraints:
///
/// 1 <= nums.length <= 1000
//-231 <= nums[i] <= 231 - 1
// nums[i] != nums[i+1] for all valid i.
public class FindPeakElement {
    public static int findPeakElement(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] < nums[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
    public static void main(String[] args){
        int[] nums = {1,2,1,3,5,6,4};
        int res=findPeakElement(nums);
        System.out.println("Peak Element Index: " + res);

    }
}
///*
//=================================================================
//162. Find Peak Element
//=================================================================
//
//Question
//
//A peak element is an element that is greater than its neighbours.
//
//nums[i] > nums[i-1]
//AND
//nums[i] > nums[i+1]
//
//Return ANY one peak.
//
//---------------------------------------------------------------
//Observation
//---------------------------------------------------------------
//
//Example
//
//1 2 3 1
//
//Peak = 3
//
//--------------------------------
//
//Example
//
//1 2 1 3 5 6 4
//
//Peaks = 2 or 6
//
//Return any one.
//
//---------------------------------------------------------------
//Main Idea
//---------------------------------------------------------------
//
//Compare nums[mid] with nums[mid+1].
//
//There are only TWO possibilities.
//
//---------------------------------------------------------------
//Case 1
//
//nums[mid] < nums[mid+1]
//
//Example
//
//1 2 3 4 5
//      ^
//
//The array is going UP.
//
//A peak MUST exist on the RIGHT.
//
//Move
//
//low = mid + 1
//
//---------------------------------------------------------------
//Case 2
//
//nums[mid] > nums[mid+1]
//
//Example
//
//5 4 3 2 1
//  ^
//
//The array is going DOWN.
//
//mid itself may be the peak,
//or there may be another peak on the LEFT.
//
//Move
//
//high = mid
//
//NOT
//
//high = mid - 1
//
//because mid itself could be the answer.
//
//---------------------------------------------------------------
//Why while(low < high)?
//---------------------------------------------------------------
//
//We are NOT searching for a value.
//
//We are shrinking the search space until only ONE
//candidate remains.
//
//When
//
//low == high
//
//that index is definitely a peak.
//
//Return low.
//
//---------------------------------------------------------------
//Algorithm
//---------------------------------------------------------------
//
//while(low < high){
//
//    mid
//
//    if(nums[mid] < nums[mid+1])
//        low = mid + 1;
//
//    else
//        high = mid;
//}
//
//return low;
//
//---------------------------------------------------------------
//Why compare with mid+1?
//---------------------------------------------------------------
//
//mid+1 tells us the direction.
//
//If next element is bigger,
//go RIGHT.
//
//If next element is smaller,
//go LEFT.
//
//We don't need to compare both neighbours.
//
//---------------------------------------------------------------
//Time Complexity
//
//O(log n)
//
//Space Complexity
//
//O(1)
//
//---------------------------------------------------------------
//Memory Trick
//
//Increasing slope
//
//nums[mid] < nums[mid+1]
//
//Go RIGHT.
//
//----------------------------
//
//Decreasing slope
//
//nums[mid] > nums[mid+1]
//
//Go LEFT (including mid).
//
//Think:
//
//UPHILL
//    ->
//    Keep climbing.
//
//DOWNHILL
//    ->
//    Peak is behind you.
//=================================================================
//*/