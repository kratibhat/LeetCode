package leetcode.STRING.EASY.twopointer;
//Write a function that reverses a string. The input string is given as an array of characters s.
//
//You must do this by modifying the input array in-place with O(1) extra memory.
//
//
//
//Example 1:
//
//Input: s = ["h","e","l","l","o"]
//Output: ["o","l","l","e","h"]
//Example 2:
//
//Input: s = ["H","a","n","n","a","h"]
//Output: ["h","a","n","n","a","H"]
public class ReverseString {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            // Swap characters in-place using a temporary variable
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            // Move pointers closer to the center
            left++;
            right--;
        }
    }
    public static void main(String[] args){
        ReverseString s=new ReverseString();
        char[] arr={'h','e','l','l','o'};
        s.reverseString(arr);
        for(char c:arr) {
            System.out.print(c + " ");
        }
    }

}
