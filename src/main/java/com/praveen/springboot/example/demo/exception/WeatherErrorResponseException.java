package com.praveen.springboot.example.demo.exception;

import org.springframework.web.client.HttpStatusCodeException;
import static java.lang.String.format;

public class WeatherErrorResponseException  extends RuntimeException{

    private static final String PATTERN = "%s, HTTP Status : %d %s, Response Payload: %s";

    private int httpStatusCode;
    private String httpStatusText;
    private String responsePayload;

    public WeatherErrorResponseException(String message, HttpStatusCodeException e) {
        super(enrichMessage(message, e), e);
        populateFromHttpStatusCodeException(e);
    }

    private void populateFromHttpStatusCodeException(HttpStatusCodeException e) {
        this.httpStatusCode = e.getStatusCode().value();
        this.httpStatusText = e.getStatusText();
        this.responsePayload = e.getResponseBodyAsString();
    }

    private static String enrichMessage(String message, HttpStatusCodeException e) {
        return format(PATTERN, message, e.getStatusCode().value(), e.getStatusText(), e.getResponseBodyAsString());
    }


}
