package com.nordan.exception;

public class UnexpectedException extends RuntimeException {

    public UnexpectedException() {
        super("An unexpected error has occurred");
    }

    public UnexpectedException(String message) {
        super(message);
    }
}
