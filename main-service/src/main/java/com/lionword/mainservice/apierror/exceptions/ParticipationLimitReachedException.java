package com.lionword.mainservice.apierror.exceptions;

import org.springframework.http.HttpStatus;

public class ParticipationLimitReachedException extends ParentApiException {
    public ParticipationLimitReachedException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
