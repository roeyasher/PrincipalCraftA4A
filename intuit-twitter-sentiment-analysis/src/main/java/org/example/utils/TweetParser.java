package org.example.utils;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.util.Collector;
import org.example.model.Tweet;

public class TweetParser implements FlatMapFunction<String, Tweet> {

    @Override
    public void flatMap(String data, Collector<Tweet> collector) throws Exception {
        Tweet tweet = getTweetFromJson(data);
        if (tweet != null) {
            collector.collect(tweet);
        }
    }

    private Tweet getTweetFromJson(String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode dataNode = objectMapper.readValue(data, JsonNode.class);
            if (dataNode.has("id")&&
                    dataNode.has("text") &&
                        dataNode.has("user") &&
                                dataNode.has("lang")){
                String id = dataNode.get("id").asText();
                String text = dataNode.get("text").asText();
                String lang = dataNode.get("lang").asText();
                String name = dataNode.get("user").get("name").asText();
                long timestamp = dataNode.get("timestamp_ms").asLong();
                return new Tweet(id, text, lang, name, timestamp, null);
            } else {
                return null;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
