package ru.narsabu.deliveryapi.exception;

public class AreaNotFoundException extends RuntimeException {

    public AreaNotFoundException(String message) {
        super(message);
    }
}
