package com.thinkdevs.mspoultrymgt.model;

import com.thinkdevs.mspoultrymgt.data.dto.BirdsMortalityDto;
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
@Table(name = "birds_mortalities")
@Where(clause = "active <> 'false'")
public class BirdsMortality extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double numberOfBirds;
    private Boolean active;

    public BirdsMortality(BirdsMortalityDto birdsMortalityDto) {
        setData(birdsMortalityDto);
    }

    private void setData(BirdsMortalityDto birdsMortalityDto) {
        this.name = birdsMortalityDto.getName();
        this.numberOfBirds = birdsMortalityDto.getNumberOfBirds();
        this.active = true;
        this.dateCreated = LocalDateTime.now();
        this.lastModifiedDate= LocalDateTime.now();
    }

    public BirdsMortality(BirdsMortality response, BirdsMortalityDto birdsMortalityDto) {
        this.id = response.getId();
        setData(birdsMortalityDto);

    }

    public static BirdsMortality addNewBirdMortality(BirdsMortalityDto birdsMortalityDto){
        return new BirdsMortality(birdsMortalityDto);
    }

    public static BirdsMortality updateBirdMortality(BirdsMortality response, BirdsMortalityDto birdsMortalityDto){
        return new BirdsMortality(response, birdsMortalityDto);
    }
}
