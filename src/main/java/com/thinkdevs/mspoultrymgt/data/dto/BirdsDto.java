package com.thinkdevs.mspoultrymgt.data.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BirdsDto {

    private String name;
    private Double numberOfBirds;
    private String date;
    private String house;
    private Integer categoryId;
    private Boolean active;
}
