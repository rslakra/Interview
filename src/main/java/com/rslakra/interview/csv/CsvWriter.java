package com.rslakra.interview.csv;

import java.util.List;
import java.util.function.Function;

public interface CsvWriter<T> {

    /**
     * The <code>delimiter</code> of the csv contents.
     *
     * @param delimiter
     * @return
     */
    CsvWriter<T> delimiter(String delimiter);

    /**
     * The <code>lineSeparator</code> of the csv contents.
     *
     * @param lineSeparator
     * @return
     */
    CsvWriter<T> lineSeparator(String lineSeparator);

    /**
     * The <code>headers</code> of the contents.
     *
     * @param headers
     * @return
     */
    CsvWriter<T> header(Object... headers);

    /**
     * The <code>headers</code> of the contents.
     *
     * @param headers
     * @return
     */
    CsvWriter<T> header(List<Object> headers);

    /**
     * The <code>footers</code> of the contents.
     *
     * @param footers
     * @return
     */
    CsvWriter<T> footer(Object... footers);

    /**
     * The <code>footers</code> of the contents.
     *
     * @param footers
     * @return
     */
    CsvWriter<T> footer(List<Object> footers);

    /**
     * The <code>objectMapper</code> of the contents.
     *
     * @param objectMapper
     * @return
     */
    CsvWriter<T> mapper(Function<T, List<Object>> objectMapper);

    /**
     * Generator.
     *
     * @return
     */
    String generate();
}
