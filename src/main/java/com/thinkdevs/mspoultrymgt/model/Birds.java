package com.thinkdevs.mspoultrymgt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkdevs.mspoultrymgt.data.dto.BirdsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "birds")
@Where(clause = "active <> 'false'")
public class Birds extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double numberOfBirds;
    private String date;
    private String house;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private BirdCategory category;
    private Boolean active;

    public Birds(BirdsDto birdsDto, BirdCategory birdCategory) {
        setData(birdsDto, birdCategory);
    }

    private void setData(BirdsDto birdsDto, BirdCategory birdCategory) {
        this.name = birdsDto.getName();
        this.numberOfBirds = birdsDto.getNumberOfBirds();
        this.date = birdsDto.getDate();
        this.house = birdsDto.getHouse();
        this.category = birdCategory;
        this.active = true;
        this.dateCreated = LocalDateTime.now();
        this.lastModifiedDate= LocalDateTime.now();
    }

    public Birds(Birds response, BirdsDto birdsDto, BirdCategory birdCategory) {
        this.id = response.getId();
        setData(birdsDto, birdCategory);

    }

    public static Birds addNewBirds(BirdsDto birdsDto, BirdCategory category){
        return new Birds(birdsDto, category);
    }

    public static Birds updateBirds(Birds response, BirdsDto birdsDto, BirdCategory category){
        return new Birds(response, birdsDto, category);
    }
}
