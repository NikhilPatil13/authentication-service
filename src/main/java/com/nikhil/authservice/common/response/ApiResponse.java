package com.nikhil.authservice.common.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ApiResponse <T>{
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;
}