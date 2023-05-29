package com.drones.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestApiExceptionController extends ResponseEntityExceptionHandler {



    @ExceptionHandler(
            {
                    DroneBatteryException.class,
                    DroneOverloadException.class,
                    DroneIllegalStateException.class
            })
    public ResponseEntity<Object> internalError(RuntimeException ex, HttpServletRequest re) {
        final String errorMessage = ex.getMessage();
        return buildErrorResponse(new ErrorResponse(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR, re.getPathInfo()));
    }



    @ExceptionHandler(
            {
                    DroneNotFoundException.class,
                    ImageNotFoundException.class,
                    MedicationNotFoundException.class,
                    DroneEventNotFoundException.class
            })
    public ResponseEntity<Object> notFoundEx(RuntimeException ex, HttpServletRequest re) {
        final String errorMessage = ex.getMessage();
        return buildErrorResponse(new ErrorResponse(errorMessage, HttpStatus.NOT_FOUND, re.getPathInfo()));
    }


    private ResponseEntity<Object> buildErrorResponse(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
