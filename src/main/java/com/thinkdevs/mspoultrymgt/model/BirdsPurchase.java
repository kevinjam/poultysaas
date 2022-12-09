package com.thinkdevs.mspoultrymgt.model;

import com.thinkdevs.mspoultrymgt.data.dto.BirdsPurchaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "birds_purchased")
@Where(clause = "active <> 'false'")
public class BirdsPurchase extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String purchasedDate;
    private Double numberOfBirds;
    private Double prices;

    private Boolean active;

    public BirdsPurchase(BirdsPurchaseDto birdsPurchaseDto) {
        setData(birdsPurchaseDto);
    }

    private void setData(BirdsPurchaseDto birdsPurchaseDto) {
        this.name = birdsPurchaseDto.getName();
        this.purchasedDate = birdsPurchaseDto.getPurchasedDate();
        this.numberOfBirds = birdsPurchaseDto.getNumberOfBirds();
        this.prices = birdsPurchaseDto.getPrices();
        this.active = true;
        this.dateCreated = LocalDateTime.now();
        this.lastModifiedDate= LocalDateTime.now();
    }

    public BirdsPurchase(BirdsPurchase response, BirdsPurchaseDto birdsPurchaseDto) {
        this.id = response.getId();
        setData(birdsPurchaseDto);

    }

    public static BirdsPurchase addNewBirdPurchased(BirdsPurchaseDto birdsPurchaseDto){
        return new BirdsPurchase(birdsPurchaseDto);
    }

    public static BirdsPurchase updateBirdPurchased(BirdsPurchase response, BirdsPurchaseDto employeeDto){
        return new BirdsPurchase(response, employeeDto);
    }
}
