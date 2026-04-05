package leetcode.array.subarray;

import java.util.HashMap;

//Given an array of integers arr[] and a number k, the task is to count the number of subarrays having XOR of their elements as k.
//
//Examples:
//
//Input: arr[] = [4, 2, 2, 6, 4], k = 6
//Output: 4
//Explanation: The subarrays having XOR of their elements as 6 are [4, 2], [4, 2, 2, 6, 4], [2, 2, 6], and [6].
//
//Input: arr[] = [5, 6, 7, 8, 9], k = 5
//Output: 2
//Explanation: The subarrays having XOR of their elements as 5 are [5] and [5, 6, 7, 8, 9].
public class CountSubarrayswithgivenXOR {
    // Function to find count of subarrays of arr
    // with XOR value equals to K
    static int subarrayXor(int[] arr, int k) {
        int res = 0;

        // Pick starting point i of subarrays
        for (int i = 0; i < arr.length; i++) {
            int prefXOR = 0;

            // Pick ending point j of subarray for each i
            for (int j = i; j < arr.length; j++) {
                // calculate prefXOR for subarray arr[i ... j]
                prefXOR = prefXOR ^ arr[j];

                // If prefXOR is equal to given value, increase res by 1
                if (prefXOR == k)
                    res++;
            }
        }
        return res;
    }
    // Function to find the count of subarrays of arr
    // with XOR value equals to k
    static int subarrayXor1(int[] arr, int k) {
        int res = 0;

        // Create map that stores number of prefix arrays
        // corresponding to a XOR value
        HashMap<Integer, Integer> mp = new HashMap<>();

        int prefXOR = 0;

        for (int val : arr) {

            // Find XOR of current prefix
            prefXOR ^= val;
//currentPrefixXOR ^ previousPrefixXOR = k
            // Rearranging the equation, we get:
            // previousPrefixXOR = currentPrefixXOR ^ k
            // If prefXOR ^ k exists in mp then there is a subarray
            // ending at i with XOR equal to k
            res += mp.getOrDefault(prefXOR ^ k, 0);
//{CurrentSum} - {PreviousSum} = k==>prefSum - k
            // If this prefix subarray has XOR equal to k
            if (prefXOR == k)
                res++;

            // Add the XOR of this subarray to the map
            mp.put(prefXOR, mp.getOrDefault(prefXOR, 0) + 1);
        }

        // Return total count of subarrays having XOR of
        // elements as given value k
        return res;
    }



    public static void main(String[] args) {
        int[] arr = { 4, 2, 2, 6, 4 };
        int k = 6;

        System.out.println(subarrayXor1(arr, k));
    }
}
//Imagine k = 6 and you are currently at an index where your prefXOR is 2.
//
//Your question:
//"Is there a subarray ending at this index whose XOR is equal to 6?"
//
//The idea:
//To form a subarray with XOR 6, you need a previous prefix XOR such that:
//
//previousXOR ^ currentPrefXOR = k
//
//
//Substitute values:
//
//previousXOR ^ 2 = 6
//
//
//Using the XOR identity, rearrange:
//
//previousXOR = 2 ^ 6
//
//
//Which gives:
//
//previousXOR = 4
//
//
//The search:
//You now look in the map for how many times prefix XOR 4 has appeared before.
//
//The result:
//
//If the map says prefix XOR 4 appeared twice,
//
//Then there are 2 different subarrays ending at the current index whose XOR is 6
//
//Why this works:
//Each occurrence of the required previous XOR represents a different place where you can "cut" the array to form a valid subarray.