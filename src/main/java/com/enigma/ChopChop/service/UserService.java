package com.enigma.ChopChop.service;

import com.enigma.ChopChop.dto.request.UserRequest;
import com.enigma.ChopChop.dto.request.UserUpdatePasswordRequest;
import com.enigma.ChopChop.dto.response.UserResponse;
import com.enigma.ChopChop.entity.User;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    User createUser(User userRequest);
    User getUserById(Long id);
    UserResponse getAuthentication();
    void updatePassword(Long id, UserUpdatePasswordRequest request);
    void deleteUser(Long id);
}
