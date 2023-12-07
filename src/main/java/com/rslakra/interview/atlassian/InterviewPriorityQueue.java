package com.rslakra.interview.atlassian;

import java.util.Optional;
import java.util.PriorityQueue;

/**
 * @author Rohtash Lakra
 * @created 11/15/23 5:38 PM
 */
public class InterviewPriorityQueue implements MostPopular {

    final class Pair implements Comparable<Pair> {

        private Integer contentId;
        private int count;

        Pair(Integer contentId, int count) {
            this.contentId = contentId;
            this.count = count;
        }

        @Override
        public int compareTo(Pair pair) {
            return (pair.count - count); // max heap
        }

        @Override
        public String toString() {
            return (String.format("%d (%d)", contentId, count));
        }
    }

    private PriorityQueue<Pair> queue = new PriorityQueue<>();

    @Override
    public void increasePopularity(Integer contentId) {
        Optional<Pair> pairOptional = queue.stream().filter(p -> p.contentId == contentId).findFirst();
        if (pairOptional.isPresent()) {
            Pair pair = pairOptional.get();
            queue.remove(pair);
            pair.count++;
            queue.add(pair);
        } else {
            queue.add(new Pair(contentId, 1));
        }
    }

    // n log n
    @Override
    public Integer mostPopular() {
        Pair pair = queue.peek();
        System.out.println(queue);
        System.out.println(pair);
        if (pair.count <= 0) {
            return -1;
        }

        return pair.contentId;
    }

    @Override
    public void decreasePopularity(Integer contentId) {
        Optional<Pair> pairOptional = queue.stream().filter(p -> p.contentId == contentId).findFirst();
        if (pairOptional.isPresent()) {
            Pair pair = pairOptional.get();
            queue.remove(pair);
            pair.count--;
            queue.add(pair);
        } else {
            queue.add(new Pair(contentId, 0));
        }
    }

    public static void main(String[] args) {
        InterviewPriorityQueue popularityTracker = new InterviewPriorityQueue();
        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(8);
        System.out.println(popularityTracker.mostPopular());        // returns 7
        popularityTracker.increasePopularity(8);
        popularityTracker.increasePopularity(8);
        System.out.println(popularityTracker.mostPopular());        // returns 8
        popularityTracker.decreasePopularity(8);
        popularityTracker.decreasePopularity(8);
        System.out.println(popularityTracker.mostPopular());        // returns 7
        popularityTracker.decreasePopularity(7);
        popularityTracker.decreasePopularity(7);
        popularityTracker.decreasePopularity(8);
        System.out.println(popularityTracker.mostPopular());        // returns -1 since there is no content with popularity greater than 0
    }
}
