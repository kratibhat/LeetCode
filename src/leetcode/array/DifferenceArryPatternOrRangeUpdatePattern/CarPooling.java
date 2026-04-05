package leetcode.array.DifferenceArryPatternOrRangeUpdatePattern;
//There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
//
//You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.
//
//Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
//
//
//
//Example 1:
//
//Input: trips = [[2,1,5],[3,3,7]], capacity = 4
//Output: false
//Example 2:
//
//Input: trips = [[2,1,5],[3,3,7]], capacity = 5
//Output: true
//
public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] passengers = new int[1001];
        for (int[] trip : trips) {
            passengers[trip[1]] += trip[0]; // Pick up passengers
            passengers[trip[2]] -= trip[0]; // Drop off passengers
        }
        int currentPassengers = 0;
        for (int passengerChange : passengers) {
            currentPassengers += passengerChange;
            if (currentPassengers > capacity) {
                return false; // Capacity exceeded
            }
        }
        return true; // All trips can be accommodated
    }
    public static void main(String[] args) {
        CarPooling solution = new CarPooling();
        int[][] trips1 = {{2,1,5},{3,3,7}};
        int capacity1 = 4;
        System.out.println("Can accommodate trips1: " + solution.carPooling(trips1, capacity1)); // Output: false

        int[][] trips2 = {{2,1,5},{3,3,7}};
        int capacity2 = 5;
        System.out.println("Can accommodate trips2: " + solution.carPooling(trips2, capacity2)); // Output: true
    }
}
