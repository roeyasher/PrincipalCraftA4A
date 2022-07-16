package org.example.intuittwitterapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Document(value="tweets")
public class Tweet {

    @Id
    private String id;
    private String text;
    private String lang;
    private String userName;
    private long timestamp;
    private Sentiment sentiment;
}
