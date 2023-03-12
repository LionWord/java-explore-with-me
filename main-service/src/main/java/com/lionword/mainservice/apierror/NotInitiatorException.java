package com.lionword.mainservice.apierror;

import com.lionword.mainservice.apierror.exceptions.ParentApiException;
import org.springframework.http.HttpStatus;

public class NotInitiatorException extends ParentApiException {
    public NotInitiatorException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
