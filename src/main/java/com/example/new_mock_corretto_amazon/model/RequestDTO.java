package com.example.new_mock_corretto_amazon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestDTO {

    private String rqUID;
    private String clientId;
    private String account;
    private String openDate;
    private String closeDate;

}
