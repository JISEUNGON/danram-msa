package com.danram.comment.exception;
import com.danram.comment.exception.comment.CommentIdNotFoundException;
import com.danram.comment.exception.comment.CommentLikeIdNotFoundException;
import com.danram.comment.exception.comment.NotAuthorityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(CommentIdNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(CommentIdNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse("DEC-001", "Comment id is not founded: " + ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(NotAuthorityException.class)
    public ResponseEntity<ApiErrorResponse> handleException(NotAuthorityException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse("DEC-002", "This member has not authority: " + ex.getMessage()),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(CommentLikeIdNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(CommentLikeIdNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse("DEC-003", "Memter did not like this comment: " + ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
