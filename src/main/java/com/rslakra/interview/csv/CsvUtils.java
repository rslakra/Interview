package com.rslakra.interview.csv;

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
    public static final char DEFAULT_DELIMITER = ',';
    public static final String DEFAULT_LINE_SEPARATOR = System.lineSeparator();

    /**
     * @param items
     * @param <T>
     * @return
     */
    public static <T> CsvWriter<T> buildWriter(Collection<T> items) {
        LOGGER.debug("buildWriter()");
        return new CsvWriterImpl<T>(items);
    }

    /**
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> CsvReader<T> buildReader(Class<T> classType) {
        return new CsvReaderImpl<T>();
    }

}
