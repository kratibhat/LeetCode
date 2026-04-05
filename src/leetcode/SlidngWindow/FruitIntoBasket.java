package leetcode.SlidngWindow;
//You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
//
//You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
//
//You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
//Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
//Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
//Given the integer array fruits, return the maximum number of fruits you can pick.
public class FruitIntoBasket {
    public int totalFruit(int[] fruits){
        int[] freq = new int[100001];
        int l=0;
        int unique = 0;
        int max_len = 0;
        for(int r = 0; r< fruits.length ; r++){
            if(freq[fruits[r]] == 0)unique++;
            freq[fruits[r]]++;

            while(unique > 2){
                freq[fruits[l]]--;
                if(freq[fruits[l]] == 0)unique--;
                l++;
            }

            max_len = Math.max( max_len,r-l+1);
        }
        return max_len;
    }
    public static void main(String[] args) {
        FruitIntoBasket solution = new FruitIntoBasket();
        int[] fruits = {1, 2, 1};
        int []f={1,2,3,2,2};
        int r=solution.totalFruit(f);
        int result = solution.totalFruit(fruits);
        System.out.println("Maximum number of fruits that can be picked: " + result);
        System.out.println("Maximum number of fruits that can be picked: " + r);
    }
}
