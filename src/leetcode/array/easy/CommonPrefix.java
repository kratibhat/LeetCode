package leetcode.array.easy;

public class CommonPrefix {
    public static String findCommonPrefix(String[] strings) {
        if (strings == null || strings.length == 0) return "";

        String prefix = strings[0];
        for (int i = 1; i < strings.length; i++) {
            while (!strings[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return ""; // No common prefix
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        String[] words = {"geeksforgeeks", "geeks", "geek", "geezer"};
        System.out.println("Common Prefix: " + findCommonPrefix(words));
    }
}