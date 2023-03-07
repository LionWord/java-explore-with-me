package com.lionword.mainservice.apierror.exceptions;

import org.springframework.http.HttpStatus;

public class NotUniqueCategoryNameException extends ParentApiException{
    public NotUniqueCategoryNameException(HttpStatus status, String reason, String message) {
        super(status, reason, message);
    }
}
