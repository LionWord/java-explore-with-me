package com.lionword.mainservice.apierror.exceptions;

import org.springframework.http.HttpStatus;

public class NotAuthorException extends ParentApiException {
    public NotAuthorException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
