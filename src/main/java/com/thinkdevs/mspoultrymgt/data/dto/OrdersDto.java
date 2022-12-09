package com.thinkdevs.mspoultrymgt.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class OrdersDto {

    private String phoneNumber;
    private String prodName;
    private BigDecimal cost;
    private String status;

}
