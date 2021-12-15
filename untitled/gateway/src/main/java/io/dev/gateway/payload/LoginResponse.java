package io.dev.gateway.payload;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 5/1/2019
 * Github: https://github.com/loda-kun
 */
@Data
public class LoginResponse {


    private String accessToken;
    private String tokenType = "Bearer";
    private Date expiration;
    private Integer code;
    private String message;

    public LoginResponse(String accessToken, Date expDate, String message, Integer code) {
        this.accessToken = accessToken;
        this.expiration = expDate;
        this.code = code;
        this.message = message;
    }
}