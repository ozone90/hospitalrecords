package com.oze.hospitalrecords.enums;

public enum ResponseCodes
{
    SUCCESSFUL("00", "Successful"),
    DUPLICATE_RECORD("01", "Record exists"),
    BAD_REQUEST("03", "Bad Request"),
    RECORD_NOT_FOUND("04", "Record not found"),
    SYSTEM_ERROR("05", "System Error");


    private final String code;
    private final String description;

    /**
     * This constructor is used to build response codes
     * @param code response code
     * @param description response description
     */
    private ResponseCodes(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
