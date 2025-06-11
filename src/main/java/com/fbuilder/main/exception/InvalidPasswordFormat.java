package com.fbuilder.main.exception;

public class InvalidPasswordFormat extends RuntimeException {
    public InvalidPasswordFormat(String message) {
        super(message);
    }
}
