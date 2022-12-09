package com.thinkdevs.mspoultrymgt.data.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
public class BirdsPurchaseDto {

    private String name;
    private String purchasedDate;
    private Double numberOfBirds;
    private Double prices;
}
