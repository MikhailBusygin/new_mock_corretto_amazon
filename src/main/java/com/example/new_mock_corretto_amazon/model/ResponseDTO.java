package com.example.new_mock_corretto_amazon.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ResponseDTO {

    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private BigDecimal balance;
    private BigDecimal maxLimit;

}
