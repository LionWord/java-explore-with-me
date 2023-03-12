package com.lionword.mainservice.apierror.exceptions;

import org.springframework.http.HttpStatus;

public class RepeatedRequestException extends ParentApiException{
    public RepeatedRequestException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
