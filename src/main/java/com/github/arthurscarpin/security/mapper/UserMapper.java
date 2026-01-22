package com.github.arthurscarpin.security.mapper;

import com.github.arthurscarpin.security.controller.request.UserRequest;
import com.github.arthurscarpin.security.controller.response.UserResponse;
import com.github.arthurscarpin.security.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toUser(UserRequest request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
