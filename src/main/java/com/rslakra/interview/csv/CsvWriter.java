package com.rslakra.interview.csv;

import java.util.List;
import java.util.function.Function;

public interface CsvWriter<T> {

    CsvWriter<T> delimiter(char delimiter);

    CsvWriter<T> lineSeparator(String lineSeparator);

    CsvWriter<T> header(Object... headers);

    CsvWriter<T> header(List<Object> headers);

    CsvWriter<T> footer(Object... footers);

    CsvWriter<T> footer(List<Object> footers);

    CsvWriter<T> mapper(Function<T, List<Object>> mapper);

    String generate();
}
