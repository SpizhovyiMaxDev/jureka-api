package studio.maxdev.jureka_api.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import studio.maxdev.jureka_api.exception.BadRequestException;
import studio.maxdev.jureka_api.exception.FileStorageException;
import studio.maxdev.jureka_api.exception.ProductNotFoundException;
import studio.maxdev.jureka_api.exception.error_shell.ErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException exc) {
        return buildError(HttpStatus.BAD_REQUEST, exc.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(ProductNotFoundException exc) {
        return buildError(HttpStatus.NOT_FOUND, exc.getMessage());
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ErrorResponse> handleFileStorageException(FileStorageException exc){
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, exc.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception exc){
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong. Please try again. More specific reasons: " + exc.getMessage());
    }

    private ResponseEntity<ErrorResponse> buildError(HttpStatus status, String message){
        return ResponseEntity.status(status).body(new ErrorResponse(status.value(), LocalDateTime.now(), message));
    }
}