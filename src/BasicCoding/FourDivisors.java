package BasicCoding;
//Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors. If there is no such integer in the array, return 0.
//
//
//
//Example 1:
//
//Input: nums = [21,4,7]
//Output: 32
//Explanation:
//21 has 4 divisors: 1, 3, 7, 21
//4 has 3 divisors: 1, 2, 4
//7 has 2 divisors: 1, 7
//The answer is the sum of divisors of 21 only.
public class FourDivisors {
    public int sumFourDivisors(int[] nums) {
        int total = 0;
        for (int n : nums) {
            if (n <= 1) continue;
            int count = 0;
            int sumDiv = 0;
            for (int d = 1; d * d <= n; d++) {
                if (n % d == 0) {
                    int other = n / d;
                    if (d == other) {           // perfect square
                        count += 1;
                        sumDiv += d;
                    } else {
                        count += 2;
                        sumDiv += d + other;
                    }
                    if (count > 4) break;      // early exit
                }
            }
            if (count == 4) total += sumDiv;
        }
        return total;
    }

    public static void main(String[] args) {
        FourDivisors solution = new FourDivisors();
        int[] nums = {21, 4, 7};
        System.out.println(solution.sumFourDivisors(nums)); // Output: 32
    }
}
