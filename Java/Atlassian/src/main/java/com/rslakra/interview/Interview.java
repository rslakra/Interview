package com.rslakra.interview;

import com.rslakra.interview.core.Pair;

import java.util.Optional;
import java.util.PriorityQueue;

/**
 * @author Rohtash Lakra
 * @created 12/7/23 5:19 PM
 */
public class Interview {

    private PriorityQueue<Pair> queue = new PriorityQueue<>();

    /**
     * @param contentId
     * @return
     */
    private Optional<Pair> findById(Integer contentId) {
        return queue.stream()
            .filter(entry -> entry.getContentId().equals(contentId))
            .findFirst();
    }

    /**
     * @param contentId
     */
    public void increasePopularity(Integer contentId) {
        Optional<Pair> pairOptional = findById(contentId);
        if (pairOptional.isPresent()) {
            Pair pair = pairOptional.get();
            queue.remove(pair);
            pair.setCount(pair.getCount() + 1);
            queue.add(pair);
        } else {
            queue.add(new Pair(contentId, 1));
        }
    }

    /**
     * @return
     */
    public Integer mostPopular() {
        Pair pair = queue.peek();
        if (pair.getCount() <= 0) {
            return -1;
        }

        return pair.getContentId();
    }

    /**
     * @param contentId
     */
    public void decreasePopularity(Integer contentId) {
        Optional<Pair> pairOptional = findById(contentId);
        if (pairOptional.isPresent()) {
            Pair pair = pairOptional.get();
            queue.remove(pair);
            pair.setCount(pair.getCount() - 1);
            queue.add(pair);
        } else {
            queue.add(new Pair(contentId, 0));
        }
    }
}
