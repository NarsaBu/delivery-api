package ru.narsabu.deliveryapi.util;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.narsabu.deliveryapi.dto.ErrorMessageDto;
import ru.narsabu.deliveryapi.utils.GenerationUtils;

@SpringBootTest
public class GenerateErrorMessageTests {

    @Test
    public void generateErrorMessageTest() {
        //GIVEN
        val message = GenerationUtils.generateString();

        //WHEN
        val result = GenerateErrorMessage.generateErrorMessageDto(message);

        //THEN
        Assertions.assertEquals(result.getClass(), ErrorMessageDto.class);
        Assertions.assertEquals(result.getMessage(), message);
    }
}
