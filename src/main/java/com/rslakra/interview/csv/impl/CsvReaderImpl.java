package com.rslakra.interview.csv.impl;

import com.rslakra.interview.csv.CsvLine;
import com.rslakra.interview.csv.CsvReader;
import com.rslakra.interview.csv.CsvUtils;
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

public class CsvReaderImpl<T> extends AbstractCsvImpl<T> implements CsvReader<T> {

    private String content;
    private boolean includeHeaders = true;
    private boolean includeFooters = true;
    private Function<CsvLine, T> mapper;
    private Predicate<String> filterLines = e -> true;


    /**
     * @param delimiter
     * @param lineSeparator
     */
    public CsvReaderImpl(String delimiter, String lineSeparator) {
        super(delimiter, lineSeparator);
    }

    /**
     * @param delimiter
     */
    public CsvReaderImpl(String delimiter) {
        super(delimiter);
    }

    /**
     *
     */
    public CsvReaderImpl() {
        super();
    }

    /**
     * @param delimiter
     * @return
     */
    @Override
    public CsvReader<T> delimiter(String delimiter) {
        setDelimiter(delimiter);
        return this;
    }

    @Override
    public CsvReader<T> lineSeparator(String lineSeparator) {
        setLineSeparator(lineSeparator);
        return this;
    }

    @Override
    public CsvReader<T> includeHeaders(boolean includeHeaders) {
        this.includeHeaders = includeHeaders;
        return this;
    }

    @Override
    public CsvReader<T> includeFooters(boolean includeFooters) {
        this.includeFooters = includeFooters;
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
    public CsvReader<T> filterLines(Predicate<String> filter) {
        if (filter != null) {
            filterLines = filter;
        }
        return this;
    }

    @Override
    public List<T> read() {
        if (Utils.isEmpty(getLineSeparator())) {
            throw new IllegalArgumentException("line separator cannot be empty, must provide a valid line separator");
        }

        if (mapper == null) {
            throw new IllegalArgumentException("line reader cannot be null");
        }

        return splitLines().stream()
                .map(this::splitCells)
                .map(CsvLine::new)
                .map(mapper)
                .collect(Collectors.toList());
    }

    private List<String> splitLines() {
        List<String> lines = Stream.of(content.split(getLineSeparator())).collect(Collectors.toList());

        boolean recheckFlag;

        do {
            recheckFlag = false;
            for (ListIterator<String> itr = lines.listIterator(); itr.hasNext(); ) {
                String line = itr.next();
                if (Utils.getCount(line, CsvUtils.DOUBLE_QUOTE) % 2 == 1) {
                    itr.remove();
                    String nextLine = itr.next();
                    itr.set(line + getLineSeparator() + nextLine);
                    recheckFlag = true;
                    // break for loop, redo the checking from the beginning
                    break;
                }
            }
        } while (recheckFlag);

        if (!includeHeaders) {
            lines.remove(0);
        }
        if (!includeFooters) {
            lines.remove(lines.size() - 1);
        }

        return lines.stream()
                .filter(filterLines)
                .collect(Collectors.toList());
    }

    /**
     * @param line
     * @return
     */
    private List<String> splitCells(String line) {
        List<String> cells = Stream.of(line.split(getDelimiter().toString())).collect(Collectors.toList());

        boolean recheckFlag;
        do {
            recheckFlag = false;
            for (ListIterator<String> itr = cells.listIterator(); itr.hasNext(); ) {
                String cell = itr.next();
                if (Utils.getCount(cell, CsvUtils.DOUBLE_QUOTE) % 2 == 1) {
                    itr.remove();
                    String nextCell = itr.next();
                    itr.set(cell + getDelimiter() + nextCell);
                    recheckFlag = true;
                    break;
                }
            }
        } while (recheckFlag);

        return cells.stream()
                .map(cell -> CsvUtils.isInQuote(cell) ? CsvUtils.removeQuotes(cell) : cell)
                .map(CsvUtils::removeSkippingQuotes)
                .collect(Collectors.toList());
    }

}
