package com.fbuilder.main.exception;

public class InvalidUsernameFormat extends RuntimeException {
    public InvalidUsernameFormat(String message) {
        super(message);
    }
}
