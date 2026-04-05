package leetcode.STRING.EASY;
//Given a string s, return the number of homogenous substrings of s. Since the answer may be too large, return it modulo 109 + 7.
//
//A string is homogenous if all the characters of the string are the same.
//
//A substring is a contiguous sequence of characters within a string.
//
//
//
//Example 1:
//
//Input: s = "abbcccaa"
//Output: 13
//Explanation: The homogenous substrings are listed as below:
//"a"   appears 3 times.
//"aa"  appears 1 time.
//"b"   appears 2 times.
//"bb"  appears 1 time.
//"c"   appears 3 times.
//"cc"  appears 2 times.
//"ccc" appears 1 time.
//3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.
public class CountNumberOfHomogeneousSubstrings {
    public int countHomogenous(String s) {
        //10^9 + 7 is a large prime number commonly used as a modulus in programming contests and competitive programming.
        long mod = 1_000_000_007; //// Prevents integer overflow for large results
        long count = 0;
        long currentLength = 1;
//n*(n+1)/2 formula to calculate number of substrings
// for ex ccc--> 3*(3+1)/2=6 substrings (c, c, c, cc, cc, ccc)
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currentLength++;
            } else {
                count = (count + (currentLength * (currentLength + 1)) / 2) % mod;
                currentLength = 1;
            }
        }
//The maximum value for a signed 32-bit integer (like int in Java or C++) is 2,147,483,647.
// Since 1,000,000,007 is less than 2 \times 10^9, any result after a modulo operation
// will always fit inside a standard 32-bit integer.
// This makes the code memory-efficient and avoids the need for specialized "BigInt" libraries
        count = (count + (currentLength * (currentLength + 1)) / 2) % mod;

        return (int) count;
    }

    public static void main(String[] args) {
        CountNumberOfHomogeneousSubstrings solution = new CountNumberOfHomogeneousSubstrings();
        String s = "abbcccaa";
        int result = solution.countHomogenous(s);
        System.out.println("Number of homogenous substrings: " + result); // Output: 13
    }
}


////1. The "Contiguous" Clue
/// The problem asks for substrings, not subsequences.
///
/// Subsequences can skip characters (like "ac" in "abc").
///
/// Substrings must be a solid, unbroken "chunk" of the original string.
///
/// The Logic: Since a homogenous substring cannot contain two different characters (e.g., "aab" is not homogenous),
////any homogenous substring must be entirely contained within a single block of identical characters.
////You can never "bridge" across a different character.
///
/// 2. The "Homogenous" (All-the-Same) Clue
/// When a problem asks you to count something where every element in the group must be the same, it is a massive hint to use Grouping (also called "Run-Length" logic).
///
/// If you see "aaa", you don't need to look at the 'b' that comes after it to know how many 'a' substrings are inside that "aaa".
///
/// The Logic: Break the string into blocks of same-character "runs" and treat each run as its own mini-problem.