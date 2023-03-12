package com.lionword.mainservice.apierror.exceptions;

import org.springframework.http.HttpStatus;

public class PublishingAlreadyCancelledEventException extends ParentApiException {
    public PublishingAlreadyCancelledEventException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
