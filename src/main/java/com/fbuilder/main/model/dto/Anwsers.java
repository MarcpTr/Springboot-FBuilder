package com.fbuilder.main.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Anwsers {
    private String id;
    private String type;
    private String question_text;
    private String value;
    private List<Option> options;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Option {
        private String id;
        private String question_text;
        private boolean checked;


    }
}
