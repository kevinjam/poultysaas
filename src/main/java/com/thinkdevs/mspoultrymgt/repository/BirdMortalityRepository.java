package com.thinkdevs.mspoultrymgt.repository;

import com.thinkdevs.mspoultrymgt.data.respose.MortalityStatistics;
import com.thinkdevs.mspoultrymgt.model.BirdsMortality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BirdMortalityRepository extends JpaRepository<BirdsMortality, Integer> {
    @Query(value = "SELECT COUNT(id) as id, SUM(number_of_birds) AS TotalMortality FROM birds_mortalities", nativeQuery = true)
    MortalityStatistics sumStatistics();
}
