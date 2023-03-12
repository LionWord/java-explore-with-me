package com.lionword.mainservice.apierror.exceptions;

import org.springframework.http.HttpStatus;

public class HaveLinkedEventsException extends ParentApiException {
    public HaveLinkedEventsException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
