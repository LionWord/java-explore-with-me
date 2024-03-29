package com.lionword.mainservice.apierror;

import com.lionword.mainservice.apierror.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({NotUniqueUsernameException.class,
            NotUniqueCategoryNameException.class,
            HaveLinkedEventsException.class,
            TimeConstraintViolationException.class,
            AlreadyPublishedException.class,
            PublishingAlreadyCancelledEventException.class,
            CancellingPublishedEventException.class,
            RepeatedRequestException.class,
            OwnEventParticipationRequestException.class,
            NotPublishedEventException.class,
            ParticipationLimitReachedException.class,
            AlteringAlreadyPublishedEventException.class,
            NotPendingRequestStatusChangingException.class,
            NotInitiatorException.class,
            NotAuthorException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handle409ErrorCode(ParentApiException pae) {
        return new ApiError(pae);
    }

    @ExceptionHandler(NoSuchEntryException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handle404ErrorCode(ParentApiException pae) {
        return new ApiError(pae);
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleGeneric400ErrorCode(ParentApiException pae) {
        return new ApiError(pae);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleConstraintViolation(ConstraintViolationException cve) {
        return new ApiError(cve);
    }

}
