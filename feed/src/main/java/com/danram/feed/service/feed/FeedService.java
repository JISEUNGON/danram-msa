package com.danram.feed.service.feed;

import com.danram.feed.dto.request.feed.FeedAddRequestDto;
import com.danram.feed.dto.response.feed.FeedAddResponseDto;

import java.util.List;

public interface FeedService {
    public FeedAddResponseDto addFeed(FeedAddRequestDto dto, List<String> files);
}
