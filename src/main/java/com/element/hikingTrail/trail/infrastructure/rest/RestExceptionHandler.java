package com.element.hikingTrail.trail.infrastructure.rest;

import com.element.hikingTrail.trail.application.exception.BookingNotFound;
import com.element.hikingTrail.trail.application.exception.HikerNotEligible;
import com.element.hikingTrail.trail.application.exception.TrailNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {
            BookingNotFound.class,
            TrailNotFound.class})
    protected ResponseEntity<ErrorResponse> handleNotFound(Exception ex) {
        log.error("Error: resource not found: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorMessage(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {HikerNotEligible.class})
    protected ResponseEntity<ErrorResponse> handleHikerNotEligible(Exception ex) {
        log.error("Error: hiker not eligible: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorMessage(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
