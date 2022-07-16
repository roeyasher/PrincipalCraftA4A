package org.example.utils;

import org.apache.flink.api.common.functions.FilterFunction;
import org.example.model.Tweet;

import java.util.List;

public class TweetFilter implements FilterFunction<Tweet> {
    private final List<String> keys;

    public TweetFilter(List<String> keys){
        this.keys = keys;
    }

    @Override
    public boolean filter(Tweet tweet) {
        return tweet.getLang().equals("en") &&
                keys.stream().map(x -> x.toLowerCase())
                        .anyMatch(tweet.getText().toLowerCase()::contains);
    }
}
