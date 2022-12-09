package com.thinkdevs.mspoultrymgt.controller;

import com.thinkdevs.mspoultrymgt.data.FarmProjection;
import com.thinkdevs.mspoultrymgt.data.dto.BirdsPurchaseDto;
import com.thinkdevs.mspoultrymgt.data.respose.PurchasedStatistic;
import com.thinkdevs.mspoultrymgt.data.respose.Response;
import com.thinkdevs.mspoultrymgt.model.BirdsPurchase;
import com.thinkdevs.mspoultrymgt.service.BirdPurchaseService;
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
@RequestMapping("/api/v1/poultry/purchase")
@CrossOrigin
public class BirdPurchaseController {

    private final BirdPurchaseService birdPurchaseService;

    @GetMapping
    @ApiOperation(value = "",
            response = ResponseEntity.class)
    public ResponseEntity<Response<List<BirdsPurchase>>> getBirdPurchase() {
        return ok(ofData(birdPurchaseService.getAllBirdsPurchased()));
    }

    @GetMapping("/{birdId}")
    @ApiOperation(value = "Get Bird Purchase",
            response = ResponseEntity.class)
    public ResponseEntity<Response<BirdsPurchase>> getBirdPurchase(@PathVariable Integer birdId) {
        return ok(ofData(birdPurchaseService.getBird(birdId)));
    }


    @PutMapping("/{birdId}")
    public ResponseEntity<Response<FarmProjection>> updateEmployee(@PathVariable Integer birdId,
                                                                       @RequestBody BirdsPurchaseDto birdsPurchaseDto) {
        return ok(ofData(birdPurchaseService.updateBirdPurchase(birdId, birdsPurchaseDto)));
    }

    @PostMapping
    public ResponseEntity<Response<BirdsPurchase>> saveDisciple(@RequestBody BirdsPurchaseDto birdsPurchaseDto) {
        return new ResponseEntity<>(ofData(birdPurchaseService.addBirdPurchase(birdsPurchaseDto), 200), HttpStatus.CREATED);
    }

    @DeleteMapping("/{birdId}")
    public ResponseEntity<Void> deleteLeader(@PathVariable Integer birdId) {
        birdPurchaseService.deleteBirdPurchase(birdId);
        return noContent().build();
    }

    @GetMapping("/statistics")
    @ApiOperation(value = "Get Purchased statistic",
            response = ResponseEntity.class)
    public ResponseEntity<Response<PurchasedStatistic>> getPurchased() {
        return ok(ofData(birdPurchaseService.purchasedStatistic()));
    }


}
