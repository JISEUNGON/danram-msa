package com.danram.party.exception;

import com.danram.party.exception.party.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    /**
     * Party Error DEP => Danram Party Error
     * */
    @ExceptionHandler(PartyNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(PartyNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEP-0001","Party is not found - id "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PartyMemberNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(PartyMemberNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEP-0002","party member not found - party or party-member id "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PartyHostExitException.class)
    public ResponseEntity<ApiErrorResponse> handleException(PartyHostExitException ex) {
        ApiErrorResponse response  = new ApiErrorResponse("DEP-0003","party host cannot exit party - member id "+ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PartyMemberDuplicatedException.class)
    public ResponseEntity<ApiErrorResponse> handleException(PartyMemberDuplicatedException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEP-0004","party member duplicated "+ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PartyJoinNotAllowException.class)
    public ResponseEntity<ApiErrorResponse> handleException(PartyJoinNotAllowException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEP-0005",ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
