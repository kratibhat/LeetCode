package leetcode.STRING.EASY;
//Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.
//
//Return the sorted string. If there are multiple answers, return any of them.
//
//
//
//Example 1:
//
//Input: s = "tree"
//Output: "eert"
//Explanation: 'e' appears twice while 'r' and 't' both appear once.
//So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//Example 2:
//
//Input: s = "cccaaa"
//Output: "aaaccc"
//Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
//Note that "cacaca" is incorrect, as the same characters must be together.
//Example 3:
//
//Input: s = "Aabb"
//Output: "bbAa"
//Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
//Note that 'A' and 'a' are treated as two different characters.
public class SortCharectersByFrequency {
    //7ms
    public String frequencySort(String s) {
        int[] freq = new int[128];
        for (char c : s.toCharArray()) {
            freq[c]++;
        }

        StringBuilder[] buckets = new StringBuilder[s.length() + 1]; //s.length() + 1 because frequency of any char can be at most s.length()
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new StringBuilder(); // Initialize each bucket
        }

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                buckets[freq[i]].append((char) i); //bucket[2] will have all chars with frequency 2
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = buckets.length - 1; i >= 0; i--) {
            for (char c : buckets[i].toString().toCharArray()) {
                for (int j = 0; j < i; j++) { //indicates frequency
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
    public static void main(String[] args) {
        SortCharectersByFrequency solution = new SortCharectersByFrequency();
        String s = "tree";
        String result = solution.frequencySort(s);
        String r2=solution.frequencySort1(s);
        System.out.println("Sorted string by frequency (Method 2): " + r2); //
        System.out.println("Sorted string by frequency: " + result); // Output: "eert" or "eetr"
    }

//method 2 using max heap
    // Time Complexity: O(n log k), where n is the length of the string and k is the number of unique characters.
    // Space Complexity: O(k)
    // k is the number of unique characters in the string.
    // This method uses a max heap (priority queue) to sort characters by their frequency.
    // It first counts the frequency of each character, then adds them to the max heap,
    // and finally constructs the result string by repeatedly polling from the heap.
    //13 ms

    public String frequencySort1(String s) {
        // 1. Count frequency
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // 2. Max heap based on frequency
        PriorityQueue<Character> maxHeap =
                new PriorityQueue<>((a, b) -> freqMap.get(b) - freqMap.get(a));

        maxHeap.addAll(freqMap.keySet());

        // 3. Build result
        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll();
            int count = freqMap.get(c);
            for (int i = 0; i < count; i++) {
                result.append(c);
            }
        }

        return result.toString();
    }
}



