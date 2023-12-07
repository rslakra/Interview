package com.rslakra.interview.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements ArrayList<String> with safe {@link ArrayList#get(int)}<br> does not throw
 * {@link IndexOutOfBoundsException}
 *
 * @author iferdou
 */
public class CsvLine extends ArrayList<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvLine.class);

    /**
     * @param cells
     */
    public CsvLine(List<String> cells) {
        super(cells);
    }

    /**
     * return null in case of {@link IndexOutOfBoundsException}
     */
    @Override
    public String get(int index) {
        try {
            return super.get(index);
        } catch (IndexOutOfBoundsException ex) {
            LOGGER.error(ex.getMessage(), ex);
            return null;
        }
    }

}