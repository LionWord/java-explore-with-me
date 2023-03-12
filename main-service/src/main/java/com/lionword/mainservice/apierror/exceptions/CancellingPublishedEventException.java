package com.lionword.mainservice.apierror.exceptions;

import org.springframework.http.HttpStatus;

public class CancellingPublishedEventException extends ParentApiException {
    public CancellingPublishedEventException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
