package leetcode.STRING.EASY;
//The beauty of a string is the difference in frequencies between the most frequent and least frequent characters.
//
//For example, the beauty of "abaacc" is 3 - 1 = 2.
//Given a string s, return the sum of beauty of all of its substrings.
//
//
//
//Example 1:
//
//Input: s = "aabcb"
//Output: 5
//Explanation: The substrings with non-zero beauty are ["aab","aabc","aabcb","abcb","bcb"], each with beauty equal to 1.
public class SumOfBeautyOfAllSubstring {
    /// /1. The Outer Loop (i): The Starting PointThe loop for (int i = 0; i < n; i++)
    /// determines where a substring begins. If $i = 0$, we are looking at all substrings
    ///  that start at the very first character. If $i = 5$, we are looking at substrings that
    ///  start at the sixth character.2.
    /// The Inner Loop (j): The Ending PointThe loop for (int j = i; j < n; j++)
    /// determines where the substring ends. By starting j at the current value of i
    /// and moving forward, we are effectively "growing" the substring one character at a time.
    public int beautySum(String s) {

        int n = s.length();

        int totalBeauty = 0;



        for (int i = 0; i < n; i++) {

            int[] freq = new int[26];



            for (int j = i; j < n; j++) {

                freq[s.charAt(j) - 'a']++;



                int maxFreq = 0;

                int minFreq = Integer.MAX_VALUE;



                for (int f : freq) {

                    if (f > 0) {

                        maxFreq = Math.max(maxFreq, f);

                        minFreq = Math.min(minFreq, f);

                    }

                }



                totalBeauty += (maxFreq - minFreq);

            }

        }



        return totalBeauty;

    }

    public static void main(String[] args) {
        SumOfBeautyOfAllSubstring solution = new SumOfBeautyOfAllSubstring();
        String s = "aabcb";
        int result = solution.beautySum(s);
        System.out.println("Sum of beauty of all substrings: " + result); // Output: 5
    }
}
