package com.thinkdevs.mspoultrymgt.service;

import com.thinkdevs.mspoultrymgt.data.FarmProjection;
import com.thinkdevs.mspoultrymgt.data.dto.BirdsMortalityDto;
import com.thinkdevs.mspoultrymgt.data.respose.FeedStatistics;
import com.thinkdevs.mspoultrymgt.data.respose.MortalityStatistics;
import com.thinkdevs.mspoultrymgt.model.BirdsMortality;
import com.thinkdevs.mspoultrymgt.repository.BirdMortalityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.thinkdevs.mspoultrymgt.data.FarmProjection.toProjection;
import static com.thinkdevs.mspoultrymgt.model.BirdsMortality.addNewBirdMortality;
import static com.thinkdevs.mspoultrymgt.model.BirdsMortality.updateBirdMortality;

@Service
@Transactional(readOnly = false)
@Slf4j
@RequiredArgsConstructor
public class BirdMortalityService {

    private final BirdMortalityRepository birdMortalityRepository;

    public List<BirdsMortality> getAllBirdsMortality() {
        return birdMortalityRepository.findAll();
    }

    public BirdsMortality addBirdMortality(BirdsMortalityDto birdsMortalityDto) {
        return birdMortalityRepository.save(addNewBirdMortality(birdsMortalityDto));
    }

    public FarmProjection updateBirdMortalities(Integer birdId, BirdsMortalityDto birdsPurchaseDto) {
        var birdsMortality = birdMortalityRepository.findById(birdId)
                .map(response-> updateBirdMortality(response, birdsPurchaseDto))
                .orElseThrow(()->new EntityNotFoundException("Bird Mortality not found for provided ID: " + birdId));
        birdMortalityRepository.saveAndFlush(birdsMortality);
        return toProjection(birdsMortality);
    }

    public void deleteBirdMortality(Integer mortalityId) {
        birdMortalityRepository.deleteById(mortalityId);
    }

    public BirdsMortality getBird(Integer birdPurchaseId) {
        return birdMortalityRepository.findById(birdPurchaseId)
                .orElseThrow(() -> new EntityNotFoundException("Bird Mortality not found for provided ID "));
    }

    public MortalityStatistics mortalityStatistics(){
        return birdMortalityRepository.sumStatistics();
    }
}
