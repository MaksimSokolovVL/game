package com.game.controller;


import com.game.domain.model.WebError;
import com.game.exception.PlayerCreationException;
import com.game.exception.DeletePlayerBiIdException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<WebError> handleIllegalArgumentException(IllegalArgumentException ex) {
        return handleException(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(DeletePlayerBiIdException.class)
    public ResponseEntity<WebError> handleDeletePlayerBiIdException(DeletePlayerBiIdException ex) {
        return handleException(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(PlayerCreationException.class)
    public ResponseEntity<WebError> handlePlayerCreationException(PlayerCreationException ex) {
        return handleException(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<WebError> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
         return handleException(HttpStatus.BAD_REQUEST, ex);
    }

   @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<WebError> handleEntityNotFoundException(EntityNotFoundException ex) {
        return handleException(HttpStatus.NOT_FOUND, ex);
    }

    private ResponseEntity<WebError> handleException(HttpStatus status, Exception exception) {
        return ResponseEntity
                .status(status)
                .body(
                        new WebError(status, exception.getMessage()));
    }
}
