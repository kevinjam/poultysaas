package com.thinkdevs.mspoultrymgt.repository;

import com.thinkdevs.mspoultrymgt.model.Birds;
import com.thinkdevs.mspoultrymgt.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdRepository extends JpaRepository<Birds, Integer> {
}
