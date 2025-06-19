package com.fbuilder.main.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Answer { @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    @NotNull(message = "La respuesta es obligatoria")
    @Size(max = 300, message = "La respuesta no puede tener más de 300 caracteres")
    @Column(nullable = false, length = 300)
    private String answer;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;
    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}
