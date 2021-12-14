package io.dev.gateway.controller;

import io.dev.gateway.Service.User.CustomUserDetail;
import io.dev.gateway.payload.LoginRequest;
import io.dev.gateway.payload.LoginResponse;
import io.dev.gateway.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

/**
 * Created by TuNguyen
 * In 12/14/2021
 */
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @GetMapping("/test")
    public String tst() {
        return "ASDasdsadsadsa";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Xác thực thông tin người dùng Request lên
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Trả về jwt cho người dùng.
            String jwt = tokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
            return new ResponseEntity<>(new LoginResponse(jwt), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.UNAUTHORIZED);
        }
    }

}
