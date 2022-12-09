package com.thinkdevs.mspoultrymgt.repository;

import com.thinkdevs.mspoultrymgt.model.BirdCategory;
import com.thinkdevs.mspoultrymgt.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;

public interface CategoryRepository extends JpaRepository<BirdCategory, Integer> {

    default BirdCategory getBirdCategory(Integer categoryId) {
        return findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found for " + categoryId));
    }
}
