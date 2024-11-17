package com.book_catalog.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@ToString
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String message = "Successful";
    private Boolean success = false;
    private T data;
    private Map<String, String> error;

    public ApiResponse() {}
    public ApiResponse(String message, Boolean success, T data, Map<String, String> error) {
        setMessage(message);
        setData(data);
        setSuccess(success);
        setError(error);
    }
}
