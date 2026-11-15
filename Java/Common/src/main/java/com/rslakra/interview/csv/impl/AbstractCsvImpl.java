package com.rslakra.interview.csv.impl;

import com.rslakra.interview.csv.CsvUtils;

/**
 * @author Rohtash Lakra
 * @created 12/12/23 11:37 AM
 */
public abstract class AbstractCsvImpl<T> {

    private String delimiter = CsvUtils.DEFAULT_DELIMITER;
    private String lineSeparator = CsvUtils.DEFAULT_LINE_SEPARATOR;

    /**
     * @param delimiter
     * @param lineSeparator
     */
    public AbstractCsvImpl(String delimiter, String lineSeparator) {
        this.delimiter = delimiter;
        this.lineSeparator = lineSeparator;
    }

    /**
     * @param delimiter
     */
    public AbstractCsvImpl(String delimiter) {
        this(delimiter, CsvUtils.DEFAULT_LINE_SEPARATOR);
    }

    /**
     *
     */
    public AbstractCsvImpl() {
        this(CsvUtils.DEFAULT_DELIMITER, CsvUtils.DEFAULT_LINE_SEPARATOR);
    }

    /**
     * Returns the <code>delimiter</code>.
     *
     * @return
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * The <code>delimiter</code> to be set.
     *
     * @param delimiter
     */
    protected void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Returns the <code>lineSeparator</code>.
     *
     * @return
     */
    public String getLineSeparator() {
        return lineSeparator;
    }

    /**
     * The <code>lineSeparator</code> to be set.
     *
     * @param lineSeparator
     */
    protected void setLineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
    }
}
