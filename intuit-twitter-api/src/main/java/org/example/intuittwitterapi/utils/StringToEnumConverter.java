package org.example.intuittwitterapi.utils;

import org.example.intuittwitterapi.model.Sentiment;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, Sentiment> {

    @Override
    public Sentiment convert(String input) {
        try{
            return Sentiment.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
