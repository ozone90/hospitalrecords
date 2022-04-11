package com.oze.hospitalrecords.exception;

public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * This method defines AccessDeniedException
     * @param msg the exception message
     */
    public ForbiddenException(String msg) {
        super(msg);
    }
}