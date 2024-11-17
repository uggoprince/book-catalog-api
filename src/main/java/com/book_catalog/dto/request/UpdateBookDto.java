package com.book_catalog.dto.request;

import com.book_catalog.validation.NotEmptyIfPresent;
import com.book_catalog.validation.ValidPrice;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Past;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateBookDto {
    @NotEmptyIfPresent(message = "Name should not be empty.")
    private String name;

    @NotEmptyIfPresent(message = "ISBN Number should not be empty.")
    private String isbnNumber;

    @NotEmptyIfPresent(message = "Author should not be empty.")
    private String author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Past(message = "Date must be in the past.")
    private Date date;

    @ValidPrice(message = "Price is invalid")
    private Double price;

    @NotEmptyIfPresent(message = "Type should not be empty.")
    private String type;
}
