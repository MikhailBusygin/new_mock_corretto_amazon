package com.example.new_mock_corretto_amazon.controller;

import com.example.new_mock_corretto_amazon.model.RequestDTO;
import com.example.new_mock_corretto_amazon.model.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.Random;

@RestController
@Slf4j
public class MockController {

    ObjectMapper mapper = new ObjectMapper();
    Random random = new Random();

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO) {

        String clientId;
        String currency;
        int balance;
        int maxLimit;

        try {
            clientId = requestDTO.getClientId();
            char firstDigit = clientId.charAt(0);

            if (firstDigit == '8') {
                currency = "US";
                maxLimit = 2000;
            } else if (firstDigit == '9') {
                currency = "EU";
                maxLimit = 1000;
            } else {
                currency = "RUB";
                maxLimit = 10000;
            }
            balance = random.nextInt(maxLimit);
        } catch (Exception e) {
            log.error(String.format("********** RequestDTO **********\n%s", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO)));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        ResponseDTO responseDTO = new ResponseDTO(
                requestDTO.getRqUID(),
                clientId,
                requestDTO.getAccount(),
                currency,
                new BigDecimal(balance),
                new BigDecimal(maxLimit)
        );

        log.info(String.format("********** RequestDTO **********\n%s", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO)));
        log.info(String.format("********** ResponseDTO **********\n%s", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO)));

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
