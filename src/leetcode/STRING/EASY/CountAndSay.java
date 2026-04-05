package leetcode.STRING.EASY;
//first frequency thn number
//The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
////
//countAndSay(1) = "1"
//countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.
//To determine how you "say" a digit string, split it into the minimal number of groups so that each group is a contiguous section all of the same digit. Then for each group, say the number of digits, then say the digit. Finally, concatenate every group's "say" to form the next term in the sequence.
////Given a positive integer n, return the nth term of the count-and-say sequence.
//Example 1:
//Input: n = 1
//Output: "1"
public class CountAndSay {
    public String countAndSay(int n) {
        if (n <= 0) {
            return "";
        }
        if (n == 1) {
            return "1";
        }

        String previousTerm = countAndSay(n - 1);
        StringBuilder currentTerm = new StringBuilder();

        int count = 1;
        for (int i = 1; i < previousTerm.length(); i++) {
            if (previousTerm.charAt(i) == previousTerm.charAt(i - 1)) {
                count++;
            } else {
                currentTerm.append(count).append(previousTerm.charAt(i - 1));
                count = 1;
            }
        }
        currentTerm.append(count).append(previousTerm.charAt(previousTerm.length() - 1));

        return currentTerm.toString();
    }

    public static void main(String[] args) {
        CountAndSay countAndSay = new CountAndSay();
        int n = 7;
        String result = countAndSay.countAndSay(n);
        String s="1";
        System.out.println("The " + n + "th term of the Count and Say sequence is: " + result);
        for(int i=1;i<n;i++){
           s= countAndSay.countAndSayIterative(s);
        }
        System.out.println("The " + n + "th term of the Count and Say sequence using iterative method is: " + s);
    }
         //method 2
    public String countAndSayIterative(String  n) {
        StringBuilder sb = new StringBuilder();
        char prevChar = n.charAt(0);
        int count = 1;
        for (int i = 1; i < n.length(); i++) {
            char currentChar = n.charAt(i);
            if (currentChar == prevChar) {
                count++;
            } else {
                sb.append(count).append(prevChar);
                prevChar = currentChar;
                count = 1;
            }
        }
        sb.append(count).append(prevChar);
        return sb.toString();
    }
}
