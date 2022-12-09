package com.thinkdevs.mspoultrymgt.service;

import com.thinkdevs.mspoultrymgt.data.FarmProjection;
import com.thinkdevs.mspoultrymgt.data.dto.FeedDto;
import com.thinkdevs.mspoultrymgt.data.respose.EggStatistics;
import com.thinkdevs.mspoultrymgt.data.respose.FeedStatistics;
import com.thinkdevs.mspoultrymgt.model.Feed;
import com.thinkdevs.mspoultrymgt.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.thinkdevs.mspoultrymgt.data.FarmProjection.toProjection;
import static com.thinkdevs.mspoultrymgt.model.Feed.createNewFeeds;
import static com.thinkdevs.mspoultrymgt.model.Feed.updateFeeds;

@Service
@Transactional(readOnly = false)
@Slf4j
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;

    public List<Feed> getAllFeeds() {
        return feedRepository.findAll();
    }

    public Feed addFeeds(FeedDto feedDto) {
        return feedRepository.save(createNewFeeds(feedDto));
    }

    public FarmProjection updateFeed(Integer feedId, FeedDto feedDto) {
        var feeds = feedRepository.findById(feedId)
                .map(response-> updateFeeds(response, feedDto))
                .orElseThrow(()->new EntityNotFoundException("Feed not found for provided ID: " + feedId));
        feedRepository.saveAndFlush(feeds);
        return toProjection(feeds);
    }

    public void deleteFeed(Integer feedId) {
        feedRepository.deleteById(feedId);
    }

    public Feed getFeed(Integer feedId) {
        return feedRepository.findById(feedId)
                .orElseThrow(() -> new EntityNotFoundException("Feed not found for provided ID "));
    }

    public FeedStatistics feedStatistics(){
        return feedRepository.sumStatistics();
    }
}
