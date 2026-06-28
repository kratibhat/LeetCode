package leetcode.array.KadaneAlgo;

import java.util.PriorityQueue;

public class KthLargestSumContiguousSubarray {
    public int kthLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int sum = 0;

            for (int j = i; j < n; j++) {
                sum += arr[j];

                if (pq.size() < k) {
                    pq.offer(sum);
                } else if (sum > pq.peek()) {
                    pq.poll();
                    pq.offer(sum);
                }
            }
        }

        return pq.peek();
    }
    public static void main(String[]args){
        int []arr={3, 2, 1};
        int k=2;
        System.out.println(new KthLargestSumContiguousSubarray().kthLargest(arr,k));
    }
}
