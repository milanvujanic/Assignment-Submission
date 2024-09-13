package com.milan.Assignment.Submission.dto;

import java.util.List;

public class LoginResponseDto {
    private Long id;
    private String username;
    private List<String> authorities;

    public LoginResponseDto(Long id, String username, List<String> authorities) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getAuthorities() {
        return authorities;
    }
}
