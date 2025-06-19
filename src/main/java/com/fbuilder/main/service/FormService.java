package com.fbuilder.main.service;

import com.fbuilder.main.model.*;
import com.fbuilder.main.model.dto.FormData;
import com.fbuilder.main.repository.FormRepository;
import com.fbuilder.main.repository.OptionRepository;
import com.fbuilder.main.repository.QuestionRepository;
import com.fbuilder.main.repository.UserRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
@Service
public class FormService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private OptionRepository optionRepository;
    public void createForm(FormData formData, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Form form = new Form();
        form.setName("Untitled Form");
        form.setDescription("Auto-generated form");
        form.setVisible(true);
        form.setOpen(true);
        form.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
        form.setUser(user);

        form = formRepository.save(form);

        int order = 1;

        // Procesar preguntas simples
        for (String questionText : formData.getQuestions()) {
            Question question = new Question();
            question.setQuestion_text(questionText);
            question.setQuestionType(QuestionType.TEXT);
            question.setRequired(true);
            question.setOrderIndex(order++);
            question.setForm(form);
            question.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
            questionRepository.save(question);
        }

        // Procesar preguntas con opciones (checkboxes)
        for (FormData.CheckboxGroup group : formData.getCheckboxes()) {
            Question question = new Question();
            question.setQuestion_text(group.getQuestion());
            question.setQuestionType(QuestionType.CHECKBOX);
            question.setRequired(false);
            question.setOrderIndex(order++);
            question.setForm(form);
            question.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
            question = questionRepository.save(question);

            int optionIndex = 1;
            for (String optionText : group.getOption()) {
                Option option = new Option();
                option.setOptionText(optionText);
                option.setOrderIndex(optionIndex++);
                option.setQuestion(question);
                optionRepository.save(option);
            }
        }
    }
}