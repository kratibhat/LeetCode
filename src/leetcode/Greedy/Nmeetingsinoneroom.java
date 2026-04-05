package leetcode.Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//Gemini said
//The approach used to solve the N Meetings in One Room (or Activity Selection) problem is a Greedy Approach.
//
//Specifically, it follows a Greedy Choice Property where making a locally optimal choice (picking the meeting that ends earliest) leads to the globally optimal solution (the maximum number of meetings).
//
//Why is this "Greedy"?
//It is greedy because at every step, you make the best possible choice right now without worrying about future meetings. You simply ask: "Which meeting finishes first and doesn't clash with my current schedule?"
public class Nmeetingsinoneroom {
    class Meeting {
        int start, end;
        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public int maxMeetings(int start[], int end[]) {
        int n = start.length;
        List<Meeting> meetings = new ArrayList<>();

        // 1. Create Meeting objects
        for (int i = 0; i < n; i++) {
            meetings.add(new Meeting(start[i], end[i]));
        }

        // 2. Sort based on End Time
        Collections.sort(meetings, (a, b) -> a.end - b.end);

        // 3. Select meetings greedily
        int count = 1;
        int lastEndTime = meetings.get(0).end;

        for (int i = 1; i < n; i++) {
            // Check if meeting starts AFTER the last one ended
            if (meetings.get(i).start > lastEndTime) {
                count++;
                lastEndTime = meetings.get(i).end;
            }
        }

        return count;
    }
    public static void main(String[] args) {
        Nmeetingsinoneroom solution = new Nmeetingsinoneroom();
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};
        int result = solution.maxMeetings(start, end);
        System.out.println("Maximum number of meetings that can be held: " + result);
    }
}
