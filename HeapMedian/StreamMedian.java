package HeapMedian;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * This class provides median calculations for a stream of input data.
 * <p>
 *     It uses two priority queues to store the smaller half and larger half of the data encountered so far. The class
 *     has a constructor and two methods: insert(Integer i) and getMedian(). If there is an even number of data, the
 *     priority queues should be the same size; otherwise one of the priority queues will be slightly larger.
 * </p>
 * @author Adam Ziemans
 */
public class StreamMedian {
    // max heap for smaller half of data
    private PriorityQueue<Integer> maxHeap;
    // min heap for larger half of data
    private PriorityQueue<Integer> minHeap;

    public StreamMedian() {
        // initialize max heap with reverse order comparator
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        // initialize min heap with natural order comparator
        minHeap = new PriorityQueue<>();
    }

    /**
     * Inserts the next element of the stream to the data used for calculating the median.
     * @param i the next element of the stream
     */
    public void insert(Integer i) {
        // Determine which heap to insert i into
        if (maxHeap.isEmpty() || i < maxHeap.peek()) {
            maxHeap.offer(i);
        } else {
            minHeap.offer(i);
        }

        // Rebalance heaps if necessary to maintain the constraints
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    /**
     * Finds and returns the median of all the data inserted.
     * @return the median of all the data inserted
     */
    public double getMedian() {
        // If total number of elements is even
        if (maxHeap.size() == minHeap.size()) {
            // Return average of max from maxHeap and min from minHeap
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        // If total number of elements is odd
        else {
            // Return max from maxHeap (which has one more element than minHeap)
            return maxHeap.peek();
        }
    }
}
