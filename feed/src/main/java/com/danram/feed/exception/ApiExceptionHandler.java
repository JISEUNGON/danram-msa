package com.danram.feed.exception;

import com.danram.feed.exception.feed.FeedIdNotFoundException;
import com.danram.feed.exception.feed.FeedLikeIdNotFoundException;
import com.danram.feed.exception.feed.RoleNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(FeedIdNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(FeedIdNotFoundException ex) {
        return new ResponseEntity<>(new ApiErrorResponse("DEF-001", "Feed id is not exist: " + ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleException(RoleNotExistException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse("DEF-002", "This member does not have Role"),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(FeedLikeIdNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(FeedLikeIdNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse("DEF-003", "Member like is not exist" + ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
