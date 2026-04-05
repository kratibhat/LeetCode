package leetcode.array.next.circular;
/// Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.
///
/// A subarray is a contiguous part of an array.
///
///
///
/// Example 1:
///
/// Input: nums = [1], k = 1
/// Output: 1
/// Example 2:
///
/// Input: nums = [1,2], k = 4
/// Output: -1
/// Example 3:
///
/// Input: nums = [2,-1,2], k = 3
/// Output: 3
///
///
/// Constraints:
///
/// 1 <= nums.length <= 105

import java.util.ArrayDeque;
import java.util.Deque;

///
public class ShortestSubarraywithsumatleastK {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSums = new long[n + 1];
        //By making the array size n + 1:
        //
        //prefixSums[0] represents the sum of 0 elements.
        //
        //prefixSums[1] represents the sum of the first 1 element.
        //
        //prefixSums[n] represents the sum of all n elements.
        //
        //This makes the math much cleaner: the sum of a subarray from nums[i] to nums[j] is simply prefixSums[j + 1] - prefixSums[i].
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            while (!deque.isEmpty() && prefixSums[i] - prefixSums[deque.peekFirst()] >= k) {
                minLength = Math.min(minLength, i - deque.pollFirst());
            }
            /// This step is crucial because it maintains the Monotonic (Increasing) property of
            ///your Deque. In simple terms, it kicks out candidates that are "useless" or "worse"
            ///than the current one.To understand why a candidate becomes "useless," we have to
            ///look at what makes a prefix sum a "good" candidate for the starting point of a subarray.1.
            ///What makes a "Better" starting point?Remember, we are looking for two indices $i$ and $j$ where $P[j] - P[i] \geq K$. We want to minimize the length $j - i$.A "better" starting point ($i$) is one that:Is smaller in value: If $P[i]$ is small, it’s easier for $P[j] - P[i]$ to reach the target $K$.Is further to the right: If $i$ is larger (closer to $j$), the resulting subarray length ($j - i$) is shorter.2. The Logic of the "Kick Out"Imagine your Deque has an old index peekLast() and you are currently looking at a new index i.If prefixSums[i] <= prefixSums[deque.peekLast()], then the new index i is better in both ways:Value: It is smaller than (or equal to) the old value, making it easier to satisfy the $K$ sum.Distance: It is further to the right, meaning it will always produce a shorter subarray than the old index could for any future $j$.Since the new index i is superior in every way, the old index at peekLast() will never be part of the "shortest" subarray from this point forward. It is mathematically "dead wood." We remove it to keep our search space clean and efficient.3. A Concrete ExampleSuppose $K = 3$ and we are at index $j = 10$.Old candidate in Deque: Index 2 with value 10 (prefixSums[2] = 10).New candidate: Index 5 with value 8 (prefixSums[5] = 8).If a future $P[j]$ is 13:Using index 2: $13 - 10 = 3$ (Success!). Length = $10 - 2 = 8$.Using index 5: $13 - 8 = 5$ (Success!). Length = $10 - 5 = 5$.
            while (!deque.isEmpty() && prefixSums[i] <= prefixSums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
    public int shortestSubarrayOpt(int[] nums, int k) {
        /// 11ms
        int n = nums.length;
        int[] deque = new int[n + 1];
        long[] pre = new long[n + 1];
        long sum = 0L;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            pre[i + 1] = sum;
        }
        int head = 0;
        int tail = 0;
        int result = n + 2;
        int r = 0;
        for (int i = 1; i <= n; i++) {
            while (head <= tail && pre[deque[head]] <= pre[i] - k) {
                result = Math.min(result, i - deque[head]);
                head++;
            }
            while (tail >= head && pre[deque[tail]] >= pre[i]) {
                tail--;
            }
            deque[++tail] = i;
        }
        return result == n + 2 ? -1 : result;
    }

public static void main(String[] args) {
        ShortestSubarraywithsumatleastK solution = new ShortestSubarraywithsumatleastK();
        int[] nums = {2, -1, 2};
        int k = 3;
        int result = solution.shortestSubarray(nums, k);
        System.out.println("Length of shortest subarray: " + result); // Output: 3
    }
}
////Think of the Deque as a "Waiting Room" for people who want to be the start of a subarray.
//The index i in your loop is the end of a potential subarray.To get the shortest subarray,
// you want the start and the end to be as close together as possible.1.
// Why peekFirst? (Checking the Oldest Candidates)The Deque stores indices in increasing order
// ($0, 1, 2, 3 \dots$). This means the oldest indices—the ones furthest away from our current
//
// position i—are at the front (First).The Logic:You look at the person at the front (peekFirst)
// .If the sum between that person and your current position is already $\ge K$, you've found a valid subarray!Why pollFirst? Once a "start" index at the front has satisfied the condition for the current i, we kick it out (pollFirst). We do this because any future i (like $i+1, i+2$) would be further away, making the subarray longer. Since we only care about the shortest subarray, that starting index has already done its best possible job.2. Why offerLast? (Adding the Newest Candidate)Every index i must be added to the Deque because it might be the perfect starting point for some future j.The Logic:i is the newest index. In a line that goes from smallest to largest, the newest always goes to the back.Before we put i in, we use that other while loop to kick out people from the Last (back) who are "weaker" than i.Once the "waiting room" is cleaned up, we use offerLast to put i at the end of the line.