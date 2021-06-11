package ru.narsabu.deliveryapi.util;

import lombok.val;
import ru.narsabu.deliveryapi.dto.ErrorMessageDto;

import java.util.Date;

public class GenerateErrorMessage {

    public static ErrorMessageDto generateErrorMessageDto(String message) {
        val errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setDate(new Date());
        errorMessageDto.setMessage(message);

        return errorMessageDto;
    }
}
