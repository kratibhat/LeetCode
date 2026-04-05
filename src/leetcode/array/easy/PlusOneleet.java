package leetcode.array.easy;

public class PlusOneleet {
    public static int[] plusOne(int[] digits) {
        int n = digits.length;

        // Traverse the array from the last digit
        for (int i = n - 1; i >= 0; i--) {
            // If the current digit is less than 9, just increment it and return
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            // If the digit is 9, set it to 0 and continue to the next digit
            digits[i] = 0;
        }

        // If all digits were 9, we need an extra digit at the front
        int[] newNumber = new int[n + 1];
        newNumber[0] = 1; // Set the first digit to 1, rest are already 0
        return newNumber;
    }

    public static void main(String[] args) {
        int[] digits = {9, 9, 9};
        int[] result = plusOne(digits);
        System.out.print("Result: ");
        for (int digit : result) {
            System.out.print(digit);
        }
    }
}
