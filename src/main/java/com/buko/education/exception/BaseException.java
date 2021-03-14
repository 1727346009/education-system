package com.buko.education.exception;

/**
 * @author buko
 */
public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }

    public BaseException(Exception error) {
        super(error);
    }
}
