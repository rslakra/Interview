package com.rslakra.interview.csv;

import com.rslakra.interview.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CsvReaderImpl<T> implements CsvReader<T> {

    private String content;
    private boolean includeFirstLine = true;
    private boolean includeLastLine = true;
    private Function<CsvLine, T> mapper;
    private Character delimiter = CsvUtils.DEFAULT_DELIMITER;
    private String lineSeparator = CsvUtils.DEFAULT_LINE_SEPARATOR;
    private Predicate<String> csvLineFilter = e -> true;

    @Override
    public CsvReader<T> delimiter(char delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    @Override
    public CsvReader<T> lineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
        return this;
    }

    @Override
    public CsvReader<T> includeFirstLine(boolean includeFirstLine) {
        this.includeFirstLine = includeFirstLine;
        return this;
    }

    @Override
    public CsvReader<T> includeLastLine(boolean includeLastLine) {
        this.includeLastLine = includeLastLine;
        return this;
    }

    @Override
    public CsvReader<T> content(String content) {
        this.content = content;
        return this;
    }

    @Override
    public CsvReader<T> content(File file) throws IOException {
        return content(file.toPath());
    }

    @Override
    public CsvReader<T> content(Path path) throws IOException {
        return content(new String(Files.readAllBytes(path)));
    }

    @Override
    public CsvReader<T> mapper(Function<CsvLine, T> mapper) {
        this.mapper = mapper;
        return this;
    }

    @Override
    public CsvReader<T> csvLineFilter(Predicate<String> filter) {
        if (filter != null) {
            csvLineFilter = filter;
        }
        return this;
    }

    @Override
    public List<T> read() {
        if (Utils.isEmpty(lineSeparator)) {
            throw new IllegalArgumentException("line separator cannot be empty, must provide a valid line separator");
        }

        if (mapper == null) {
            throw new IllegalArgumentException("line reader cannot be null");
        }

        return splitLines().stream().map(this::splitCells).map(CsvLine::new).map(mapper).collect(Collectors.toList());
    }

    private List<String> splitLines() {
        List<String> lines = Stream.of(content.split(lineSeparator)).collect(Collectors.toList());

        boolean recheckFlag;

        do {
            recheckFlag = false;
            for (ListIterator<String> itr = lines.listIterator(); itr.hasNext(); ) {
                String line = itr.next();
                if (Utils.getCount(line, CsvUtils.DOUBLE_QUOTE) % 2 == 1) {
                    itr.remove();
                    String nextLine = itr.next();
                    itr.set(line + lineSeparator + nextLine);
                    recheckFlag = true;
                    // break for loop, redo the checking from the beginning
                    break;
                }
            }
        } while (recheckFlag);

        if (!includeFirstLine) {
            lines.remove(0);
        }
        if (!includeLastLine) {
            lines.remove(lines.size() - 1);
        }

        return lines.stream().filter(csvLineFilter).collect(Collectors.toList());
    }

    private List<String> splitCells(String line) {
        List<String> cells = Stream.of(line.split(delimiter.toString())).collect(Collectors.toList());

        boolean recheckFlag;
        do {
            recheckFlag = false;
            for (ListIterator<String> itr = cells.listIterator(); itr.hasNext(); ) {
                String cell = itr.next();
                if (Utils.getCount(cell, CsvUtils.DOUBLE_QUOTE) % 2 == 1) {
                    itr.remove();
                    String nextCell = itr.next();
                    itr.set(cell + delimiter + nextCell);
                    recheckFlag = true;
                    break;
                }
            }
        } while (recheckFlag);

        return cells.stream().map(cell -> isInQuote(cell) ? removeQuotes(cell) : cell)
            .map(CsvReaderImpl::removeSkippingQuotes).collect(Collectors.toList());
    }

    private static String removeSkippingQuotes(String line) {
        return line.replace(CsvUtils.DOUBLE_QUOTE_STRING + CsvUtils.DOUBLE_QUOTE_STRING, CsvUtils.DOUBLE_QUOTE_STRING);
    }

    /**
     * @param line
     * @return
     */
    private static String removeQuotes(String line) {
        return line.substring(1, line.length() - 1);
    }

    /**
     * @param line
     * @return
     */
    private static boolean isInQuote(String line) {
        return line != null && line.startsWith(CsvUtils.DOUBLE_QUOTE_STRING) && line.endsWith(
            CsvUtils.DOUBLE_QUOTE_STRING);
    }

}