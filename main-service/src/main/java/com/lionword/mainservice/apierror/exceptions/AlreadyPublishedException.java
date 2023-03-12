package com.lionword.mainservice.apierror.exceptions;

import org.springframework.http.HttpStatus;

public class AlreadyPublishedException extends ParentApiException{
    public AlreadyPublishedException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
