package com.fbuilder.main.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "profile_image_path")
    private String profileImagePath;
    @Column(name = "role")
    private String role = "USER";

    @OneToMany(mappedBy = "user")
    private List<Form> forms;

    @OneToMany(mappedBy = "user")
    private List<Submission> submissions;

    @OneToMany(mappedBy = "user")
    private List<Answer> answers;
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}