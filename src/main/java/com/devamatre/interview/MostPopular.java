package com.devamatre.interview;

/**
 * @author Rohtash Lakra
 * @created 11/13/23 9:08 AM
 */
public interface MostPopular {

    void increasePopularity(Integer contentId);

    Integer mostPopular();

    void decreasePopularity(Integer contentId);
}
