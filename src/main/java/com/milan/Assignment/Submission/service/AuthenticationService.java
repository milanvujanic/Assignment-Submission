package com.milan.Assignment.Submission.service;

import com.milan.Assignment.Submission.dto.LoginUserDto;
import com.milan.Assignment.Submission.dto.RegisterUserDto;
import com.milan.Assignment.Submission.dto.RegisterResponseDto;
import com.milan.Assignment.Submission.dto.mapper.UserToRegisteredUserDtoMapper;
import com.milan.Assignment.Submission.entity.Authority;
import com.milan.Assignment.Submission.entity.User;
import com.milan.Assignment.Submission.repository.AuthorityRepository;
import com.milan.Assignment.Submission.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(AuthenticationManager authenticationManager, UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponseDto signUp(RegisterUserDto request) {
        Set<Authority> authorities = authorityRepository.findAllByAuthority(request.getAuthorities());

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCohortStartDate(request.getCohortStartDate());
        user.addAuthorities(authorities);

        user = userRepository.save(user);

        return UserToRegisteredUserDtoMapper.mapToRegisterUserResponseDto(user);
    }

    public org.springframework.security.core.userdetails.User login(LoginUserDto request) {

        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        return (org.springframework.security.core.userdetails.User) authenticate.getPrincipal();
    }
}

