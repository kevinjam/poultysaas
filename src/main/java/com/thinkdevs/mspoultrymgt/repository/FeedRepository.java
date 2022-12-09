package com.thinkdevs.mspoultrymgt.repository;

import com.thinkdevs.mspoultrymgt.data.respose.FeedStatistics;
import com.thinkdevs.mspoultrymgt.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedRepository extends JpaRepository<Feed, Integer> {
    @Query(value = "SELECT COUNT(id) as id, SUM(quantity) AS TotalQuantity, SUM(price) AS Price, SUM(total_price) as TotalPrice FROM feeds", nativeQuery = true)
    FeedStatistics sumStatistics();


}
