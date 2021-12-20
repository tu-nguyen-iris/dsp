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
 * Created by TuNguyen
 * In 12/14/2021
 */
@Data
public class LoginResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private Date expiration;
    private Integer code;
    private String message;

    /**
     *
     * @param accessToken
     * @param expDate
     * @param message
     * @param code
     */
    public LoginResponse(String accessToken, Date expDate, String message, Integer code) {
        this.accessToken = accessToken;
        this.expiration = expDate;
        this.code = code;
        this.message = message;
    }
}