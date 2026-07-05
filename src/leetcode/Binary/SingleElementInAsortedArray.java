package leetcode.Binary;
/// You are given a sorted array consisting of only integers where every element appears
///  exactly twice, except for one element which appears exactly once.
///
/// Return the single element that appears only once.
///
/// Your solution must run in O(log n) time and O(1) space.
///
///
///
/// Example 1:
///
/// Input: nums = [1,1,2,3,3,4,4,8,8]
/// Output: 2
/// Example 2:
///
/// Input: nums = [3,3,7,7,10,11,11]
/// Output: 10
///
///
/// Constraints:
///
/// 1 <= nums.length <= 105
//0 <= nums[i] <= 105
public class SingleElementInAsortedArray {
    public int singleNonDuplicate(int[] nums) {
/// /*
/// =========================================================
/// Why do we use (mid ^ 1) ?
/// =========================================================
///
/// mid ^ 1 flips the last bit.
///
/// Effect:
///
/// If mid is EVEN
///     mid ^ 1 = mid + 1
///
/// Examples:
/// 0 -> 1
/// 2 -> 3
/// 4 -> 5
/// 6 -> 7
///
/// If mid is ODD
///     mid ^ 1 = mid - 1
///
/// Examples:
/// 1 -> 0
/// 3 -> 2
/// 5 -> 4
/// 7 -> 6
///
/// Notice:
///
/// Even index -> Next index
/// Odd index  -> Previous index
///
/// This is EXACTLY what we want because
/// duplicate pairs are always adjacent.
///
/// So,
///
/// nums[mid^1]
///
/// always refers to the expected duplicate of nums[mid].
///
/// If
///
/// nums[mid] == nums[mid^1]
///
/// then the pairing is correct.
///
/// If not,
///
/// the pairing is broken, which means we've crossed
/// the single element.
/// =========================================================
/// */
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (mid % 2 == 0) {

                if (nums[mid] == nums[mid + 1]) {
                    low = mid + 2;
                } else {
                    high = mid;
                }

            } else {

                if (nums[mid] == nums[mid - 1]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }

            }
        }

        return nums[low];
    }
    public static void main(String[] args) {
        SingleElementInAsortedArray solution = new SingleElementInAsortedArray();
        int[] nums1 = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        int result1 = solution.singleNonDuplicate(nums1);
        System.out.println("Single element in nums1: " + result1); // Output: 2

        int[] nums2 = {3, 3, 7, 7, 10, 11, 11};
        int result2 = solution.singleNonDuplicate(nums2);
        System.out.println("Single element in nums2: " + result2); // Output: 10
    }

        public int singleNonDuplicateadvanced(int[] nums) {

            int low = 0;
            int high = nums.length - 1;

            while (low < high) {

                int mid = low + (high - low) / 2;

                if (nums[mid] == nums[mid ^ 1]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            return nums[low];

    }
}
///*
//===============================================================
//540. Single Element in a Sorted Array
//===============================================================
//
//Question:
//- Every element appears exactly twice.
//- Only one element appears once.
//- Find that single element in O(log n).
//
//---------------------------------------------------------------
//Observation 1
//---------------------------------------------------------------
//
//Before the single element:
//
//Index : 0 1 2 3 4 5 6 7
//Value : 1 1 2 2 3 3 4 4
//
//Pairs always start at EVEN index.
//
//(0,1)
//(2,3)
//(4,5)
//(6,7)
//
//---------------------------------------------------------------
//Observation 2
//---------------------------------------------------------------
//
//Suppose
//
//nums = [1,1,2,3,3,4,4,8,8]
//
//Index : 0 1 2 3 4 5 6 7 8
//
//The single element is 2.
//
//Now look after it.
//
//3 -> (3,4)
//4 -> (5,6)
//8 -> (7,8)
//
//Notice:
//
//Pairs now start at ODD index.
//
//---------------------------------------------------------------
//Main Idea
//---------------------------------------------------------------
//
//Before single element:
//Pair starts at EVEN index.
//
//After single element:
//Pair starts at ODD index.
//
//Our job:
//Check whether the pair at mid is correct.
//
//---------------------------------------------------------------
//Case 1 : mid is EVEN
//---------------------------------------------------------------
//
//Expected pair:
//
//mid -----> mid+1
//
//Example
//
//Index : 0 1 2 3 4 5
//
//Value : 1 1 2 2 3 3
//            ^
//           mid=2
//
//If
//
//nums[mid] == nums[mid+1]
//
//then pairing is correct.
//
//Single element must be on RIGHT.
//
//Move:
//
//low = mid + 2;
//
//---------------------------------------------------------------
//Case 2 : mid is ODD
//---------------------------------------------------------------
//
//Expected pair:
//
//mid-1 -----> mid
//
//Example
//
//Index : 0 1 2 3 4 5
//
//Value : 1 1 2 2 3 3
//              ^
//             mid=3
//
//If
//
//nums[mid] == nums[mid-1]
//
//pairing is correct.
//
//Single element is on RIGHT.
//
//Move:
//
//low = mid + 1;
//
//---------------------------------------------------------------
//Otherwise
//---------------------------------------------------------------
//
//If expected pairing is broken,
//
//Single element lies on LEFT.
//
//Move
//
//high = mid;
//
//---------------------------------------------------------------
//Why while(low < high)?
//---------------------------------------------------------------
//
//We are NOT searching for an exact value.
//
//We are shrinking the search space until only ONE
//candidate remains.
//
//When
//
//low == high
//
//that index itself is the answer.
//
//Return
//
//nums[low]
//
//---------------------------------------------------------------
//Time Complexity
//---------------------------------------------------------------
//
//Every iteration removes half of the array.
//
//Time : O(log n)
//
//Space : O(1)
//
//---------------------------------------------------------------
//Easy Memory Trick
//---------------------------------------------------------------
//
//Before single:
//Pair starts at EVEN index.
//
//After single:
//Pair starts at ODD index.
//
//Correct pairing
//    -> Move RIGHT
//
//Broken pairing
//    -> Move LEFT
//===============================================================
//*/

////*
/// ===============================================================
/// Why do we use (mid ^ 1) ?
/// ===============================================================
///
/// ^ is the BITWISE XOR operator.
///
/// Here we XOR mid with 1.
///
/// mid ^ 1
///
/// This simply flips the LAST BIT of the binary number.
///
/// ---------------------------------------------------------------
/// Examples
/// ---------------------------------------------------------------
///
/// mid = 0
///
/// Binary
///
/// 0 = 0000
/// 1 = 0001
///
/// 0 ^ 1 = 0001 = 1
///
/// --------------------------------
///
/// mid = 1
///
/// 1 = 0001
/// 1 = 0001
///
/// 1 ^ 1 = 0000 = 0
///
/// --------------------------------
///
/// mid = 2
///
/// 2 = 0010
/// 1 = 0001
///
/// 2 ^ 1 = 0011 = 3
///
/// --------------------------------
///
/// mid = 3
///
/// 3 = 0011
/// 1 = 0001
///
/// 3 ^ 1 = 0010 = 2
///
/// --------------------------------
///
/// mid = 4
///
/// 4 = 0100
///
/// 4 ^ 1 = 0101 = 5
///
/// --------------------------------
///
/// mid = 5
///
/// 5 = 0101
///
/// 5 ^ 1 = 0100 = 4
///
/// ---------------------------------------------------------------
/// Pattern
/// ---------------------------------------------------------------
///
/// mid        mid ^ 1
///
/// 0   --->   1
/// 1   --->   0
///
/// 2   --->   3
/// 3   --->   2
///
/// 4   --->   5
/// 5   --->   4
///
/// 6   --->   7
/// 7   --->   6
///
/// Notice:
///
/// Even index  -> Next index
/// Odd index   -> Previous index
///
/// Exactly what we need for pairs!
///
/// ---------------------------------------------------------------
/// Why is this useful?
/// ---------------------------------------------------------------
///
/// Normally we write:
///
/// if(mid % 2 == 0)
///     compare nums[mid] with nums[mid+1]
///
/// else
///     compare nums[mid] with nums[mid-1]
///
/// Instead of writing two separate cases,
///
/// mid ^ 1 automatically gives the partner index.
///
/// So we simply write
///
/// if(nums[mid] == nums[mid^1])
///
/// If true
///     Pair is correct.
///     Single element is on RIGHT.
///
/// Else
///     Pair is broken.
///     Single element is on LEFT.
///
/// ---------------------------------------------------------------
/// Easy Memory Trick
/// ---------------------------------------------------------------
///
/// mid ^ 1
///
/// Even -> Next
///
/// Odd -> Previous
///
/// So,
///
/// mid ^ 1 always points to the duplicate partner.
/// ===============================================================
/// */
///The intuition in one sentence
///
/// Think of ^ 1 as:
///
/// "Give me the index where mid's duplicate should be."
///
/// If mid is even, the duplicate should be next (mid + 1).
/// If mid is odd, the duplicate should be previous (mid - 1).
///
/// That's why mid ^ 1 is such a neat trick for this problem. It's not a general binary search trick—it's specifically useful because every duplicate pair occupies two adjacent indices.