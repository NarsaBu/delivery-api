package ru.narsabu.deliveryapi.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessageDto {

    private Date date;

    private String message;
}
