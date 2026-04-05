package BasicCoding;
//Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
//
//Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
//
//
//
//Example 1:
//
//Input: x = 123
//Output: 321
//Example 2:
//
//Input: x = -123
//Output: -321
public class ReverseInteger {
    public int reverse(int x) {
        int rev=0;
        while(x!=0){
            if (rev > Integer.MAX_VALUE / 10 || rev < Integer.MIN_VALUE / 10) {
                return 0;
            }
            rev=rev*10+x%10;
            x/=10;
        }
        return rev;
    }
    public static void main(String[] args) {
        ReverseInteger solution = new ReverseInteger();
        int x1 = 123;
        int x2 = -123;
        int x3 = 120;

        System.out.println("Reversed " + x1 + ": " + solution.reverse(x1)); // Output: 321
        System.out.println("Reversed " + x2 + ": " + solution.reverse(x2)); // Output: -321
        System.out.println("Reversed " + x3 + ": " + solution.reverse(x3)); // Output: 21
    }
}
