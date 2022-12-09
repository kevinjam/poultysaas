package com.thinkdevs.mspoultrymgt.service;

import com.thinkdevs.mspoultrymgt.data.FarmProjection;
import com.thinkdevs.mspoultrymgt.data.dto.BirdsPurchaseDto;
import com.thinkdevs.mspoultrymgt.data.respose.FeedStatistics;
import com.thinkdevs.mspoultrymgt.data.respose.PurchasedStatistic;
import com.thinkdevs.mspoultrymgt.model.BirdsPurchase;
import com.thinkdevs.mspoultrymgt.repository.BirdPurchaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.thinkdevs.mspoultrymgt.data.FarmProjection.toProjection;
import static com.thinkdevs.mspoultrymgt.model.BirdsPurchase.addNewBirdPurchased;
import static com.thinkdevs.mspoultrymgt.model.BirdsPurchase.updateBirdPurchased;

@Service
@Transactional(readOnly = false)
@Slf4j
@RequiredArgsConstructor
public class BirdPurchaseService {

    private final BirdPurchaseRepository birdPurchaseRepository;

    public List<BirdsPurchase> getAllBirdsPurchased() {
        return birdPurchaseRepository.findAll();
    }

    public BirdsPurchase addBirdPurchase(BirdsPurchaseDto birdsPurchaseDto) {
        return birdPurchaseRepository.save(addNewBirdPurchased(birdsPurchaseDto));
    }

    public FarmProjection updateBirdPurchase(Integer birdPurchaseId, BirdsPurchaseDto birdsPurchaseDto) {
        var birdPurchase = birdPurchaseRepository.findById(birdPurchaseId)
                .map(response-> updateBirdPurchased(response, birdsPurchaseDto))
                .orElseThrow(()->new EntityNotFoundException("Bird Purchased not found for provided ID: " + birdPurchaseId));
        birdPurchaseRepository.saveAndFlush(birdPurchase);
        return toProjection(birdPurchase);
    }

    public void deleteBirdPurchase(Integer birdPurchaseId) {
        birdPurchaseRepository.deleteById(birdPurchaseId);
    }

    public BirdsPurchase getBird(Integer birdPurchaseId) {
        return birdPurchaseRepository.findById(birdPurchaseId)
                .orElseThrow(() -> new EntityNotFoundException("Bird Purchased not found for provided ID "));
    }

    public PurchasedStatistic purchasedStatistic(){
        return birdPurchaseRepository.sumStatistics();
    }

}
