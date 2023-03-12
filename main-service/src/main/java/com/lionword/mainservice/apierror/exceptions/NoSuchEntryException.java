package com.lionword.mainservice.apierror.exceptions;

import org.springframework.http.HttpStatus;

public class NoSuchEntryException extends ParentApiException {
    public NoSuchEntryException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
