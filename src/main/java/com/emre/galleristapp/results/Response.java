package com.emre.galleristapp.results;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private T data;
    private String message;
    private boolean success;
    private int statusCode;

    public static <T> Response<T> success(T data) {
        Response<T> result = new Response<>();
        result.setSuccess(true);
        result.setData(data);
        result.setMessage(null);
        result.setStatusCode(HttpStatus.OK.value());
        return result;
    }

    public static <T> Response<T> fail(String message, HttpStatusCode statusCode) {
        Response<T> result = new Response<>();
        result.setSuccess(false);
        result.setMessage(message);
        result.setData(null);
        result.setStatusCode(statusCode.value());
        return result;
    }
}