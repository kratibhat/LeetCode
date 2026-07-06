package leetcode.Binary.BSonAnswers;

public class FindSqrtOfaNumber {
    // This function returns the floor value of the square root of a number
    public int mySqrt(int x) {
        // Handle small numbers directly
        if (x < 2) return x;

        // Initialize binary search range
        int left = 1, right = x / 2, ans = 0;

        // Perform binary search
        while (left <= right) {
            // Find middle point
            long mid = left + (right - left) / 2;

            // Check if mid*mid is less than or equal to x
            if (mid * mid <= x) {
                // Store mid as potential answer
                ans = (int) mid;
                // Move to right half
                left = (int) mid + 1;
            } else {
                // Move to left half
                right = (int) mid - 1;
            }
        }

        // Return final answer
        return ans;
    }
    public static void main(String []args)
    {
        FindSqrtOfaNumber fsn=new FindSqrtOfaNumber();
        int ans=fsn.mySqrt(27);
        System.out.println(ans);
    }
}
