package com.oze.hospitalrecords.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * This method defines NotFoundException
     * @param msg the exception message
     */
    public NotFoundException(String msg) {
        super(msg);
    }
}