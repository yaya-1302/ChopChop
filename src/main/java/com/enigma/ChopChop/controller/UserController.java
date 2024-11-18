package com.enigma.ChopChop.controller;

import com.enigma.ChopChop.constant.Constant;
import com.enigma.ChopChop.dto.request.UserRequest;
import com.enigma.ChopChop.dto.request.UserUpdatePasswordRequest;
import com.enigma.ChopChop.dto.response.GeneralResponse;
import com.enigma.ChopChop.dto.response.UserResponse;
import com.enigma.ChopChop.entity.User;
import com.enigma.ChopChop.service.UserService;
import com.enigma.ChopChop.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = Constant.USER_API)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.createUser(userRequest);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_CREATE_ACCOUNT_USER, userResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getAuthentication() {
        UserResponse userResponse = userService.getAuthentication();
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_GET_SELF_INFO, userResponse);
    }

    @PatchMapping(path = "/{id}/update-password")
    public ResponseEntity<?> updatePassword(@PathVariable("id") Long id, @RequestBody UserUpdatePasswordRequest request) {
        userService.updatePassword(id, request);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_UPDATE_PASSWORD, null);
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_USER, null);
    }

}
