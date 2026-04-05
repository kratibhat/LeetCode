package leetcode.array.next.MontonicSTack;
//Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

//Example 1:
//
//Input: temperatures = [73,74,75,71,69,72,76,73]
//Output: [1,1,4,2,1,1,0,0]
//Example 2:
//
//Input: temperatures = [30,40,50,60]
//Output: [1,1,1,0]
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        /// 62 ms
        int n = temperatures.length;
        int[] answer = new int[n];
        java.util.Stack<Integer> stack = new java.util.Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                answer[index] = i - index;
            }
            stack.push(i);
        }

        return answer;
    }
    public int[] dailyTemperaturesOptimal(int[] temperatures) {
        /// 6ms
        int n = temperatures.length;
        int hottest = 0;
        int answer[] = new int[n];

        for (int currDay = n - 1; currDay >= 0; currDay--) {
            int currentTemp = temperatures[currDay];
            if (currentTemp >= hottest) {
                hottest = currentTemp;
                continue;
            }

            int days = 1;
            while (temperatures[currDay + days] <= currentTemp) {
                // Use information from answer to search for the next warmer day
                days += answer[currDay + days];
            }
            answer[currDay] = days;
        }

        return answer;
    }
    public static void main(String[] args) {
        DailyTemperatures solution = new DailyTemperatures();
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = solution.dailyTemperaturesOptimal(temperatures);
        System.out.print("Days to wait for a warmer temperature: ");
        for (int days : result) {
            System.out.print(days + " ");
        }
        // Output: [1,1,4,2,1,1,0,0]
    }
}
