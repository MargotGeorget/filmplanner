package com.filmplanner.exceptions;

public class InvalidValuesClientException extends Exception{

    public enum invalid_value {
        INVALID_COMPANY_NAME,
        INVALID_DESCRIPTION,
        INVALID_REFEREE8NAME,
        INVALID_REFEREE_EMAIL,
        INVALID_REFEREE_TEL
    };

    public InvalidValuesClientException(String message) {
        super(message);
    }
}
