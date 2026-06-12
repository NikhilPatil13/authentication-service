package com.nikhil.authservice.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true , length = 255)
    private String email;

    @Column(name="password_hash" , nullable = false , length = 255)
    private String passwordHash;

    @Column(name="is_active" , nullable = false)
    private Boolean isActive;

    @Column(name="created_at" , nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at" , nullable = false)
    private LocalDateTime updatedAt;
}