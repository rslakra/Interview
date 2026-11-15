package com.rslakra.interview.atlassian;

/**
 * @author Rohtash Lakra
 * @created 11/13/23 9:08 AM
 */
public interface MostPopular {

    void increasePopularity(Integer contentId);

    Integer mostPopular();

    void decreasePopularity(Integer contentId);
}
