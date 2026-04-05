package BasicCoding;

import java.util.Scanner;

public class ArmstrongNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = sc.nextInt();
        
        if (isArmstrong(number)) {
            System.out.println(number + " is an Armstrong number.");
        } else {
            System.out.println(number + " is not an Armstrong number.");
        }
    }

    static boolean isArmstrong(int n) {
        int temp = n;
        int digits = 0;
        int sum = 0;

        // Step 1: Count the number of digits
        // Example: 153 has 3 digits
        digits = String.valueOf(n).length();

        // Step 2: Extract each digit and raise to the power of 'digits'
        temp = n;
        while (temp > 0) {
            int lastDigit = temp % 10;
            sum += Math.pow(lastDigit, digits);
            temp = temp / 10;
        }

        // Step 3: Check if sum equals original number
        return sum == n;
    }
}