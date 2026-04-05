package leetcode.STRING.EASY;
//Given two strings s and t, determine if they are isomorphic.
//
//Two strings s and t are isomorphic if the characters in s can be replaced to get t.
//
//All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
//
//
//
//Example 1:
//
//Input: s = "egg", t = "add"
//
//Output: true
//
//Explanation:
//
//The strings s and t can be made identical by:
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] mapS = new int[256];
        int[] mapT = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            if (mapS[charS] != mapT[charT]) {
                return false;
            }

            mapS[charS] = i + 1;
            mapT[charT] = i + 1;
        }

        return true;
    }
    public static void main(String[] args) {
        IsomorphicStrings solution = new IsomorphicStrings();
        String s = "egg";
        String t = "add";
        boolean result = solution.isIsomorphic(s, t);
        System.out.println("Are the strings \"" + s + "\" and \"" + t + "\" isomorphic? " + result);
    }
}
