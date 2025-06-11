package com.fbuilder.main.exception;

public class InvalidLogin extends RuntimeException {
    public InvalidLogin(String message) {
        super(message);
    }
}
