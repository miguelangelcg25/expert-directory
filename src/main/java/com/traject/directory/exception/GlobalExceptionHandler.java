package com.traject.directory.exception;

import java.util.Date;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(NoSuchElementException ex,
            WebRequest request) {
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        LOG.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ErrorMessage(ex.getMessage(), path, HttpStatus.NOT_FOUND.value(), new Date()),
                HttpStatus.NOT_FOUND);
    }

}
