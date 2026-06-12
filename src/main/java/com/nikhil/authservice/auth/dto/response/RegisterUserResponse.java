package com.nikhil.authservice.auth.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RegisterUserResponse {
    private Long id;
    private String email;
}
