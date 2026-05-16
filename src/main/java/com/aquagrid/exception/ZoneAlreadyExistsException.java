package com.aquagrid.exception;

public class ZoneAlreadyExistsException extends RuntimeException {
    public ZoneAlreadyExistsException(String message) {
        super(message);
    }
}