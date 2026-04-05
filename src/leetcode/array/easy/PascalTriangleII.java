// java
package leetcode.array.easy;

import java.util.ArrayList;
import java.util.List;
//pascal's rule: C(n, k) = C(n-1, k-1) + C(n-1, k)
public class PascalTriangleII {
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i <= rowIndex; i++) {
            row.add(1); // append new element (rightmost 1)
            for (int j = i - 1; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1)); // update in-place from right to left
            }
        }
        return row;
    }

    public static void main(String[] args) {
        System.out.println(getRow(3)); // [1, 3, 3, 1]
        System.out.println(getRow(0)); // [1]
        System.out.println(getRow(1)); // [1, 1]
    }
}
