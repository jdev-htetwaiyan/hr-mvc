package com.jdev.hr_mvc.Exceptions;

public class PositionAlreadyExistsException extends RuntimeException {

    public PositionAlreadyExistsException(String message) {
        super(message);
    }

}
