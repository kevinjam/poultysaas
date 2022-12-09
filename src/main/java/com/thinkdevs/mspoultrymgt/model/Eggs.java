package com.thinkdevs.mspoultrymgt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkdevs.mspoultrymgt.data.dto.BirdsDto;
import com.thinkdevs.mspoultrymgt.data.dto.EggsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "eggs")
@Where(clause = "active <> 'false'")
public class Eggs extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eggs_generator")
    @SequenceGenerator(name = "eggs_generator", sequenceName = "eggs_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    private String date;
    private Integer numberOfEggs;
    private Integer badEggs;
    private Double trays;
    private String notes;
    private Boolean active;

    public Eggs(EggsDto eggsDto) {
        setData(eggsDto);
    }

    private void setData(EggsDto eggsDto) {
        this.date = eggsDto.getDate();
        this.numberOfEggs = eggsDto.getNumberOfEggs();
        this.badEggs = eggsDto.getBadEggs();
        this.trays = (double) (eggsDto.getNumberOfEggs() / 30);
        this.notes = eggsDto.getNotes();
        this.active = true;
        this.dateCreated = LocalDateTime.now();
        this.lastModifiedDate= LocalDateTime.now();
    }

    public Eggs(Eggs response, EggsDto eggsDto) {
        this.id = response.getId();
        setData(eggsDto);

    }

    public static Eggs addNewEggs(EggsDto eggsDto){
        return new Eggs(eggsDto);
    }

    public static Eggs updateEggs(Eggs response, EggsDto eggsDto){
        return new Eggs(response, eggsDto);
    }
}
