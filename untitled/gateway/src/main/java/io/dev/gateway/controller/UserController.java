package io.dev.gateway.controller;

import io.dev.gateway.Service.User.CustomUserDetail;
import io.dev.gateway.Service.User.UserServiceImp;
import io.dev.gateway.entity.User;
import io.dev.gateway.exception.ErrorDetails;
import io.dev.gateway.exception.SystemMessage;
import io.dev.gateway.payload.LoginRequest;
import io.dev.gateway.payload.LoginResponse;
import io.dev.gateway.security.JwtTokenProvider;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TuNguyen
 * In 12/14/2021
 */
@RestController
@RequestMapping("/")
@Slf4j
public class UserController {

    @Value("${io.dev.secret}")
    private String JWT_SECRET;

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
            System.out.println(jwt);
            Date expDate = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwt).getBody().getExpiration();
            return new ResponseEntity<>(new LoginResponse(jwt, expDate, SystemMessage.LOGIN_SUCCESS, 1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorDetails(new Date(), SystemMessage.USER_NOT_FOUND, null, 0, null), HttpStatus.OK);
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        try {
            if (user.getUsername().isEmpty() || user.getPassword().isEmpty())
                return new ResponseEntity(new ErrorDetails(new Date(), "Required Username and password", null, 0, null), HttpStatus.OK);
            User newUsr = new User();
            newUsr.setUsername(user.getUsername());
            newUsr.setPassword(bcryptEncoder.encode(user.getPassword()));
            newUsr.setEmail("Test@Dasd.co");
            newUsr.setName("demo");
            boolean createUsr = userServiceImp.createUser(newUsr);
            if (createUsr) {
                return new ResponseEntity(new ErrorDetails(new Date(), SystemMessage.HANDLE_SUCCESS, null, 1, null), HttpStatus.OK);
            } else
                return new ResponseEntity(new ErrorDetails(new Date(), SystemMessage.ALREADY_EXIST_ACCOUNT, null, 0, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorDetails(new Date(), SystemMessage.HAS_AN_ERROR, null, 0, null), HttpStatus.OK);
        }
    }
}
