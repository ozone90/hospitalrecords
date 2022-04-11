package com.oze.hospitalrecords.exception;

public class ConflictException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * This method defines ConflictException
     * @param msg the exception message
     */
    public ConflictException(String msg) {
        super(msg);
    }
}