package com.thinkdevs.mspoultrymgt.controller;

import com.thinkdevs.mspoultrymgt.data.FarmProjection;
import com.thinkdevs.mspoultrymgt.data.dto.FeedDto;
import com.thinkdevs.mspoultrymgt.data.respose.EggStatistics;
import com.thinkdevs.mspoultrymgt.data.respose.FeedStatistics;
import com.thinkdevs.mspoultrymgt.data.respose.Response;
import com.thinkdevs.mspoultrymgt.model.Feed;
import com.thinkdevs.mspoultrymgt.service.FeedService;
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
@RequestMapping("/api/v1/poultry/feed")
@CrossOrigin(value = "http://localhost:3000")
public class FeedController {

    private final FeedService feedService;

    @GetMapping
    @ApiOperation(value = "",
            response = ResponseEntity.class)
    public ResponseEntity<Response<List<Feed>>> getAllFeeds() {
        return ok(ofData(feedService.getAllFeeds()));
    }

    @GetMapping("/{feedId}")
    @ApiOperation(value = "Get Feed",
            response = ResponseEntity.class)
    public ResponseEntity<Response<Feed>> getFeed(@PathVariable Integer feedId) {
        return ok(ofData(feedService.getFeed(feedId)));
    }


    @PutMapping("/{feedId}")
    public ResponseEntity<Response<FarmProjection>> updateFeeds(@PathVariable Integer feedId,
                                                                       @RequestBody FeedDto feedDto) {
        return ok(ofData(feedService.updateFeed(feedId, feedDto)));
    }

    @PostMapping
    public ResponseEntity<Response<Feed>> saveFeed(@RequestBody FeedDto feedDto) {
        return new ResponseEntity<>(ofData(feedService.addFeeds(feedDto), 200), HttpStatus.CREATED);
    }

    @DeleteMapping("/{feedId}")
    public ResponseEntity<Void> deleteFeed(@PathVariable Integer feedId) {
        feedService.deleteFeed(feedId);
        return noContent().build();
    }

    @GetMapping("/statistics")
    @ApiOperation(value = "Get Feeds statistic",
            response = ResponseEntity.class)
    public ResponseEntity<Response<FeedStatistics>> getStatistic() {
        return ok(ofData(feedService.feedStatistics()));
    }



}
