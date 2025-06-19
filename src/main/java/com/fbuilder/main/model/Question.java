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
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "La pregunta es obligatoria")
    @Size(max = 300, message = "El nombre no puede tener más de 300 caracteres")
    @Column(nullable = false, length = 300)
    private String question_text;
    @Column(nullable = false, name="order_index")
    private int orderIndex;
    @NotNull(message = "El tipo es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false, length = 20)
    private QuestionType questionType;
    @Column(nullable = false, name="is_required")
    private boolean isRequired;
    @ManyToOne(optional = false)
    @JoinColumn(name = "form_id", nullable = false)
    private Form form;
    @OneToMany(mappedBy = "question")
    private List<Option> options;
    @OneToMany(mappedBy = "question")
    private List<Answer> answers;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
