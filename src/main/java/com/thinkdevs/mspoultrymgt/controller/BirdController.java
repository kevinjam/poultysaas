package com.thinkdevs.mspoultrymgt.controller;

import com.thinkdevs.mspoultrymgt.data.FarmProjection;
import com.thinkdevs.mspoultrymgt.data.dto.BirdsDto;
import com.thinkdevs.mspoultrymgt.data.respose.Response;
import com.thinkdevs.mspoultrymgt.model.Birds;
import com.thinkdevs.mspoultrymgt.service.BirdService;
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
@RequestMapping("/api/v1/poultry/bird")
@CrossOrigin
public class BirdController {

    private final BirdService birdService;

    @GetMapping
    @ApiOperation(value = "",
            response = ResponseEntity.class)
    public ResponseEntity<Response<List<Birds>>> getAllBirds() {
        return ok(ofData(birdService.getAllBirds()));
    }

    @GetMapping("/{birdIs}")
    @ApiOperation(value = "Get Bird",
            response = ResponseEntity.class)
    public ResponseEntity<Response<Birds>> getBird(@PathVariable Integer birdIs) {
        return ok(ofData(birdService.getBird(birdIs)));
    }


    @PutMapping("/{birdIs}")
    public ResponseEntity<Response<FarmProjection>> updateBird(@PathVariable Integer birdIs,
                                                               @RequestBody BirdsDto birdsDto) {
        return ok(ofData(birdService.updateBird(birdIs, birdsDto)));
    }

    @PostMapping
    public ResponseEntity<Response<Birds>> saveFeed(@RequestBody BirdsDto birdsDto) {
        return new ResponseEntity<>(ofData(birdService.addBirds(birdsDto), 200), HttpStatus.CREATED);
    }

    @DeleteMapping("/{birdIs}")
    public ResponseEntity<Void> deleteBird(@PathVariable Integer birdIs) {
        birdService.deleteBird(birdIs);
        return noContent().build();
    }

}
