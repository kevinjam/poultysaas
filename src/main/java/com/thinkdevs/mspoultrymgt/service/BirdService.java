package com.thinkdevs.mspoultrymgt.service;

import com.thinkdevs.mspoultrymgt.data.FarmProjection;
import com.thinkdevs.mspoultrymgt.data.dto.BirdsDto;
import com.thinkdevs.mspoultrymgt.model.Birds;
import com.thinkdevs.mspoultrymgt.repository.BirdRepository;
import com.thinkdevs.mspoultrymgt.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.thinkdevs.mspoultrymgt.data.FarmProjection.toProjection;
import static com.thinkdevs.mspoultrymgt.model.Birds.addNewBirds;
import static com.thinkdevs.mspoultrymgt.model.Birds.updateBirds;

@Service
@Transactional(readOnly = false)
@Slf4j
@RequiredArgsConstructor
public class BirdService {

    private final BirdRepository birdRepository;
    private final CategoryRepository categoryRepository;

    public List<Birds> getAllBirds() {
        return birdRepository.findAll();
    }

    public Birds addBirds(BirdsDto birdsDto) {
        var category = categoryRepository.getBirdCategory(birdsDto.getCategoryId());
        return birdRepository.save(addNewBirds(birdsDto,category));
    }

    public FarmProjection updateBird(Integer birdId, BirdsDto birdsDto) {
        var category = categoryRepository.getBirdCategory(birdsDto.getCategoryId());

        var birds = birdRepository.findById(birdId)
                .map(response-> updateBirds(response, birdsDto, category))
                .orElseThrow(()->new EntityNotFoundException("Bird not found for provided ID: " + birdId));
        birdRepository.saveAndFlush(birds);
        return toProjection(birds, category);
    }

    public void deleteBird(Integer birdId) {
        birdRepository.deleteById(birdId);
    }

    public Birds getBird(Integer birdId) {
        return birdRepository.findById(birdId)
                .orElseThrow(() -> new EntityNotFoundException("Bird not found for provided ID "));
    }
}
