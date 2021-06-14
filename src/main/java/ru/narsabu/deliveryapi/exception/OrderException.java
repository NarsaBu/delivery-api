package ru.narsabu.deliveryapi.exception;

public class OrderException extends RuntimeException {

    public OrderException(String message) {
        super(message);
    }
}
