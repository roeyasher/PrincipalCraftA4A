package org.example.utils;

import org.apache.flink.streaming.connectors.twitter.TwitterSource;

import java.io.IOException;
import java.util.Properties;

public class TwitterSourceInitializer {

    // get twitter credentials from properties file
    public static TwitterSource init() {
        try {
            Properties properties = new Properties();
            properties.load(TwitterSource.class.getClassLoader().getResourceAsStream("app.properties"));
            properties.setProperty(TwitterSource.CONSUMER_KEY, properties.getProperty("tweeter.api.key"));
            properties.setProperty(TwitterSource.CONSUMER_SECRET, properties.getProperty("tweeter.api.key.secret"));
            properties.setProperty(TwitterSource.TOKEN, properties.getProperty("tweeter.access.token"));
            properties.setProperty(TwitterSource.TOKEN_SECRET, properties.getProperty("tweeter.access.token.secret"));
            return new TwitterSource(properties);
        } catch (IOException e) {
            throw new RuntimeException("Error while loading properties");
        }
    }

}
