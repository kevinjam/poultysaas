package com.thinkdevs.mspoultrymgt.controller;

import com.thinkdevs.mspoultrymgt.data.FarmProjection;
import com.thinkdevs.mspoultrymgt.data.dto.BirdsMortalityDto;
import com.thinkdevs.mspoultrymgt.data.dto.BirdsPurchaseDto;
import com.thinkdevs.mspoultrymgt.data.respose.FeedStatistics;
import com.thinkdevs.mspoultrymgt.data.respose.MortalityStatistics;
import com.thinkdevs.mspoultrymgt.data.respose.Response;
import com.thinkdevs.mspoultrymgt.model.BirdsMortality;
import com.thinkdevs.mspoultrymgt.model.BirdsPurchase;
import com.thinkdevs.mspoultrymgt.service.BirdMortalityService;
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
@RequestMapping("/api/v1/poultry/mortality")
@CrossOrigin
public class BirdMortalityController {

    private final BirdMortalityService birdMortalityService;

    @GetMapping
    @ApiOperation(value = "",
            response = ResponseEntity.class)
    public ResponseEntity<Response<List<BirdsMortality>>> getBirdPurchase() {
        return ok(ofData(birdMortalityService.getAllBirdsMortality()));
    }

    @GetMapping("/{birdId}")
    @ApiOperation(value = "Get Bird Purchase",
            response = ResponseEntity.class)
    public ResponseEntity<Response<BirdsMortality>> getBirdPurchase(@PathVariable Integer birdId) {
        return ok(ofData(birdMortalityService.getBird(birdId)));
    }


    @PutMapping("/{birdId}")
    public ResponseEntity<Response<FarmProjection>> updateEmployee(@PathVariable Integer birdId,
                                                                       @RequestBody BirdsMortalityDto birdsMortalityDto) {
        return ok(ofData(birdMortalityService.updateBirdMortalities(birdId, birdsMortalityDto)));
    }

    @PostMapping
    public ResponseEntity<Response<BirdsMortality>> saveDisciple(@RequestBody BirdsMortalityDto birdsMortalityDto) {
        return new ResponseEntity<>(ofData(birdMortalityService.addBirdMortality(birdsMortalityDto), 200), HttpStatus.CREATED);
    }

    @DeleteMapping("/{birdId}")
    public ResponseEntity<Void> deleteBird(@PathVariable Integer birdId) {
        birdMortalityService.deleteBirdMortality(birdId);
        return noContent().build();
    }

    @GetMapping("/statistics")
    @ApiOperation(value = "Get Mortality Statistics",
            response = ResponseEntity.class)
    public ResponseEntity<Response<MortalityStatistics>> getStatistic() {
        return ok(ofData(birdMortalityService.mortalityStatistics()));
    }


}
