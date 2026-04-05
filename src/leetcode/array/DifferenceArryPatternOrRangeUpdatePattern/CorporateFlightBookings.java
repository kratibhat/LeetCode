package leetcode.array.DifferenceArryPatternOrRangeUpdatePattern;
//There are n flights that are labeled from 1 to n.
//
//You are given an array of flight bookings bookings, where bookings[i] = [firsti, lasti, seatsi] represents a booking for flights firsti through lasti (inclusive) with seatsi seats reserved for each flight in the range.
//
//Return an array answer of length n, where answer[i] is the total number of seats reserved for flight i.
//
//
//
//Example 1:
//
//Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
//Output: [10,55,45,25,25]
//Explanation:
//Flight labels:        1   2   3   4   5
//Booking 1 reserved:  10  10
//Booking 2 reserved:      20  20
//Booking 3 reserved:      25  25  25  25
//Total seats:         10  55  45  25  25
//Hence, answer = [10,55,45,25,25]
//Example 2:
//
//Input: bookings = [[1,2,10],[2,2,15]], n = 2
//Output: [10,25]
//Explanation:
//Flight labels:        1   2
//Booking 1 reserved:  10  10
//Booking 2 reserved:      15
//Total seats:         10  25
//Hence, answer = [10,25]
public class CorporateFlightBookings {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] result = new int[n];
        /// 3ms
        for (int[] booking : bookings) {
            int first = booking[0] - 1; // Convert to 0-based index
            int last = booking[1] - 1;  // Convert to 0-based index
            int seats = booking[2];

            result[first] += seats; // Add seats to the starting flight
            if (last + 1 < n) {
                result[last + 1] -= seats; // Subtract seats from the flight after the last one
            }
        }

        // Calculate the prefix sum to get the total seats for each flight
        for (int i = 1; i < n; i++) {
            result[i] += result[i - 1];
        }

        return result;
    }
    public int[] corpFlightBookings1(int[][] bookings, int n) {
        // n flights
        // booking i-th [first, last, seats]
        //3ms
        int[] events = new int[n];

        for (int[] booking : bookings) {
            int first = booking[0] - 1;
            int last = booking[1];
            int seats = booking[2];

            events[first] += seats;

            if (last < n)
                events[last] -= seats;
        }

        for (int i = 1; i < n; i++) {
            events[i] += events[i - 1];
        }



        return events;
    }
    public static void main(String[] args) {
        CorporateFlightBookings solution = new CorporateFlightBookings();
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int n = 5;
        int[] result = solution.corpFlightBookings(bookings, n);
        System.out.print("Total seats reserved for each flight: ");
        for (int seats : result) {
            System.out.print(seats + " ");
        }
    }
}
//That is exactly the right intuition!
//
//Think of it like a bus route. Passengers get on at one stop (Flight first) and they must get off at their destination (Flight last).
//
//The "Empty Seat" Logic
//At first: The seats are occupied. You add them to your running total.
//
//Between first and last: The passengers are still on the plane. The seats remain occupied.
//
//After last: The flight is over for those passengers. They "get off" the plane.
//
//By doing -seats at last + 1, you are mathematically "emptying" those seats so they are available for the next set of bookings. If you didn't subtract them, the computer would think those passengers stayed on the plane for every single flight until the end of the line!
//
//The "Running Total" View
//Imagine the result array is a counter that follows the plane as it flies from City 0 to City 4.
//
//City 0: The counter sees +10. (Total: 10)
//
//City 1: The counter sees 0. (Total: 10)
//
//City 2: The counter sees -10. (Total: 0) — The seats are now empty.
//
//If another booking starts at City 2, it will add its own seats to that 0, and the counter will start climbing again.
//
//Why this is better than a loop?
//If you used a normal loop for every booking, you would have to visit every flight in the range:
//
//Booking 1 (Flights 1-100): 100 operations.
//
//Booking 2 (Flights 1-100): 100 operations.
//
//Total: 200 operations.