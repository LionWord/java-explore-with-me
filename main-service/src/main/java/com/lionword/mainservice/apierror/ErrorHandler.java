package com.lionword.mainservice.apierror;

import com.lionword.mainservice.apierror.exceptions.NotUniqueUsernameException;
import com.lionword.mainservice.apierror.exceptions.ParentApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotUniqueUsernameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handle409ErrorCode(ParentApiException e) {
        return new ApiError(e);
    }
}
