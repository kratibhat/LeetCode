package leetcode.Binary.BSonAnswers;
//Given an array arr[] of integers, where each element arr[i] represents the number of pages in the i-th book. You also have an integer k representing the number of students. The task is to allocate books to each student such that:
//
//Each student receives atleast one book.
//Each student is assigned a contiguous sequence of books.
//No book is assigned to more than one student.
//All books must be allocated.
//The objective is to minimize the maximum number of pages assigned to any student. In other words, out of all possible allocations, find the arrangement where the student who receives the most pages still has the smallest possible maximum. If it is not possible to allocate books to all students, return -1;
//
//Note: Test cases are generated such that the answer always fits in a 32-bit integer.
//
//Examples:
//
//Input: arr[] = [12, 34, 67, 90], k = 2
//Output: 113
//Explanation: Allocation can be done in following ways:
//=> [12] and [34, 67, 90] Maximum Pages = 191
//=> [12, 34] and [67, 90] Maximum Pages = 157
//=> [12, 34, 67] and [90] Maximum Pages = 113.
//The third combination has the minimum pages assigned to a student which is 113.
//Input: arr[] = [15, 17, 20], k = 5
//Output: -1
//Explanation: Since there are more students than total books, it's impossible to allocate a book to each student.
public class AllocateMinimumPages {
    public int findPages(int[] arr, int k) {
        int n = arr.length;

        if (k > n) return -1;

        long low = 0, high = 0;

        for (int pages : arr) {
            low = Math.max(low, pages);
            high += pages;
        }

        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (canAllocate(arr, k, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return (int) ans;
    }

    private boolean canAllocate(int[] arr, int k, long maxPages) {
        int students = 1;
        long pages = 0;

        for (int book : arr) {
            if (pages + book <= maxPages) {
                pages += book;
            } else {
                students++;
                pages = book;

                if (students > k) {
                    return false;
                }
            }
        }

        return true;
    }
    public static void main(String []args){
        int []arr={12, 34, 67, 90};
        int k = 2;
        AllocateMinimumPages sol = new AllocateMinimumPages();
        System.out.println(sol.findPages(arr, k));
    }
}
