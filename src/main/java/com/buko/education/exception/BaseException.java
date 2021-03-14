package com.buko.education.exception;

/**
 * @author 徐健威
 */
public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }

    public BaseException(Exception error) {
        super(error);
    }
}
