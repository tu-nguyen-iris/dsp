package io.dev.gateway.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TuNguyen
 * In 12/14/2021
 */
@Setter
@Getter
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    Map<String, Object> response = new HashMap<>();
    private Integer code;

    /**
     *
     * @param timestamp
     * @param message
     * @param details
     * @param code
     * @param response
     */
    public ErrorDetails(Date timestamp, String message, String details, Integer code, Map<String, Object> response) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.code = code;
        this.response = response;
    }
}
