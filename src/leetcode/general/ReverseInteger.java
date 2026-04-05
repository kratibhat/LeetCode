package leetcode.general;

public class ReverseInteger {
    public int reverse(int x) {
        long reversed = 0;
        while (x != 0) {
            int digit = x % 10;
            reversed = reversed * 10 + digit;
            x /= 10;
        }
        if (reversed < Integer.MIN_VALUE || reversed > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) reversed;
    }

    public static void main(String[] args) {
        ReverseInteger solution = new ReverseInteger();
        int x = -123;
        int result = solution.reverse(x);
        System.out.println("Reversed Integer: " + result); // Output: 321
    }
}
