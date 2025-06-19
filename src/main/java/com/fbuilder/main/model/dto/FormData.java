package com.fbuilder.main.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FormData {
    private String name;
    private String description;
    private List<String> questions;
    private List<CheckboxGroup> checkboxes;


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class CheckboxGroup {
        private String question;
        private List<String> option;

    }
}
