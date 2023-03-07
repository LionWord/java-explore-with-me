package com.lionword.mainservice.apierror.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Getter
public class ParentApiException extends RuntimeException {
    protected HttpStatus status;
    protected String reason;
    protected String message;
    protected final LocalDateTime timestamp = LocalDateTime.now();

    public ParentApiException(HttpStatus status, String reason, String message) {
        super();
        this.status = status;
        this.reason = reason;
        this.message = message;
    }

}
