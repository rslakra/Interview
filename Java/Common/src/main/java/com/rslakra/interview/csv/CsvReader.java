package com.rslakra.interview.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CsvReader<T> {

    /**
     * The <code>delimiter</code> of the csv contents.
     *
     * @param delimiter
     * @return
     */
    CsvReader<T> delimiter(String delimiter);

    /**
     * The <code>lineSeparator</code> of the csv contents.
     *
     * @param lineSeparator
     * @return
     */
    CsvReader<T> lineSeparator(String lineSeparator);

    /**
     * The <code>includeHeaders</code> of the csv contents.
     *
     * @param includeHeaders
     * @return
     */
    CsvReader<T> includeHeaders(boolean includeHeaders);

    /**
     * The <code>includeFooters</code> of the csv contents.
     *
     * @param includeFooters
     * @return
     */
    CsvReader<T> includeFooters(boolean includeFooters);

    /**
     * The <code>content</code> of the csv contents.
     *
     * @param content
     * @return
     */
    CsvReader<T> content(String content);

    /**
     * The <code>file</code> of the csv contents.
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    CsvReader<T> content(File file) throws FileNotFoundException, IOException;

    /**
     * The <code>path</code> of the csv contents.
     *
     * @param path
     * @return
     * @throws IOException
     */
    CsvReader<T> content(Path path) throws IOException;

    /**
     * The <code>mapper</code> of the csv contents.
     *
     * @param mapper
     * @return
     */
    CsvReader<T> mapper(Function<CsvLine, T> mapper);

    /**
     * Filters the lines based on the <code>filter</code>.
     *
     * @param filter
     * @return
     */
    CsvReader<T> filterLines(Predicate<String> filter);

    /**
     * Reads the contents of the csv file.
     *
     * @return
     */
    List<T> read();
}
