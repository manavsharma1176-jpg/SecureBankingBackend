package com.manav.securebanking.exception;


import com.manav.securebanking.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(
            AccountNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(java.time.LocalDateTime.now().toString());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }


        @ExceptionHandler(MethodArgumentNotValidException.class)
            public ResponseEntity<ErrorResponse>handleValidationException(
                    MethodArgumentNotValidException ex)
                    {
                        ErrorResponse errorResponse = new ErrorResponse();

                        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
                        errorResponse.setMessage("Validation Failed");
                        errorResponse.setTimestamp(java.time.LocalDateTime.now().toString());

                        return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(errorResponse);



    }






}
