package com.lionword.mainservice.apierror.exceptions;

import org.springframework.http.HttpStatus;

public class TimeConstraintViolationException extends ParentApiException{
    public TimeConstraintViolationException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
