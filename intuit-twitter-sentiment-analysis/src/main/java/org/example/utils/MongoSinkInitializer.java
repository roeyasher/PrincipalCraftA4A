package org.example.utils;

import org.apache.flink.streaming.connectors.twitter.TwitterSource;
import org.example.model.Tweet;
import org.mongoflink.config.MongoOptions;
import org.mongoflink.sink.MongoSink;

import java.io.IOException;
import java.util.Properties;

public class MongoSinkInitializer {

    // get flink-mongo configuration from properties file
    public static MongoSink<Tweet> init() {
        try {
            Properties properties = new Properties();
            properties.load(TwitterSource.class.getClassLoader().getResourceAsStream("app.properties"));
            properties.setProperty(MongoOptions.SINK_TRANSACTION_ENABLED,
                    properties.getProperty("mongo.sink.transaction.enabled"));
            properties.setProperty(MongoOptions.SINK_FLUSH_ON_CHECKPOINT,
                    properties.getProperty("mongo.sink.flush.on.checkpoint"));
            properties.setProperty(MongoOptions.SINK_FLUSH_SIZE,
                    properties.getProperty("mongo.sink.flush.size"));
            properties.setProperty(MongoOptions.SINK_FLUSH_INTERVAL,
                    properties.getProperty("mongo.sink.flush.interval"));
            String url = properties.getProperty("mongo.url");
            String db = properties.getProperty("mongo.db");
            String col = properties.getProperty("mongo.col");
            return new MongoSink<>(url, db, col, new TweetSerializer(), properties);
        } catch (IOException e) {
            throw new RuntimeException("Error while loading properties");
        }
    }
}
