package io.dev.employees.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@AllArgsConstructor
public class ResponseModified {
    private int code;
    private String message;
    Map<String, Object> response = new HashMap<>();
}
