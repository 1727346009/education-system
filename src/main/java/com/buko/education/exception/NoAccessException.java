package com.buko.education.exception;

/**
 * @author buko
 */
public class NoAccessException extends RuntimeException {
    public NoAccessException(String message) {
        super(message);
    }

    public NoAccessException(Exception error) {
        super(error);
    }
}
