package com.thinkdevs.mspoultrymgt.repository;

import com.thinkdevs.mspoultrymgt.data.respose.PurchasedStatistic;
import com.thinkdevs.mspoultrymgt.model.BirdsPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BirdPurchaseRepository extends JpaRepository<BirdsPurchase, Integer> {
    @Query(value = "SELECT COUNT(id) as id, SUM(number_of_birds) AS TotalBirdPurchased, SUM(prices) AS TotalPrice FROM birds_purchased", nativeQuery = true)
    PurchasedStatistic sumStatistics();
}
