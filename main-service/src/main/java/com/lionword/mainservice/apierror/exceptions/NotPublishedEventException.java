package com.lionword.mainservice.apierror.exceptions;

import org.springframework.http.HttpStatus;

public class NotPublishedEventException extends ParentApiException{
    public NotPublishedEventException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
