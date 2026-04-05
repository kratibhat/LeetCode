package leetcode.array.easy;

import java.util.Arrays;

//There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
//
//You are giving candies to these children subjected to the following requirements:
//
//Each child must have at least one candy.
//Children with a higher rating get more candies than their neighbors.
//Return the minimum number of candies you need to have to distribute the candies to the children.
//
//
//
//Example 1:
//
//Input: ratings = [1,0,2]
//Output: 5
//Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
//Example 2:
public class Candy {
public static void main(String[] args) {
    Candy solution = new Candy();
    int[] ratings = {1, 0, 2};
    int result = solution.candy(ratings);
    System.out.println("Minimum candies needed: " + result); // Output: 5
}
        public int candy(int[] ratings) {
            int []a=new int[ratings.length];
            Arrays.fill(a,1);  //each child gets at least one candy
            for(int i=1;i<ratings.length;i++)
            {
                if(ratings[i]>ratings[i-1])
                {
                    a[i]=a[i-1]+1;
                }

            }
            for(int i=ratings.length-2;i>=0;i--)
            {
                if(ratings[i]>ratings[i+1])
                {
                    {
                        a[i] = Math.max(a[i], a[i + 1] + 1);

                    }
                }
            }
            int t=0;
            for(int b:a)
            {
                t+=b;
            }
            return t;
        }

}
//One pass ensures left neighbors are correct
//
//The other pass ensures right neighbors are correct
//
//Math.max() ensures we do not break the previous constraint while fixing the new one

//ratings = [1, 3, 4, 5, 2]
//Left → right:
//
//Copy code
//1 2 3 4 1
//Right → left:
//
//vbnet
//Copy code
//At index 3 (5 > 2):
//    a[3] = max(4, 1 + 1 = 2) = 4     <-- important!
//
//If we DON'T use max:
//    a[3] becomes 2, which BREAKS the increasing order from left side.
//This would violate the rule that each higher rating must have more candies.