package com.depromeet.dayblock.auth;

public class EmailInvalidException extends Exception {
    public EmailInvalidException() {
        super("Email not found");
    }
    public EmailInvalidException(String message) {
        super(message);
    }
}