package com.rslakra.interview.atlassian;

import com.rslakra.interview.core.Pair;

import java.util.Optional;
import java.util.PriorityQueue;

/**
 * @author Rohtash Lakra
 * @created 11/15/23 5:38 PM
 */
public class InterviewPriorityQueue implements MostPopular {

    private PriorityQueue<Pair> queue = new PriorityQueue<>();

    @Override
    public void increasePopularity(Integer contentId) {
        Optional<Pair> pairOptional = queue.stream()
            .filter(p -> p.getContentId() == contentId)
            .findFirst();

        if (pairOptional.isPresent()) {
            Pair pair = pairOptional.get();
            queue.remove(pair);
            pair.setCount(pair.getCount() + 1);
            queue.add(pair);
        } else {
            queue.add(new Pair(contentId, 1));
        }
    }

    // n log n
    @Override
    public Integer mostPopular() {
        Pair pair = queue.peek();
        if (pair.getCount() <= 0) {
            return -1;
        }

        return pair.getContentId();
    }

    @Override
    public void decreasePopularity(Integer contentId) {
        Optional<Pair> pairOptional = queue.stream()
            .filter(p -> p.getContentId() == contentId)
            .findFirst();
        if (pairOptional.isPresent()) {
            Pair pair = pairOptional.get();
            queue.remove(pair);
            pair.setCount(pair.getCount() - 1);
            queue.add(pair);
        } else {
            queue.add(new Pair(contentId, 0));
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        MostPopular instance = new InterviewPriorityQueue();
        instance.increasePopularity(7);
        instance.increasePopularity(7);
        instance.increasePopularity(8);
        // returns 7
        System.out.println(instance.mostPopular());
        instance.increasePopularity(8);
        instance.increasePopularity(8);
        // returns 8
        System.out.println(instance.mostPopular());
        instance.decreasePopularity(8);
        instance.decreasePopularity(8);
        // returns 7
        System.out.println(instance.mostPopular());
        instance.decreasePopularity(7);
        instance.decreasePopularity(7);
        instance.decreasePopularity(8);
        // returns -1 since there is no content with popularity greater than 0
        System.out.println(instance.mostPopular());
    }
}
