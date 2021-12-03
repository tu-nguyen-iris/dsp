package com.example.demo.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
public class ResponseModified extends Exception {
    private int code;
    private String message;
    Map<String, Object> data = new HashMap<>();
}
