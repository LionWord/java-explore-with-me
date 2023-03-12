package com.lionword.mainservice.apierror;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lionword.mainservice.apierror.exceptions.ParentApiException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError {
    private HttpStatus status;
    private String reason;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime timestamp;

    public ApiError(ParentApiException exception) {
        this.status = exception.getStatus();
        this.reason = exception.getReason();
        this.message = exception.getMessage();
        this.timestamp = exception.getTimestamp();
    }

    public ApiError(ConstraintViolationException cve) {
        this.status = HttpStatus.BAD_REQUEST;
        this.reason = "Constraint violation";
        this.message = cve.getMessage();
        this.timestamp = LocalDateTime.now();
    }

}
