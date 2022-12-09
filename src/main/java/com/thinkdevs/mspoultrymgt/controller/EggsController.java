package com.thinkdevs.mspoultrymgt.controller;

import com.thinkdevs.mspoultrymgt.data.FarmProjection;
import com.thinkdevs.mspoultrymgt.data.dto.EggsDto;
import com.thinkdevs.mspoultrymgt.data.respose.EggStatistics;
import com.thinkdevs.mspoultrymgt.data.respose.Response;
import com.thinkdevs.mspoultrymgt.model.Eggs;
import com.thinkdevs.mspoultrymgt.service.EggsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.thinkdevs.mspoultrymgt.data.respose.Response.ofData;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/poultry/eggs")
@CrossOrigin
public class EggsController {

    private final EggsService eggsService;

    @GetMapping
    @ApiOperation(value = "",
            response = ResponseEntity.class)
    public ResponseEntity<Response<List<Eggs>>> getAllEggs() {
        return ok(ofData(eggsService.getAllEggs()));
    }

    @GetMapping("/{eggId}")
    @ApiOperation(value = "Get Eggs",
            response = ResponseEntity.class)
    public ResponseEntity<Response<Eggs>> getEgg(@PathVariable Integer eggId) {
        return ok(ofData(eggsService.getEgg(eggId)));
    }

    @GetMapping("/statistics")
    @ApiOperation(value = "Get Eggs",
            response = ResponseEntity.class)
    public ResponseEntity<Response<EggStatistics>> getStatistic() {
        return ok(ofData(eggsService.eggStatistics()));
    }


    @PutMapping("/{eggId}")
    public ResponseEntity<Response<FarmProjection>> updateEgg(@PathVariable Integer eggId,
                                                              @RequestBody EggsDto eggsDto) {
        return ok(ofData(eggsService.updateEgg(eggId, eggsDto)));
    }

    @PostMapping
    public ResponseEntity<Response<Eggs>> saveEggs(@RequestBody EggsDto eggsDto) {
        return new ResponseEntity<>(ofData(eggsService.addEggs(eggsDto), 200), HttpStatus.CREATED);
    }

    @DeleteMapping("/{eggId}")
    public ResponseEntity<Void> deleteFeed(@PathVariable Integer eggId) {
        eggsService.deleteEggs(eggId);
        return noContent().build();
    }


}
