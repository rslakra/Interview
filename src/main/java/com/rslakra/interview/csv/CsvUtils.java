package com.rslakra.interview.csv;

import com.rslakra.interview.csv.impl.CsvReaderImpl;
import com.rslakra.interview.csv.impl.CsvWriterImpl;
import com.rslakra.interview.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public enum CsvUtils {

    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvUtils.class);

    public static final String EMPTY = Utils.EMPTY;
    public static final char DOUBLE_QUOTE = '"';
    public static final String DOUBLE_QUOTE_STRING = DOUBLE_QUOTE + EMPTY;
    public static final String DEFAULT_DELIMITER = ",";
    public static final String DEFAULT_LINE_SEPARATOR = System.lineSeparator();


    public static String removeSkippingQuotes(String line) {
        return line.replace(DOUBLE_QUOTE_STRING + DOUBLE_QUOTE_STRING, DOUBLE_QUOTE_STRING);
    }

    /**
     * Removes the <code>"</code> of the <code>line</code>.
     *
     * @param line
     * @return
     */
    public static String removeQuotes(String line) {
        return line.substring(1, line.length() - 1);
    }

    /**
     * Returns true if the <code>line</code> is in <code>"</code> otherwise false.
     *
     * @param line
     * @return
     */
    public static boolean isInQuote(String line) {
        return line != null && line.startsWith(DOUBLE_QUOTE_STRING) && line.endsWith(DOUBLE_QUOTE_STRING);
    }

    /**
     * @param items
     * @param <T>
     * @return
     */
    public static <T> CsvWriter<T> buildWriter(Collection<T> items) {
        LOGGER.debug("buildWriter()");
        return new CsvWriterImpl<>(items);
    }

    /**
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> CsvReader<T> buildReader(Class<T> classType) {
        return new CsvReaderImpl<T>();
    }

    /**
     * Returns true if the <code>input</code> contains any <code>findWhat</code> otherwise false.
     *
     * @param input
     * @param findWhat
     * @return
     */
    public static boolean contains(String input, String... findWhat) {
        if (findWhat == null) {
            return true;
        }

        if (input != null) {
            for (String find : findWhat) {
                if (input.contains(find)) {
                    return true;
                }
            }
        }

        return false;
    }

}
