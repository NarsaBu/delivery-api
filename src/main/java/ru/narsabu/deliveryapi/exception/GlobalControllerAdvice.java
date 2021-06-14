package ru.narsabu.deliveryapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.narsabu.deliveryapi.dto.ErrorMessageDto;
import ru.narsabu.deliveryapi.util.GenerateErrorMessage;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(AreaException.class)
    public ResponseEntity<ErrorMessageDto> areaNotFoundExceptionHandler(AreaException e) {
        return new ResponseEntity<>(
                GenerateErrorMessage.generateErrorMessageDto(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorMessageDto> productNotFoundExceptionHandler(ProductException e) {
        return new ResponseEntity<>(
                GenerateErrorMessage.generateErrorMessageDto(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ErrorMessageDto> orderNotFoundExceptionHandler(OrderException e) {
        return new ResponseEntity<>(
                GenerateErrorMessage.generateErrorMessageDto(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
