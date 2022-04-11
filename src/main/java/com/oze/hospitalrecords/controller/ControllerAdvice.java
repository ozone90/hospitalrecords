package com.oze.hospitalrecords.controller;

import com.google.common.base.Joiner;
import com.oze.hospitalrecords.dto.ErrorMessage;
import com.oze.hospitalrecords.enums.ResponseCodes;
import com.oze.hospitalrecords.exception.BadRequestException;
import com.oze.hospitalrecords.exception.ConflictException;
import com.oze.hospitalrecords.exception.ForbiddenException;
import com.oze.hospitalrecords.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.ControllerAdvice
@Slf4j
/**
 * This class is used as the global excpetion handler
 */
public class ControllerAdvice
{
    /**
     * This method is used to handle BadRequestException
     * @param ex Exception to be caught
     * @return ResponseEntity to be returned
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestException ex) {
        ErrorMessage message = new ErrorMessage(ResponseCodes.BAD_REQUEST.getCode(),ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is used to handle InternalServerException
     * @param ex Exception to be caught
     * @return ResponseEntity to be returned
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception ex) {
        log.error("Exception Message: {}",ex.getMessage());
        log.error("Localized Exception Message: {}",ex.getLocalizedMessage());
        ErrorMessage message = new ErrorMessage(ResponseCodes.SYSTEM_ERROR.getCode(), ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method is used to handle ForbiddenException
     * @param ex Exception to be caught
     * @return ResponseEntity to be returned
     */
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorMessage> handleForbiddenException(ForbiddenException ex) {
        log.error("Exception Message: {}",ex.getMessage());
        log.error("Localized Exception Message: {}",ex.getLocalizedMessage());
        ErrorMessage message = new ErrorMessage(ResponseCodes.SYSTEM_ERROR.getCode(), ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    /**
     * This method is used to handle ConflictException
     * @param ex Exception to be caught
     * @return ResponseEntity to be returned
     */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorMessage> handleConflictException(ConflictException ex) {
        ErrorMessage message = new ErrorMessage(ResponseCodes.DUPLICATE_RECORD.getCode(), ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }


    /**
     * This method is used to handle NotFoundException
     * @param ex Exception to be caught
     * @return ResponseEntity to be returned
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex) {
        ErrorMessage message = new ErrorMessage(ResponseCodes.RECORD_NOT_FOUND.getCode(), ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }


    /**
     * This method is used to handle MethodArgumentNotValidException
     * @param ex Exception to be caught
     * @return ResponseEntity to be returned
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage>  handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
         String errorDescription = Joiner.on(",").withKeyValueSeparator("=").join(errors);
        ErrorMessage message = new ErrorMessage(ResponseCodes.BAD_REQUEST.getCode(), errorDescription);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
