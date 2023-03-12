package com.lionword.mainservice.apierror.exceptions;

import com.lionword.mainservice.apierror.NotInitiatorException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ExceptionTemplates {

    public static void dateParse() {
        throw new InvalidInputException(HttpStatus.BAD_REQUEST,
                "Invalid time format",
                "Could not parse time string. Check your input. Correct format is \"yyyy-MM-dd HH:mm:ss\"");
    }

    public static void eventTooEarlyAfterPublication() {
        throw new TimeConstraintViolationException(HttpStatus.CONFLICT,
                "Invalid event date",
                "Event date can't be earlier than one hour after publication date");
    }

    public static void eventTooEarlyAfterCreation() {
        throw new TimeConstraintViolationException(HttpStatus.CONFLICT,
                "Invalid event date",
                "Event date can't be earlier than two hours after creation date");
    }

    public static void eventEarlierThanCreation() {
        throw new TimeConstraintViolationException(HttpStatus.CONFLICT,
                "Invalid event date",
                "Event date can't be earlier than creation date");
    }

    public static void eventInPast() {
        throw new TimeConstraintViolationException(HttpStatus.CONFLICT,
                "Invalid event date",
                "Can't set an event date at past");
    }

    public static void publishingAlreadyPublished() {
        throw new AlreadyPublishedException(HttpStatus.CONFLICT,
                "Event is already published",
                "Can't publish an event that was already published");
    }

    public static void publishingCancelledEvent() {
        throw new PublishingAlreadyCancelledEventException(HttpStatus.CONFLICT,
                "Event is cancelled",
                "Can't publish an event that was already cancelled");
    }

    public static void cancellingPublishedEvent() {
        throw new CancellingPublishedEventException(HttpStatus.CONFLICT,
                "Cancelling published event",
                "Can't cancel an event that was already published");
    }

    public static void notUniqueName() {
        throw new NotUniqueCategoryNameException(HttpStatus.CONFLICT,
                "Not unique category name",
                "Category name must be unique");
    }

    public static NoSuchEntryException categoryNotFound() {
        throw new NoSuchEntryException (
                HttpStatus.NOT_FOUND,
                "Entry not found",
                "Category entry was not found"
        );
    }

    public static NoSuchEntryException userNotFound() {
        throw new NoSuchEntryException (
                HttpStatus.NOT_FOUND,
                "User not found",
                "User entry was not found"
        );
    }

    public static NoSuchEntryException eventNotFound() {
        throw new NoSuchEntryException (
                HttpStatus.NOT_FOUND,
                "Event not found",
                "Event entry was not found"
        );
    }

    public static void haveLinkedEvents(List<Long> linkedEvents) {
        throw new HaveLinkedEventsException(
                HttpStatus.CONFLICT,
                "Linked events",
                "This category got linked events: " + linkedEvents
        );
    }

    public static void repeatedRequest() {
        throw new RepeatedRequestException(
                HttpStatus.CONFLICT,
                "Repeated request",
                "This user already sent a participation request for this event"
        );
    }

    public static void requestToParticipateInOwnEvent() {
        throw new OwnEventParticipationRequestException(HttpStatus.CONFLICT,
                "Request to participate in own event",
                "The event initiator does not need to send participation requests for their own events");
    }

    public static void notPublishedEventParticipation() {
        throw new NotPublishedEventException(HttpStatus.CONFLICT,
                "Event is not published yet",
                "Can't sent participation request for non-published event");
    }

    public static void participationLimitExceeded() {
        throw new ParticipationLimitReachedException(HttpStatus.CONFLICT,
                "Participation limit reached",
                "No more participants allowed - participation limit already reached");
    }

    public static void alteringAlreadyPublishedEvent() {
        throw new AlteringAlreadyPublishedEventException(HttpStatus.CONFLICT,
                "Event was already published",
                "Can't alter params of already published event");
    }

    public static void changingNonPendingRequestStatus() {
        throw new NotPendingRequestStatusChangingException(HttpStatus.CONFLICT,
                "Request is not pending",
                "Can't confirm or reject non pending request");
    }

    public static void notInitiatorRequest() {
        throw new NotInitiatorException(HttpStatus.CONFLICT,
                "Can't execute request",
                "Only initiator of event can get information about participation requests");
    }

}


