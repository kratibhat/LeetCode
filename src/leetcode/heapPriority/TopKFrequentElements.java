package leetcode.heapPriority;

import java.util.*;

//Given an integer array nums and an integer k,
// -
// return the k most frequent elements. You may return the answer in any order.][

//
//
//
//Example 1:
//
//Input: nums = [1,1,1,2,2,3], k = 2
//
//Output: [1,2]
//
//Example 2:
//
//Input: nums = [1], k = 1
//
//Output: [1]
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        // Count the frequency of each element
        List<Integer>[] list = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        System.out.println("frequencyMap: " + frequencyMap);
        //approach 1: Bucket Sort
//        for(int key : frequencyMap.keySet()) {
//            int freq = frequencyMap.get(key);
//            if(list[freq] == null) {
//                list[freq] = new ArrayList<>();
//            }
//            list[freq].add(key);
//        }
//        System.out.println("list: " + Arrays.toString(list));
//        List<Integer> resultList = new ArrayList<>();
//        for(int i = list.length - 1; i >= 0 && resultList.size() < k; i--) {
//            if(list[i] != null) {
//                resultList.addAll(list[i]);
//            }
//        }
//        System.out.println("resultList before trimming: " + resultList);

        // Create a max heap based on frequency
        //Creates a priority queue (a heap) that stores Map.Entry<Integer,Integer> and
        // orders entries by their getValue() in descending order — so poll() returns the entry with the largest value (frequency). The lambda (a, b) -> b.getValue() - a.getValue() computes ordering by subtracting values, but subtraction can overflow; prefer Integer.compare or Comparator.comparingInt(...).reversed().
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
      //frequencyMap.entrySet() returns a view of the map's mappings as a Set<Map.Entry<K,V>>. Each element is a Map.Entry with getKey() and getValue().
        //Key points (short):
        //It's a Set of the map's entries, e.g. Set<Map.Entry<Integer,Integer>>.
        //The set is backed by the map: removing via the set/iterator removes from the map; changing the map updates the set.
        //You can iterate the entries or pass the set to methods that accept a Collection.
        //In your code, adding those entries to a PriorityQueue<Map.Entry<...>> lets the heap order entries (e.g. by getValue()).

        maxHeap.addAll(frequencyMap.entrySet());

        // Extract the top k frequent elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll().getKey();
        }

        return result;
    }
    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] topK = solution.topKFrequent(nums, k);
        System.out.print("Top " + k + " frequent elements: ");
        for (int num : topK) {
            System.out.print(num + " ");
        }
    }
}