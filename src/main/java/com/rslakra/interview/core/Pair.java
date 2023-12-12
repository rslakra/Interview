package com.rslakra.interview.core;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 12/7/23 5:21 PM
 */
@Getter
@Setter
public class Pair implements Comparable<Pair> {

    private Integer contentId;
    private int count;

    /**
     * @param contentId
     * @param count
     */
    public Pair(Integer contentId, int count) {
        this.contentId = contentId;
        this.count = count;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(getContentId());
    }

    /**
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || other != Pair.class) {
            return false;
        }

        Pair pair = (Pair) other;
        return (getContentId().compareTo(pair.getContentId()) == 0);
    }

    /**
     * @param pair the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Pair pair) {
        return (pair.count - count); // max heap
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return (String.format("%d (%d)", contentId, count));
    }
}
