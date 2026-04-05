package leetcode.array.easy;
//Let n = 2k (even). Then x^n = x^{2k} = x^k * x^k = (x^k)^2. Your recursion computes half = myPow(x, n/2) = x^k, so returning half * half yields x^n.
//Let n = 2k + 1 (odd). Then x^n = x^{2k+1} = x^{2k} * x = (x^k)^2 * x. Your recursion gives half = x^k, so half * half * x accounts for that extra x.
//Integer division n/2 produces k (floor) for positive n, which is why the split works.
public class PowerOfXAndN {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        if (n < 0) {
            x = 1 / x;
            n = -n;  // x raised -n is the same as (1/x) raised to n
        }
        double half = myPow(x, n / 2); // Recursive call
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    public static void main(String[] args) {
        PowerOfXAndN solution = new PowerOfXAndN();
        double x = 2.0;
        int n = 10;
        double result = solution.myPow(x, n);
        System.out.println("Result: " + result); // Output: 1024.0
    }
}
