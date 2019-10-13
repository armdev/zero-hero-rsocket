package com.project.tw.domain;

import java.io.Serializable;
import java.util.Date;
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
@Document(collection = "status_data")
public class StatusModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private Long tweetUserId;
    @TextIndexed(weight = 2)
    private String tweetUserName;
    private String tweetUserScreenName;
    private String profileImageURL;
    private String tweetUserLocation;
    private String tweetUserDescription;
    private boolean contributorsEnabled;
    private String biggerProfileImageURL;
    private String profileImageURLHttps;
    private boolean defaultProfileImage;
    private String url;
    private int followersCount;
    private int friendsCount;
    private Date userCreatedAt;
    private int userFavouritesCount;
    private int utcOffset;
    private String timeZone;
    private String lang;
    private int statusesCount;
    private int listCount;
    @TextIndexed(weight = 2)
    private String message;
    private long timestamp;

  

}
