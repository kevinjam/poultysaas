package com.thinkdevs.mspoultrymgt.data.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class EggsDto {

    private String date;
    private Integer numberOfEggs;
    private Integer badEggs;
    private Double trays;
    private String notes;
    private Boolean active;
}
