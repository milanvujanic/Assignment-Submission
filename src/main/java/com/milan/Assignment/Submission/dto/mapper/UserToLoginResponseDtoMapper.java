package com.milan.Assignment.Submission.dto.mapper;

import com.milan.Assignment.Submission.dto.LoginResponseDto;
import com.milan.Assignment.Submission.entity.Authority;
import com.milan.Assignment.Submission.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserToLoginResponseDtoMapper {

    private final UserRepository userRepository;

    public UserToLoginResponseDtoMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponseDto mapToLoginResponseDto(User user) {
        Optional<com.milan.Assignment.Submission.entity.User> userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb.isEmpty()) {
            throw new BadCredentialsException("Invalid credentials!");
        }

        return buildLoginResponseDto(userFromDb.get());
    }

    private LoginResponseDto buildLoginResponseDto(com.milan.Assignment.Submission.entity.User user) {
        return new LoginResponseDto(
                user.getId(),
                user.getUsername(),
                user.getAuthorities().stream().map(Authority::getAuthority).toList()
        );
    }
}
