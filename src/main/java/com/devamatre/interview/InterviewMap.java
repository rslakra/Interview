package com.devamatre.interview;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Imagine you are given a stream of content ids along with an associated action to be performed on them. Example of
 * contents are video, pages, posts etc. There can be two actions associated with a content id:
 * <p>
 * increasePopularity → increases the popularity of the content by 1. The popularity increases when someone comments on
 * the content or likes the content
 * <p>
 * decreasePopularity → decreases the popularity of the content by 1. The popularity decreases when a spam bot's/users
 * comments are deleted from the content or its likes are removed from the content
 * <p>
 * content ids are positive integers
 * <p>
 * Implement a class that can return the mostPopular content id at any time while consuming the stream of content ids
 * and its associated action. If there are no contentIds with popularity greater than 0, return -1
 * <p>
 * <p>
 *
 * <pre>
 *      [1, increase], [1, increase], [1, increase], [1, increase]
 *      [2, increase], [2, increase], [2, increase], [2, increase], [2, increase], [2, increase] Result: [1, 4] Result: [2, 6]
 *      [3, increase], [3, increase], [3, increase], [3, increase]
 *   </pre>
 *
 * <p>
 * <p>
 * Map<ID, Counter>
 *
 * @author Rohtash Lakra
 * @created 11/12/23 5:21 PM
 */
public class InterviewMap implements MostPopular {

    private Map<Integer, Integer> popular = new HashMap<>();

    @Override
    public void increasePopularity(Integer contentId) {
        popular.put(contentId, popular.getOrDefault(contentId, 0) + 1);
    }

    // n log n
    @Override
    public Integer mostPopular() {
        // sort
        popular = sort(popular.entrySet());
        Map.Entry<Integer, Integer> entry = popular.entrySet().iterator().next();
        if (entry.getValue() <= 0) {
            return -1;
        }

        return entry.getKey();
    }

    @Override
    public void decreasePopularity(Integer contentId) {
        popular.put(contentId, popular.getOrDefault(contentId, 0) - 1);
    }

    static class MapSorter implements Comparator<Map.Entry<Integer, Integer>> {

        @Override
        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
            int result = o2.getValue() - o1.getValue();
            if (result == 0) {
                result = o1.getKey() - o2.getKey();
            }
            return result;
        }
    }

    /**
     * @param entries
     * @return
     */
    private Map<Integer, Integer> sort(Set<Map.Entry<Integer, Integer>> entries) {
        List<Map.Entry<Integer, Integer>> allEntries = new ArrayList<>(entries);
        allEntries.sort(new MapSorter());
        final Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : allEntries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }


    public static void main(String[] args) {

        InterviewMap popularityTracker = new InterviewMap();
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
        System.out.println(
            popularityTracker.mostPopular());        // returns -1 since there is no content with popularity greater than 0
    }
}
