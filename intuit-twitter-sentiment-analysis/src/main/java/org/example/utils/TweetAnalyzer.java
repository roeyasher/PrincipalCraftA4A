package org.example.utils;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import org.apache.flink.api.common.functions.MapFunction;
import org.example.model.Sentiment;
import org.example.model.Tweet;

import java.util.Properties;

public class TweetAnalyzer implements MapFunction<Tweet, Tweet> {

    private final Properties props;

    public TweetAnalyzer() {
        props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
    }

    @Override
    public Tweet map(Tweet tweet) throws Exception {
        tweet.setSentiment(getSentiment(tweet.getText()));
        return tweet;
    }

    private Sentiment getSentiment(String text) {
        int score = 0;
//        int totalSize = 0;
//        int totalScore = 0;
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        if (text != null && text.length() > 0) {
            Annotation annotation = pipeline.process(text);
            // Calculate weighted sentiment on the tweet
            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                score = RNNCoreAnnotations.getPredictedClass(tree);
//                totalSize += sentence.size();
//                totalScore += sentence.size() * score;
            }
        }
//        return Sentiment.fromFloat((1.0f * totalScore / totalSize));
        return Sentiment.fromInteger(score);
    }
}
