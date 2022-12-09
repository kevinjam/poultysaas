package com.thinkdevs.mspoultrymgt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "category")
@Where(clause = "active <> 'false'")
public class BirdCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    @SequenceGenerator(name = "category_generator", sequenceName = "category_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "name", length = 250, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Birds> birds = new ArrayList<>();

    @JsonIgnore
    private boolean active;
}
