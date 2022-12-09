package com.thinkdevs.mspoultrymgt.service;

import com.thinkdevs.mspoultrymgt.data.FarmProjection;
import com.thinkdevs.mspoultrymgt.data.dto.EggsDto;
import com.thinkdevs.mspoultrymgt.data.respose.EggStatistics;
import com.thinkdevs.mspoultrymgt.model.Eggs;
import com.thinkdevs.mspoultrymgt.repository.EggsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.thinkdevs.mspoultrymgt.data.FarmProjection.toProjection;
import static com.thinkdevs.mspoultrymgt.model.Eggs.addNewEggs;
import static com.thinkdevs.mspoultrymgt.model.Eggs.updateEggs;

@Service
@Transactional(readOnly = false)
@Slf4j
@RequiredArgsConstructor
public class EggsService {

    private final EggsRepository eggsRepository;

    public List<Eggs> getAllEggs() {
        return eggsRepository.findAll();
    }

    public Eggs addEggs(EggsDto eggsDto) {
        return eggsRepository.save(addNewEggs(eggsDto));
    }

    public FarmProjection updateEgg(Integer eggsId, EggsDto eggsDto) {
        var eggs = eggsRepository.findById(eggsId)
                .map(response-> updateEggs(response, eggsDto))
                .orElseThrow(()->new EntityNotFoundException("Eggs not found for provided ID: " + eggsId));
        eggsRepository.saveAndFlush(eggs);
        return toProjection(eggs);
    }

    public void deleteEggs(Integer eggsId) {
        eggsRepository.deleteById(eggsId);
    }

    public Eggs getEgg(Integer eggsId) {
        return eggsRepository.findById(eggsId)
                .orElseThrow(() -> new EntityNotFoundException("Eggs not found for provided ID : " +eggsId));
    }

    public EggStatistics eggStatistics(){
        return eggsRepository.sumStatistics();
    }
}
