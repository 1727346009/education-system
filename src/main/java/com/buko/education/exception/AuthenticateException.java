package com.buko.education.exception;

/**
 * @author 徐健威
 */
public class AuthenticateException extends RuntimeException {
    public AuthenticateException(String message) {
        super(message);
    }

    public AuthenticateException(Exception error) {
        super(error);
    }
}
