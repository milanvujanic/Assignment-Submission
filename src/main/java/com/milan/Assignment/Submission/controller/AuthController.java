package com.milan.Assignment.Submission.controller;

import com.milan.Assignment.Submission.config.security.JwtService;
import com.milan.Assignment.Submission.dto.LoginUserDto;
import com.milan.Assignment.Submission.dto.RegisterUserDto;
import com.milan.Assignment.Submission.dto.RegisterUserResponseDto;
import com.milan.Assignment.Submission.dto.mapper.UserToLoginResponseDtoMapper;
import com.milan.Assignment.Submission.service.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final UserToLoginResponseDtoMapper userToLoginResponseDtoMapper;

    public AuthController(AuthenticationService authenticationService, JwtService jwtService, UserToLoginResponseDtoMapper userToLoginResponseDtoMapper) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.userToLoginResponseDtoMapper = userToLoginResponseDtoMapper;
    }

    @PostMapping("/signUp")
    public ResponseEntity<RegisterUserResponseDto> signUp(@RequestBody RegisterUserDto registerUserDto) {
        RegisterUserResponseDto registeredUser = authenticationService.signUp(registerUserDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto request) {
        try {
            User user = authenticationService.login(request);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION,
                            jwtService.generateToken(new HashMap<>(), user))
                    .body(userToLoginResponseDtoMapper.mapToLoginResponseDto(user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
