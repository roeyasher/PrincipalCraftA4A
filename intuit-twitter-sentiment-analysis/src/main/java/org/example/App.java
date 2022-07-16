package org.example;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.example.utils.*;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        List<String> keyWords = Arrays.asList(" Intuit ", " TurboTax ", " QuickBooks ", " Mint ");

        // creating Flink env
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        // creating tweets steam
        DataStream<String> stream = env.addSource(TwitterSourceInitializer.init());

        stream
            //Parsing the tweets
            .flatMap(new TweetParser())
            //Filtering the tweets
            .filter(new TweetFilter(keyWords))
            //Semantic analysis on the tweets
            .map(new TweetAnalyzer())
            .sinkTo(MongoSinkInitializer.init());

        // Execute pipeline
        env.execute("Flink Streaming Java API Skeleton");
    }
}
