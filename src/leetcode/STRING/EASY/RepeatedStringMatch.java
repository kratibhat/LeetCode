package leetcode.STRING.EASY;
//RABBIN KARP ALGORITHM
//Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it. If it is impossible for b​​​​​​ to be a substring of a after repeating it, return -1.
//
//Notice: string "abc" repeated 0 times is "", repeated 1 time is "abc" and repeated 2 times is "abcabc".
//
//
//
//Example 1:
//
//Input: a = "abcd", b = "cdabcdab"
//Output: 3
//Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b is a substring of it.
public class RepeatedStringMatch {
    public int repeatedStringMatch(String a, String b) {
        ///
        StringBuilder sb = new StringBuilder();
        int count = 0;

        while (sb.length() < b.length()) {
            sb.append(a);
            count++;
        }

        if (sb.toString().contains(b)) {
            return count;
        }

        sb.append(a);
        count++;

        if (sb.toString().contains(b)) {
            return count;
        }

        return -1;
    }

    public static void main(String[] args) {
        RepeatedStringMatch solution = new RepeatedStringMatch();
        String a = "abcd";
        String b = "cdabcdab";
        int result = solution.repeatedStringMatch(a, b);
        System.out.println("Minimum repetitions: " + result); // Output: 3
    }
}
////1. The "Base" Case: sb.length() < b.length()String $B$ cannot possibly
////be a substring of $A$ if the repeated version of $A$ is shorter than $B$.Example:
////If $A = "abcd"$ (length 4) and $B = "cdabcdab"$ (length 8), you must repeat $A$ at least
////twice just to have enough characters to match the length of $B$.2.
////The First Extra Append: The "Offset" CaseEven if sb is longer than $B$,
////it might not contain $B$ yet because of how the characters are aligned.
////$B$ could start at the very end of one instance of $A$ and wrap into the next.
////Example: * $A = "abc"$$B = "ca"$After the while loop, sb = "abc" (length 3 > length 2).sb
////does not contain "ca".We need one more append: sb = "abcabc".
////Now it contains "ca" (at index 2).3. The Second Extra Append:
////The "Worst-Case" WrapThis is the "stop" point. Mathematically, $B$ can start at the very
///last character of $A$. If $B$ is to exist within the repeated string, it would need one
///$A$ at the beginning (for that first character) and one $A$ at the end (to finish the sequence).
///Why we stop after count + 1If $B$ is not found after appending $A$ until the length is $\ge B\text{.length()}$ plus
///one more instance of $A$, it will never be found.The logic: If we have reached a length
////where $sb$ covers $B$ completely plus an extra buffer of $A$ on both sides, and it
////still isn't there, adding more $A$s just repeats the same internal patterns we've
///already checked.Any further appends would just be checking the same "junctions" between
///the strings that we already verified in the count and count + 1 checks.