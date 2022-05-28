package io.seamhealthv1.simplecrudapplication;


import io.seamhealthv1.simplecrudapplication.response.errorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.net.ConnectException;

@RestControllerAdvice
@ResponseBody
public class globalErrorHandler {

    /**
     * This will handle IndexOutOfBoundsExceptions
     * it will only return statusCode without body
     * */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public void handleNoTFound() {}

    /**
     * This will handle IndexOutOfBoundsExceptions
     * it will only return statusCode without body
     * */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClassNotFoundException.class)
    public void handleBadRequest() {}

    @ExceptionHandler(ConnectException.class)
    static ResponseEntity<?> error(){
        return new ResponseEntity(new errorMessage(500,"no database connection...contact support"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    static ResponseEntity<?> nullException(){
        return new ResponseEntity(new errorMessage(500,"field cannot be null..Hint: codebase error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    static ResponseEntity<?> duplicateData(){
        return new ResponseEntity(new errorMessage( 409,"user already exist"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    static ResponseEntity<?> invalidData(){
        return new ResponseEntity(new errorMessage(404,"unable to get data..Hints:no record in db"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    static  ResponseEntity<?> entityNotFound(){
        return new ResponseEntity(new errorMessage(404,"unable to get data:Hint: invalid Id"), HttpStatus.NOT_FOUND);
    }
}
