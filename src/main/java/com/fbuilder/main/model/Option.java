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
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 150, message = "la opcion no puede tener más de 150 caracteres")
    @Column(nullable = false, length = 50, name="option_text")
    private String optionText;
    @Column(nullable = false, name="order_index")
    private int orderIndex;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}