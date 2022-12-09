package com.thinkdevs.mspoultrymgt.data.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FeedDto {

    private String name;
    private Integer quantity;
    private String date;
    private Double price;
    private String currency;
    private Boolean active;
}
