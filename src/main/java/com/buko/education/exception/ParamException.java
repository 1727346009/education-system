package com.buko.education.exception;

/**
 * @author buko
 */
public class ParamException extends RuntimeException {
    public ParamException(String message) {
        super(message);
    }

    public ParamException(Exception error) {
        super(error);
    }
}
