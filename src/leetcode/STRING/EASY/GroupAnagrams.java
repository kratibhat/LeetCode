package leetcode.STRING.EASY;

import java.util.*;

public class GroupAnagrams {
    // Given an array of strings strs, group the anagrams together. You can return the answer in any order.
    //
    //
    //
    // Example 1:
    //
    // Input: strs = ["eat","tea","tan","ate","nat","bat"]
    // Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
    //
    // Example 2:
    //
    // Input: strs = [""]
    // Output: [[""]]
    //
    // Example 3:
    //
    // Input: strs = ["a"]
    // Output: [["a"]]
    public List<List<String>> groupAnagram(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
//// Expanded equivalent of the single-line usage
//List<String> list = map.get(key);
//if (list == null) {
//    list = new ArrayList<>();
//    map.put(key, list);
//}
//list.add(s);
        return new ArrayList<>(map.values());
    }


    public static void main(String[] args) {
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams.groupAnagram(strs);
        System.out.println("Grouped Anagrams: " + result);
    }
}
