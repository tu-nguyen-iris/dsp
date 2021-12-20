package io.dev.employees.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Modified Response messages api
 * Todo
 * Need optimize this
 */
@Getter
@Setter
@NoArgsConstructor
public class ResponseModified {
    private Date timestamp;
    private int code;
    private String message;
    Map<String, Object> response = new HashMap<>();

    public ResponseModified(int code, String message, Map<String, Object> response) {
        this.code = code;
        this.message = message;
        this.response = response;
        this.timestamp = new Date();
    }
}
