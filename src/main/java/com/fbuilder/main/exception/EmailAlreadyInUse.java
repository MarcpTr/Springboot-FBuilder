package com.fbuilder.main.exception;

public class EmailAlreadyInUse extends RuntimeException {
    public EmailAlreadyInUse(String message) {
        super(message);
    }
}