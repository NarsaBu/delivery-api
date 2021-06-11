package ru.narsabu.deliveryapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.narsabu.deliveryapi.dto.ErrorMessageDto;
import ru.narsabu.deliveryapi.util.GenerateErrorMessage;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(AreaNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> areaNotFoundExceptionHandler(AreaNotFoundException e) {
        return new ResponseEntity<>(
                GenerateErrorMessage.generateErrorMessageDto(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> productNotFoundExceptionHandler(ProductNotFoundException e) {
        return new ResponseEntity<>(
                GenerateErrorMessage.generateErrorMessageDto(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> orderNotFoundExceptionHandler(OrderNotFoundException e) {
        return new ResponseEntity<>(
                GenerateErrorMessage.generateErrorMessageDto(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
