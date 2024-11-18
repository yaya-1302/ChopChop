package com.enigma.ChopChop.service.impl;

import com.enigma.ChopChop.constant.Constant;
import com.enigma.ChopChop.constant.UserRole;
import com.enigma.ChopChop.dto.request.UserRequest;
import com.enigma.ChopChop.dto.request.UserUpdatePasswordRequest;
import com.enigma.ChopChop.dto.response.UserResponse;
import com.enigma.ChopChop.entity.User;
import com.enigma.ChopChop.repository.UsersRepository;
import com.enigma.ChopChop.service.UserService;
import com.enigma.ChopChop.util.ValidationUtil;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationUtil validationUtil;

    @Value("${chopchop.user-admin}")
    private String USERNAME_ADMIN;

    @Value("${chopchop.user-password}")
    private String PASSWORD_ADMIN;

    @Transactional(rollbackFor = Exception.class)
    @PostConstruct
    public void initUser() {
        boolean exists = usersRepository.existsByUsername(USERNAME_ADMIN);
        if (exists) return;
        User userAccount = User.builder()
                .username(USERNAME_ADMIN)
                .password(passwordEncoder.encode(PASSWORD_ADMIN))
                .role(UserRole.ROLE_ADMIN)
                .build();
        usersRepository.saveAndFlush(userAccount);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        validationUtil.validate(userRequest);
        try {
            UserRole userRole = UserRole.findByDescription(userRequest.getRole());
            if (userRole == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constant.ERROR_ROLE_NOT_FOUND);
            User userAccount = User.builder()
                    .username(userRequest.getUsername())
                    .password(passwordEncoder.encode(userRequest.getPassword()))
                    .role(userRole)
                    .build();
            usersRepository.saveAndFlush(userAccount);
            return mapToResponse(userAccount);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User createUser(User userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        return usersRepository.saveAndFlush(userRequest);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public UserResponse getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userAccount = (User) authentication.getPrincipal();
        return mapToResponse(userAccount);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePassword(Long id, UserUpdatePasswordRequest request) {
        validationUtil.validate(request);
        User userAccount = getUserById(id);
        if (!passwordEncoder.matches(request.getOldPassword(), userAccount.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constant.INVALID_CREDENTIAL);
        }
        userAccount.setPassword(passwordEncoder.encode(request.getNewPassword()));
        usersRepository.saveAndFlush(userAccount);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUser(Long id) {
        User userAccount = getUserById(id);
        usersRepository.delete(userAccount);
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole().getDescription())
                .build();
    }
}
