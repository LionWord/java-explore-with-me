package com.lionword.mainservice.apierror.exceptions;

import org.springframework.http.HttpStatus;

public class OwnEventParticipationRequestException extends ParentApiException{
    public OwnEventParticipationRequestException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
