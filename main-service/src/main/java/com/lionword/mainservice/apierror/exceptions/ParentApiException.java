package com.lionword.mainservice.apierror.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ParentApiException extends RuntimeException {
    protected final LocalDateTime timestamp = LocalDateTime.now();
    protected final HttpStatus status;
    protected final String reason;
    protected final String message;

    public ParentApiException(HttpStatus status, String reason, String message) {
        super();
        this.status = status;
        this.reason = reason;
        this.message = message;
    }

}
