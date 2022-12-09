package com.thinkdevs.mspoultrymgt.repository;

import com.thinkdevs.mspoultrymgt.data.respose.EggStatistics;
import com.thinkdevs.mspoultrymgt.data.respose.FeedStatistics;
import com.thinkdevs.mspoultrymgt.model.Eggs;
import com.thinkdevs.mspoultrymgt.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EggsRepository extends JpaRepository<Eggs, Integer> {

    @Query(value = "SELECT COUNT(id) as id, SUM(number_of_eggs) AS TotalEggs, SUM(bad_eggs) AS TotalBadEggs, SUM(trays) as TotalTrays FROM eggs", nativeQuery = true)
    EggStatistics sumStatistics();

//    @Query(value = "SELECT SUM(number_of_eggs) as TotalNumberEggs , SUM(quantity) AS TotalQuantity, SUM(price) AS Price, SUM(total_price) as TotalPrice FROM feeds", nativeQuery = true)
//    FeedStatistics totalSumStatistic();
}
