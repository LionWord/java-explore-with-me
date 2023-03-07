package com.lionword.mainservice.apierror.exceptions;


import org.springframework.http.HttpStatus;

public class NotUniqueUsernameException extends ParentApiException {

    public NotUniqueUsernameException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
