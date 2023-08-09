package com.maicol1912.CloudGateway.exception;

import com.maicol1912.CloudGateway.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler{

   @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse>handleCustomException(CustomException exception){
       System.out.println("Entre en el exception handler");
       return ResponseEntity.status(exception.getStatus())
               .body(
                       ErrorResponse.builder()
                               .errorMessage(exception.getMessage())
                               .errorCode(exception.getErrorCode())
                               .build()
               );
    }
}
