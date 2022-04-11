package com.oze.hospitalrecords.exception;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * This method defines BadRequestException
     * @param msg the exception message
     */
    public BadRequestException(String msg) {
        super(msg);
    }
}