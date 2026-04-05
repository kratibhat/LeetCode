package leetcode.STRING.EASY;

public class FindtheIndexoftheFirstOccurrenceinaString {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        int haystackLength = haystack.length();
        int needleLength = needle.length();
//haystackLength - needleLength is used because we need to ensure that there are enough characters left in haystack to match needle
        //i+needleLength is prsnt so haystack-needlength
        for (int i = 0; i <= haystackLength - needleLength; i++) {
            if (haystack.substring(i, i + needleLength).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FindtheIndexoftheFirstOccurrenceinaString finder = new FindtheIndexoftheFirstOccurrenceinaString();
        String haystack = "hello";
        String needle = "ll";
        int index = finder.strStr(haystack, needle);
        System.out.println("Index of first occurrence: " + index);
    }
}
