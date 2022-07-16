package org.example.intuittwitterapi.dal;

import org.example.intuittwitterapi.model.Sentiment;
import org.example.intuittwitterapi.model.SentimentStats;
import org.example.intuittwitterapi.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Repository
public class TweetDALImpl implements TweetDAL{

    @Autowired
    ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<Tweet> getTweets(int numTweets, Sentiment sentiment) {
        Query query = new Query();
        if (sentiment != null){
            query.addCriteria(Criteria.where("sentiment").is(sentiment.toString()));
        }
        return mongoTemplate.find(query.limit(numTweets), Tweet.class);
    }

    @Override
    public Mono<Long> getCount() {
        return mongoTemplate.count(new Query(), Tweet.class);
    }

    @Override
    public Flux<SentimentStats> getStats() {
        GroupOperation groupByStateAndSumPop = group("sentiment").count().as("sentimentCnt");
        Aggregation aggregation = newAggregation(groupByStateAndSumPop);
        return mongoTemplate.aggregate(aggregation, Tweet.class, SentimentStats.class);
    }
}
