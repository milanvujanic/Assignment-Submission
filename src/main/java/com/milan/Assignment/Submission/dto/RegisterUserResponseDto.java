package com.milan.Assignment.Submission.dto;

import java.time.LocalDate;
import java.util.Set;

public class RegisterUserResponseDto {

    private Long id;
    private String username;
    private LocalDate cohortStartDate;
    private Set<String> authorities;

    public RegisterUserResponseDto(Long id, String username, LocalDate cohortStartDate, Set<String> authorities) {
        this.id = id;
        this.username = username;
        this.cohortStartDate = cohortStartDate;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getCohortStartDate() {
        return cohortStartDate;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }
}
