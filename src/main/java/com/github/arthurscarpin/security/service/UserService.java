package com.github.arthurscarpin.security.service;

import com.github.arthurscarpin.security.controller.request.UserRequest;
import com.github.arthurscarpin.security.controller.response.UserResponse;
import com.github.arthurscarpin.security.entity.User;
import com.github.arthurscarpin.security.mapper.UserMapper;
import com.github.arthurscarpin.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserResponse save(UserRequest request) {
        User user = UserMapper.toUser(request);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return UserMapper.toUserResponse(repository.save(user));
    }
}
