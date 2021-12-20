package io.dev.gateway.payload;

import lombok.Data;

/**
 * Created by TuNguyen
 * In 12/14/2021
 */
@Data
public class LoginRequest {

    private String username;

    private String password;
}