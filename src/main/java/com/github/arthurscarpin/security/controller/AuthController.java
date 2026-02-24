package com.github.arthurscarpin.security.controller;

import com.github.arthurscarpin.security.config.TokenService;
import com.github.arthurscarpin.security.controller.request.UserRequest;
import com.github.arthurscarpin.security.controller.response.LoginResponse;
import com.github.arthurscarpin.security.controller.response.UserResponse;
import com.github.arthurscarpin.security.entity.User;
import com.github.arthurscarpin.security.exception.UsernameOrPasswordException;
import com.github.arthurscarpin.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest request) {
        try {
            UsernamePasswordAuthenticationToken userAndPassword = new UsernamePasswordAuthenticationToken(
                    request.email(),
                    request.password()
            );
            Authentication authenticate = authenticationManager.authenticate(userAndPassword);
            User user = (User) authenticate.getPrincipal();
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException e) {
            throw new UsernameOrPasswordException("Username or password invalid!");
        }
    }
}
