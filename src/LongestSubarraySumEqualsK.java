
//logic :
//PREFIXSUM[I] - PREFIXSUM[J] = K
//[5,2,2,5,1,1,1,1,1,4]
//at each index we will calculate prefix sum and check if (prefixsum - k)
//5-4=1C(5,7,9 all r sum of each value)
//7-4=3  (observe 5 on left matches with 5 on right when we plot graph of each sum and remainder) 4
//9-4=5
//14-4=10
//15-4=11
//16-4=12
//17-4=13
//18-4=14
//22-4=18

public class LongestSubarraySumEqualsK {

}
