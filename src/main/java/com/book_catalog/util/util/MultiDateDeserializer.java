package com.book_catalog.util.util;

import com.book_catalog.exception.InvalidDateFormatException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class MultiDateDeserializer extends JsonDeserializer<LocalDate> {

    private static final List<DateTimeFormatter> dateFormats = Arrays.asList(
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
    );

    @SneakyThrows
    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String date = p.getText();
        String fieldName = p.getCurrentName();

        for (DateTimeFormatter formatter : dateFormats) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                // continue to the next format
            }
        }
        throw new InvalidDateFormatException(fieldName, "Unable to parse date: " + date);
    }
}

