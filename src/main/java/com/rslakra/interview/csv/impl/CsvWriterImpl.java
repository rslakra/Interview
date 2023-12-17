package com.rslakra.interview.csv.impl;

import com.rslakra.interview.csv.CsvUtils;
import com.rslakra.interview.csv.CsvWriter;
import com.rslakra.interview.utils.Utils;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvWriterImpl<T> extends AbstractCsvImpl<T> implements CsvWriter<T> {

    private final Collection<T> content;
    private List<Object> header;
    private List<Object> footer;
    private Function<T, List<Object>> mapper;

    /**
     * @param content
     */
    public CsvWriterImpl(Collection<T> content) {
        super();
        this.content = content;
    }

    /**
     * @param delimiter
     * @return
     */
    @Override
    public CsvWriter<T> delimiter(String delimiter) {
        setDelimiter(delimiter);
        return this;
    }

    /**
     * @param lineSeparator
     * @return
     */
    @Override
    public CsvWriter<T> lineSeparator(String lineSeparator) {
        setLineSeparator(lineSeparator);
        return this;
    }

    @Override
    public CsvWriter<T> header(Object... headers) {
        return header(Stream.of(headers).collect(Collectors.toList()));
    }

    @Override
    public CsvWriter<T> header(List<Object> headers) {
        header = headers;
        return this;
    }

    @Override
    public CsvWriter<T> footer(Object... footers) {
        return footer(Stream.of(footers).collect(Collectors.toList()));
    }

    @Override
    public CsvWriter<T> footer(List<Object> footers) {
        footer = footers;
        return this;
    }

    @Override
    public CsvWriter<T> mapper(Function<T, List<Object>> mapper) {
        this.mapper = mapper;
        return this;
    }

    @Override
    public String generate() {
        if (getDelimiter() == null) {
            throw new IllegalArgumentException("delimiter cannot be null");
        }
        if (Utils.isEmpty(getLineSeparator())) {
            throw new IllegalArgumentException("lineSeparator cannot be null");
        }
        if (mapper == null) {
            throw new IllegalArgumentException("mapper cannot be null");
        }

        return Stream.of(csvLine(header), contentToCsvLines(), csvLine(footer))
                .filter(Utils::isNotEmpty)
                .collect(Collectors.joining(getLineSeparator()));
    }

    private String csvLine(List<?> content) {
        if (Utils.isEmpty(content)) {
            return CsvUtils.EMPTY;
        }
        return content.stream()
                .map(Object::toString)
                .map(this::csvCell)
                .collect(Collectors.joining(getDelimiter().toString()));
    }

    private String contentToCsvLines() {
        if (content == null) {
            return CsvUtils.EMPTY;
        }
        return content.stream()
                .map(mapper)
                .map(this::csvLine)
                .collect(Collectors.joining(getLineSeparator()));
    }

    /**
     * @param text
     * @return
     */
    private String csvCell(String text) {
        if (text == null) {
            return CsvUtils.EMPTY;
        }

        text = text.replace(CsvUtils.DOUBLE_QUOTE_STRING,
                CsvUtils.DOUBLE_QUOTE_STRING + CsvUtils.DOUBLE_QUOTE_STRING);

        // check if double_quote is needed
        if (CsvUtils.contains(text, CsvUtils.DOUBLE_QUOTE_STRING, getDelimiter(), getLineSeparator())) {
            text = CsvUtils.DOUBLE_QUOTE + text + CsvUtils.DOUBLE_QUOTE;
        }

        return text;
    }

}
