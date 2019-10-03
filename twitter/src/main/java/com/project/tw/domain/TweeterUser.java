package com.project.tw.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "tweeter_user")
public class TweeterUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;    
    private Long tweetUserId;
    @TextIndexed(weight=2)
    private String tweetUserName;
    private String tweetUserScreenName;
    private String profileImageURL;
    @TextIndexed(weight=2)
    private String tweetUserLocation;    
    @TextIndexed(weight=2)
    private String message;    
    private long timestamp;   

   
}
