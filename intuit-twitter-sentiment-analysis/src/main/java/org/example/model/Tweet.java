package org.example.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Tweet {

    private String id;
    private String text;
    private String lang;
    private String userName;
    private long timestamp;
    private Sentiment sentiment;
}
