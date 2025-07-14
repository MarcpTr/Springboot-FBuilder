package com.fbuilder.main.repository;

import com.fbuilder.main.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FormRepository extends JpaRepository<Form, Integer> {
    Optional<List<Form>> findByUserId(Long id);
}