package com.book_catalog.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class BookDto {
    @NotEmpty(message = "Name is required.")
    private String name;

    @NotEmpty(message = "ISBN number is required.")
    @Size(min = 10, max = 13, message = "ISBN must be either 10 or 13 digits long.")
    private String isbnNumber;

    @NotEmpty(message = "Author is required.")
    private String author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "Published date is required.")
    @Past(message = "Published date must be in the past.")
    private Date publishedDate;

    @NotNull(message = "Price is required.")
    @DecimalMin(value = "1.0", message = "Price must be greater than or equal to 1.0")
    private Double price;

    @NotEmpty(message = "Type is required.")
    private String type;
}
