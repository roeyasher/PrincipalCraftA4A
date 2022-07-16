package org.example.intuittwitterapi.dal;

import org.example.intuittwitterapi.model.Sentiment;
import org.example.intuittwitterapi.model.Tweet;
import org.example.intuittwitterapi.model.SentimentStats;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TweetDAL {

    Flux<Tweet> getTweets(int numTweets, Sentiment sentiment);

    Flux<SentimentStats> getStats();

    Mono<Long> getCount();
}
