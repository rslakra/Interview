package com.rslakra.interview.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CsvReader<T> {

    CsvReader<T> delimiter(char delimiter);

    CsvReader<T> lineSeparator(String lineSeparator);

    CsvReader<T> includeFirstLine(boolean includeFirstLine);

    CsvReader<T> includeLastLine(boolean includeLastLine);

    CsvReader<T> content(String content);

    CsvReader<T> content(File file) throws FileNotFoundException, IOException;

    CsvReader<T> content(Path path) throws IOException;

    CsvReader<T> mapper(Function<CsvLine, T> mapper);

    CsvReader<T> csvLineFilter(Predicate<String> filter);

    List<T> read();
}
