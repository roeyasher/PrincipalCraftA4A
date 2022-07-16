package org.example.intuittwitterapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Document
public class SentimentStats {

    @Id
    Sentiment sentiment;
    long sentimentCnt;
}
