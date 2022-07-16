package org.example.utils;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.example.model.Tweet;
import org.mongoflink.serde.DocumentSerializer;

public class TweetSerializer implements DocumentSerializer<Tweet> {

    private final ObjectMapper objectMapper;

    public TweetSerializer(){
        objectMapper = new ObjectMapper();
    }

    @Override
    public Document serialize(Tweet tweet) {
        try {
            return Document.parse(objectMapper.writeValueAsString(tweet));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
