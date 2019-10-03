/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.tw.services;

import com.project.tw.app.TwitterConfiguration;
import com.project.tw.domain.TweeterUser;
import com.project.tw.repositories.TwitterUserRepository;
import java.util.Date;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/**
 *
 * @author armena
 */
@Service
@Slf4j
@Lazy
public class StreamService {
    
    @Autowired
    private TwitterConfiguration twitterService;
    
    @Autowired
    private TwitterUserRepository twitterUserRepository;
    
    @PostConstruct
    public void init(){
        log.info("Start Stream !");
        startStream();
    }
    
    public void startStream() {                
        StatusListener listener = new StatusListener() {
            TweeterUser user = null;
            @Override
            public void onStatus(Status status) {
                user = new TweeterUser();
                log.info(status.getUser().getName() + " " + status.getText());
                user.setMessage(status.getText());
                user.setProfileImageURL(status.getUser().getProfileImageURL());
                user.setTweetUserName(status.getUser().getName());
                user.setTweetUserScreenName(status.getUser().getScreenName());
                user.setTweetUserId(status.getUser().getId());
                user.setTimestamp(new Date(System.currentTimeMillis()).getTime());
                user.setTweetUserLocation(status.getUser().getLocation());
                twitterUserRepository.save(user);
            }
            
            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }
            
            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }
            
            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
            
            @Override
            public void onScrubGeo(long l, long l1) {
                
            }
            
            @Override
            public void onStallWarning(StallWarning sw) {
                
            }
        };
        twitterService.twitterStreamInstance().addListener(listener);
    }
    
}
