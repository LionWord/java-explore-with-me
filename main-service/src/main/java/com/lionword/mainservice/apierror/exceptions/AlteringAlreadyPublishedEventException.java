package com.lionword.mainservice.apierror.exceptions;

import org.springframework.http.HttpStatus;

public class AlteringAlreadyPublishedEventException extends ParentApiException {
    public AlteringAlreadyPublishedEventException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
