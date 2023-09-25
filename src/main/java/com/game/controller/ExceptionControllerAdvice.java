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
    public ResponseEntity<WebError> illegalArgumentException(IllegalArgumentException ex) {
        return handlerException(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(DeletePlayerBiIdException.class)
    public ResponseEntity<WebError> deletePlayerBiIdException(DeletePlayerBiIdException ex) {
        return handlerException(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(PlayerCreationException.class)
    public ResponseEntity<WebError> playerCreationException(PlayerCreationException ex) {
        return handlerException(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<WebError> dataIntegrityViolationException(DataIntegrityViolationException ex) {
         return handlerException(HttpStatus.BAD_REQUEST, ex);
    }

   @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<WebError> entityNotFoundException(EntityNotFoundException ex) {
        return handlerException(HttpStatus.NOT_FOUND, ex);
    }

    private ResponseEntity<WebError> handlerException(HttpStatus status, Exception exception) {
        return ResponseEntity
                .status(status)
                .body(
                        new WebError(status, exception.getMessage()));
    }
}
