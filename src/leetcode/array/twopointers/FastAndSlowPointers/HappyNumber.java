package leetcode.array.twopointers.FastAndSlowPointers;
//Write an algorithm to determine if a number n is happy.
//
//A happy number is a number defined by the following process:
//
//Starting with any positive integer, replace the number by the sum of the squares of its digits.
//Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
//Those numbers for which this process ends in 1 are happy.
//Return true if n is a happy number, and false if not.
//
//
//
//Example 1:
//
//Input: n = 19
//Output: true
//Explanation:
//12 + 92 = 82
//82 + 22 = 68
//62 + 82 = 100
//12 + 02 + 02 = 1
//Example 2:
//
//Input: n = 2
//Output: false
//
public class HappyNumber {
    //Why 1?If the sum becomes 1, the rules state the number is "Happy."
    // The loop stops, and the function returns true.Why 4?This is the clever part.
    // If a number is not happy, it will eventually fall into a repeating cycle.
    // Mathematical research has shown that all unhappy numbers eventually enter a specific
    // 8-number cycle:$$4 \rightarrow 16 \rightarrow 37 \rightarrow 58 \rightarrow 89 \rightarrow 145 \rightarrow 42 \rightarrow 20 \rightarrow 4 \dots$$
    // Since this cycle always includes the number 4, checking for 4 is a shorthand way of saying: "We have entered the 'unhappy' loop; stop calculating
    // now because we will never reach 1."
    public boolean isHappy(int n) {
        int temp = n, sum = 0;
        while(sum !=1 && sum !=4){  //1->happy 4->16->37->58->89->145->42->20->4 -->unhappy
            sum = 0;
            while(temp > 0){
                int rem = temp % 10;
                sum+= rem * rem;
                temp/=10;
            }
            temp = sum;
        }

        return sum==1;
    }

    public static void main(String[] args) {
        HappyNumber solution = new HappyNumber();
        int n1 = 19;
        int n2 = 2;

        System.out.println("Is " + n1 + " a happy number? " + solution.isHappy(n1)); // Expected: true
        System.out.println("Is " + n2 + " a happy number? " + solution.isHappy(n2)); // Expected: false
    }
}
