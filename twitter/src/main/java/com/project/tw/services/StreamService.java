/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.tw.services;

import com.project.tw.app.TwitterConfiguration;
import com.project.tw.domain.StatusModel;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.UserStreamListener;

/**
 *
 * @author Admin
 */
@Service
@Component
@Slf4j
public class StreamService implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DataService dataService;

    @Autowired
    private TwitterConfiguration twitterService;

    private TwitterStream twitterStream;

    private StatusListener listener;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    public StreamService() {

    }

    public StreamService(TwitterStream twitterStream, UserStreamListener listener) {

        this.twitterStream = twitterStream;
        this.listener = listener;

    }

    @PostConstruct
    public void init() {

        log.info("StreamService: started ");
        //  Configuration conf = twitterService.twitterInstance().getConfiguration();//
        twitterStream = twitterService.twitterStreamInstance();
        twitterStream.addListener(listener);
        twitterStream.sample();
        //twitterStream.user();

        this.listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                try {
                    //push to kafka
                    String str = "@" + status.getUser().getScreenName() + " - " + status.getText();
                    log.info(str);
                    StatusModel statusModel = new StatusModel();

                    statusModel.setDefaultProfileImage(status.getUser().isDefaultProfileImage());
                    statusModel.setBiggerProfileImageURL(status.getUser().getBiggerProfileImageURL() != null ? status.getUser().getBiggerProfileImageURL() : null);
                    statusModel.setContributorsEnabled(status.getUser().isContributorsEnabled());
                    statusModel.setFollowersCount(status.getUser().getFollowersCount());
                    statusModel.setFriendsCount(status.getUser().getFriendsCount());
                    statusModel.setLang(status.getUser().getLang() != null ? status.getUser().getLang() : null);
                    statusModel.setListCount(status.getUser().getListedCount());
                    statusModel.setProfileImageURL(status.getUser().getProfileImageURL() != null ? status.getUser().getProfileImageURL() : null);
                    //  tweeterUser.setProfileImageURLHttps(status.getUser().getProfileImageURLHttps()!= null ? status.getUser().getProfileImageURLHttps() : null);
                    statusModel.setTweetUserId(status.getUser().getId());
                    statusModel.setTweetUserName(status.getUser().getName() != null ? status.getUser().getName() : null);
                    statusModel.setTweetUserScreenName(status.getUser().getScreenName() != null ? status.getUser().getScreenName() : null);
                    statusModel.setTimeZone(status.getUser().getTimeZone() != null ? status.getUser().getTimeZone() : null);
                    statusModel.setTweetUserLocation(status.getUser().getLocation() != null ? status.getUser().getLocation() : null);
                    statusModel.setTweetUserDescription(status.getUser().getDescription() != null ? status.getUser().getDescription() : null);
                    statusModel.setUrl(status.getUser().getURL() != null ? status.getUser().getURL() : null);
                    statusModel.setStatusesCount(status.getUser().getStatusesCount());
                    statusModel.setUtcOffset(status.getUser().getUtcOffset());
                    statusModel.setUserCreatedAt(status.getUser().getCreatedAt());
                    statusModel.setMessage(status.getText());
                    dataService.saveIt(statusModel);

                } catch (Exception ex) {
                    ex.printStackTrace();

                }

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
              //  log.info("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                //log.info("Got a track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                //log.info("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                //log.info("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
                System.out.println("onException:" + ex.getMessage());
            }

        };

    }
}
