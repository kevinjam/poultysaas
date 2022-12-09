package com.thinkdevs.mspoultrymgt.data;

import com.fasterxml.jackson.annotation.*;
import com.thinkdevs.mspoultrymgt.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmProjection {
    private Integer id;
    private String name;
    private String purchasedDate;
    private Double numberOfBirds;
    private Double prices;

    private Integer quantity;
    private String date;
    private Double totalPrice;
    private Boolean active;
    private String house;
    private BirdCategory birdCategory;
    private String category;
    private Integer numberOfEggs;
    private Integer badEggs;
    private Double trays;
    private String notes;
    private String currency;
    private String customerNumber;
    private BigDecimal cost;
    private String status;
    private String prodName;




    private LocalDateTime dateCreated;


    public static FarmProjection toProjection(BirdsPurchase birdsPurchase) {
        return FarmProjection.builder()
                .id(birdsPurchase.getId())
                .name(birdsPurchase.getName())
                .purchasedDate(birdsPurchase.getPurchasedDate())
                .numberOfBirds(birdsPurchase.getNumberOfBirds())
                .prices(birdsPurchase.getPrices())
                .dateCreated(birdsPurchase.getDateCreated())
                .build();
    }

    public static FarmProjection toProjection(BirdsMortality birdsMortality) {
        return FarmProjection.builder()
                .id(birdsMortality.getId())
                .name(birdsMortality.getName())
                .numberOfBirds(birdsMortality.getNumberOfBirds())
                .dateCreated(birdsMortality.getDateCreated())
                .build();
    }

    public static FarmProjection toProjection(Feed feed) {
        return FarmProjection.builder()
                .id(feed.getId())
                .name(feed.getName())
                .quantity(feed.getQuantity())
                .date(feed.getDate())
                .totalPrice(feed.getTotalPrice())
                .currency(feed.getCurrency())
                .dateCreated(feed.getDateCreated())
                .build();
    }

    public static FarmProjection toProjection(Birds birds, BirdCategory category) {
        return FarmProjection.builder()
                .id(birds.getId())
                .name(birds.getName())
                .numberOfBirds(birds.getNumberOfBirds())
                .date(birds.getDate())
                .house(birds.getHouse())
                .category(category.getName())
                .dateCreated(birds.getDateCreated())
                .build();
    }

    public static FarmProjection toProjection(Eggs eggs) {
        return FarmProjection.builder()
                .id(eggs.getId())
                .numberOfEggs(eggs.getNumberOfEggs())
                .badEggs(eggs.getBadEggs())
                .date(eggs.getDate())
                .trays(eggs.getTrays())
                .notes(eggs.getNotes())
                .dateCreated(eggs.getDateCreated())
                .build();
    }

    public static FarmProjection toProjection(Orders orders) {
        return FarmProjection.builder()
                .customerNumber(orders.getPhoneNumber())
                .prodName(orders.getProdName())
                .cost(orders.getCost())
                .status(orders.getStatus())
                .id(orders.getId())
                .dateCreated(orders.getDateCreated())
                .build();
    }

}
