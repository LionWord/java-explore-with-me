package com.lionword.mainservice.apierror.exceptions;

import org.springframework.http.HttpStatus;

public class NotPendingRequestStatusChangingException extends ParentApiException {
    public NotPendingRequestStatusChangingException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
