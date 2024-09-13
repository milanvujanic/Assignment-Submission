package com.milan.Assignment.Submission.dto.mapper;

import com.milan.Assignment.Submission.dto.RegisterResponseDto;
import com.milan.Assignment.Submission.entity.Authority;
import com.milan.Assignment.Submission.entity.User;

import static java.util.stream.Collectors.toSet;

public class UserToRegisteredUserDtoMapper {

    public static RegisterResponseDto mapToRegisterUserResponseDto(User user) {
        return new RegisterResponseDto(
                user.getId(),
                user.getUsername(),
                user.getCohortStartDate(),
                user.getAuthorities().stream().map(Authority::getAuthority).collect(toSet())
        );
    }
}

