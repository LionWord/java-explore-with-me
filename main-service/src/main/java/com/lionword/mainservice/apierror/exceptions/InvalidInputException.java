package com.lionword.mainservice.apierror.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidInputException extends ParentApiException {
    public InvalidInputException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
