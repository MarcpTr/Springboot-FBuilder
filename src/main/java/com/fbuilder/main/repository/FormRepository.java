package com.fbuilder.main.repository;

import com.fbuilder.main.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Integer> {}