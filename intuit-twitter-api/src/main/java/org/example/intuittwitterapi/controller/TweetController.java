package org.example.intuittwitterapi.controller;

import org.example.intuittwitterapi.dal.TweetDAL;
import org.example.intuittwitterapi.model.Sentiment;
import org.example.intuittwitterapi.model.Tweet;
import org.example.intuittwitterapi.model.SentimentStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@RestController
@RequestMapping(value = "/api/v1/")
@Validated
public class TweetController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final TweetDAL tweetDAL;

    public TweetController(TweetDAL tweetDAL) {
        this.tweetDAL = tweetDAL;
    }

    @RequestMapping(value = "/tweets", method = RequestMethod.GET)
    public Flux<Tweet> getTweets(@RequestParam(required = false, defaultValue="20") @Min(1) @Max(50) int numTweets,
                                 @RequestParam(required = false) Sentiment sentiment) {
        LOG.info(String.format("Getting %d tweets.", numTweets));
        return tweetDAL.getTweets(numTweets, sentiment);
    }

    @RequestMapping(value = "/tweets/count", method = RequestMethod.GET)
    public Mono<Long> getCount() {
        LOG.info("Getting tweets count.");
        return tweetDAL.getCount();
    }

    @RequestMapping(value = "/tweets/stats", method = RequestMethod.GET)
    public Flux<SentimentStats> getStats() {
        LOG.info("Getting tweets stats.");
        return tweetDAL.getStats();
    }
}
