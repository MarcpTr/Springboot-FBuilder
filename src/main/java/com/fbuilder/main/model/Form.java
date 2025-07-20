package com.fbuilder.main.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    @Column(nullable = false, length = 50)
    private String name;

    @NotNull(message = "La descripción es obligatoria")
    @Size(max = 320, message = "La descripción no puede tener más de 320 caracteres")
    @Column(nullable = false, length = 320)
    private String description;

    @Column(nullable = false, name="is_visible")
    private boolean isVisible;
    @Column(nullable = false, name="is_open")
    private boolean isOpen;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "form")
    private List<Question> questions;
    @OneToMany(mappedBy = "form")
    private List<Submission> submissions;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
