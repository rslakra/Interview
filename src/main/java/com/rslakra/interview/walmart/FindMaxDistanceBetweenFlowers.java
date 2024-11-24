package com.rslakra.interview.walmart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindMaxDistanceBetweenFlowers {

    /**
     * Given an array of flowers, find the max distance between two flowers
     * of the same type.
     * Assumption : The array will always at least contain one or more
     * duplicate flowers.
     *
     * @param flowers
     * @return
     */
    public int getMaxDistance(String[] flowers) {
        if (flowers == null || flowers.length == 0) {
            return 0;
        }

        Map<String, List<Integer>> map = new HashMap<>(flowers.length);
        int maxDistance = Integer.MIN_VALUE;
        int index = 0;
        for (String flower : flowers) {
            List<Integer> indexList = map.getOrDefault(flower, new ArrayList<>());
            if (!indexList.isEmpty()) {
                maxDistance = Math.max(maxDistance, index - indexList.get(0));
            }
            indexList.add(index);
            map.put(flower, indexList);
            index++;
        } // end of for loop

//        System.out.println(map);
        return maxDistance;
    }  // end of method

}

