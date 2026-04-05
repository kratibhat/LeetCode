// java
import java.util.*;

public class PriorityQueueExamples {
    public static void main(String[] args) {
        creationAndBasicUsage();
        maxHeapAndSafeComparator();
        peekAndPoll();
        addAllExample();
        iterationOrderExample();
        nonComparableExample();
        comparatorOverflowExample();
    }

    static void creationAndBasicUsage() {
        System.out.println("\n--- Creation and basic add/offer ---");
        //allows duplicates, nulls not allowed
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // natural ordering (min-heap)
        minHeap.add(5);          // add (throws if full, not typical)
        minHeap.offer(2);        // offer (returns false if cannot be added)
        minHeap.addAll(Arrays.asList(7, 1, 3));
        System.out.println("Poll order (min-heap):");
        while (!minHeap.isEmpty()) System.out.print(minHeap.poll() + " ");
        System.out.println();
        System.out.println(minHeap);
    }

    static void maxHeapAndSafeComparator() {
        System.out.println("\n--- Max-heap (Comparator.reverseOrder / Integer.compare) ---");
        PriorityQueue<Integer> maxHeap1 = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); // safe
        maxHeap1.addAll(Arrays.asList(1, 9, 5));
        maxHeap2.addAll(Arrays.asList(1, 9, 5));
        System.out.print("maxHeap1.poll(): " + maxHeap1.poll());
        System.out.print("  maxHeap2.poll(): " + maxHeap2.poll());
        System.out.println();
    }

    static void peekAndPoll() {
        System.out.println("\n--- peek() vs poll() ---");
        PriorityQueue<Integer> pq = new PriorityQueue<>(Arrays.asList(4, 2, 6));
        System.out.println("peek() -> " + pq.peek()); // O(1), does not remove
        System.out.println("poll() -> " + pq.poll()); // O(log n), removes head
        System.out.println("after poll, peek() -> " + pq.peek());
    }

    static void addAllExample() {
        System.out.println("\n--- addAll(Collection) ---");
        List<Integer> many = Arrays.asList(8, 3, 10, 1, 7);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(many); // roughly O(m log(n+m)) overall for adding m elements
        System.out.println("poll order after addAll:");
        while (!pq.isEmpty()) System.out.print(pq.poll() + " ");
        System.out.println();
    }

    static void iterationOrderExample() {
        System.out.println("\n--- Iteration order is unspecified ---");
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(Arrays.asList(20, 5, 15, 10));
        System.out.print("Iterator order: ");
        for (Integer x : pq) System.out.print(x + " "); // unspecified order
        System.out.print("\nPoll order: ");
        while (!pq.isEmpty()) System.out.print(pq.poll() + " "); // heap order (sorted by priority)
        System.out.println();
    }

    static void nonComparableExample() {
        System.out.println("\n--- Elements must be Comparable or provide Comparator ---");
        class NoComparable {
            final int v;
            NoComparable(int v) { this.v = v; }
            public String toString() { return "NC(" + v + ")"; }
        }
        PriorityQueue<NoComparable> pq = new PriorityQueue<>();
        try {
            pq.add(new NoComparable(1));
            pq.add(new NoComparable(2)); // second add triggers comparison and will throw ClassCastException
        } catch (ClassCastException e) {
            System.out.println("Caught: " + e);
        }
        // Correct approach: supply a Comparator
        PriorityQueue<NoComparable> pq2 = new PriorityQueue<>(Comparator.comparingInt(n -> n.v));
        pq2.add(new NoComparable(1));
        pq2.add(new NoComparable(2));
        System.out.println("Using Comparator, poll -> " + pq2.poll());
    }

    static void comparatorOverflowExample() {
        System.out.println("\n--- Comparator overflow pitfall and safe alternative ---");
        Comparator<Integer> subComparator = (a, b) -> b - a; // can overflow
        Comparator<Integer> safeComparator = (a, b) -> Integer.compare(b, a);

        int a = Integer.MAX_VALUE;
        int b = -1;
        try {
            int bad = subComparator.compare(a, b);
            System.out.println("subComparator.compare(MAX_VALUE, -1) = " + bad + "  (may be incorrect due to overflow)");
        } catch (Exception e) {
            System.out.println("subComparator threw: " + e);
        }
        System.out.println("safeComparator.compare(MAX_VALUE, -1) = " + safeComparator.compare(a, b));
    }
}
