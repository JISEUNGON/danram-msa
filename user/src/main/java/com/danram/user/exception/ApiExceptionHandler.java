package com.danram.user.exception;

import com.danram.user.exception.member.MemberEmailNotFoundException;
import com.danram.user.exception.member.MemberIdNotFoundException;
import com.danram.user.exception.member.MemberLoginTypeNotExistException;
import com.danram.user.exception.member.MemberNotExistException;
import com.danram.user.exception.s3.FileNameNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    /**
     * S3 upload exception => DEF(Danram Exception File)
     * */
    @ExceptionHandler(FileNameNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleException(FileNameNotValidException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEF-001" ,"File name is not invalid: ");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * DEM
     * */
    @ExceptionHandler(MemberIdNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberIdNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEM-001", "Member id not found: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberEmailNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberEmailNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEM-002", "Member email is not found: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberNotExistException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEM-003", "There is not member.");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberLoginTypeNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberLoginTypeNotExistException ex) {
        ApiErrorResponse response = new ApiErrorResponse("DEM-004", "This login type is not exist: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
