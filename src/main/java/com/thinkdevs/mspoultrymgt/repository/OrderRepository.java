package com.thinkdevs.mspoultrymgt.repository;

import com.thinkdevs.mspoultrymgt.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
//    @Query(value = "SELECT COUNT(id) as id, SUM(quantity) AS TotalQuantity, SUM(price) AS Price, SUM(total_price) as TotalPrice FROM feeds", nativeQuery = true)
//    FeedStatistics sumStatistics();


}
