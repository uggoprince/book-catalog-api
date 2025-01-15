package com.book_catalog.dto.request;

import com.book_catalog.validation.NotEmptyIfPresent;
import com.book_catalog.validation.ValidPrice;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Past;
import java.util.Date;

@Data
@Setter
@Getter
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
    @Past(message = "Publish Date must be in the past.")
    private Date publishDate;

    @ValidPrice(message = "Price is invalid")
    private Double price;

    @NotEmptyIfPresent(message = "Type should not be empty.")
    private String type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public @Past(message = "Publish Date must be in the past.") Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(@Past(message = "Publish Date must be in the past.") Date publishDate) {
        this.publishDate = publishDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
