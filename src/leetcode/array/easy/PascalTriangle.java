package leetcode.array.easy;

import java.util.ArrayList;
import java.util.List;
//other logics wch can be derived from pascal's triangle
//             1 -->(a+b)^0=1      sum=1   2^0=1
//           1   1-->(a+b)^1= a + b  sum=2  2^1=2
//         1   2   1-->(a+b)^2= a^2 + 2ab + b^2   sum=4= 2^2
//       1   3   3   1-->(a+b)^3= a^3 + 3a^2b + 3ab^2 + b^3 sum=8= 2^3
//     1   4   6   4   1->>(a+b)^4= a^4 + 4a^3b + 6a^2b^2 + 4ab^3 + b^4
//   1   5  10  10   5   1

//consider diagonals is fibonacci series
//nCR -->5C2-->10 -->5th row and 2nd index
//1
//1 1
//1 2 1 -->2 square is 1+3 wch is beside it
//1 3 3 1-->3 square is 3+6 wch is beside it
//1 4 6 4 1
public class PascalTriangle {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows == 0)
            return triangle;
        List<Integer> row = new ArrayList<>();
        row.add(1);
        triangle.add(row);
        if(numRows == 1)
            return triangle;
        for (int i = 1; i < numRows; i++) {
            List<Integer> newRow = new ArrayList<>();
            newRow.add(1); // first element
            List<Integer> prevRow = triangle.get(i - 1);
            for (int j = 1; j < i; j++) {  //if j=0 thn j<i-1 shud be taken
                newRow.add(prevRow.get(j - 1) + prevRow.get(j)); //thn this will be j+1
            }
            newRow.add(1); // last element
            triangle.add(newRow);
        }
        return triangle;


    }
    public static void main(String[] args) {
        int numRows = 5;
        List<List<Integer>> res= generate(numRows);


            System.out.println(res);

    }
}
