package com.nordan.exception;

public class UnexpectedException extends RuntimeException {

    public UnexpectedException() {
        super("Wystąpił nieoczekiwany błąd");
    }

    public UnexpectedException(String message) {
        super(message);
    }
}
