package com.fbuilder.main.repository;

import com.fbuilder.main.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {}
