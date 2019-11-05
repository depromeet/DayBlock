package com.depromeet.dayblock.auth;

public class UnauthorizedException extends Exception {
    public UnauthorizedException() {
        super("Unauthorized data");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
