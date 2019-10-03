package com.project.tw.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author armena
 */
@Service
@Component
public class TwitterConfiguration {

    @Value("${twitter.user}")
    private String user;

    @Value("${twitter.pass}")
    private String pass;

    @Value("${twitter.accesstoken}")
    private String accessToken;

    @Value("${twitter.accesstokensecret}")
    private String accessTokenSecret;

    @Value("${twitter.consumersecret}")
    private String consumerSecret;

    @Value("${twitter.consumerkey}")
    private String consumerkey;

    public Twitter twitterInstance() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setHttpConnectionTimeout(15 * 1000);
        cb.setHttpReadTimeout(60 * 1000);
        cb.setUser(user);
        cb.setPassword(pass);
        cb.setOAuthAccessToken(accessToken);
        cb.setOAuthAccessTokenSecret(accessTokenSecret);
        cb.setOAuthConsumerSecret(consumerSecret);
        cb.setOAuthConsumerKey(consumerkey);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        return twitter;

    }

    public TwitterStream twitterStreamInstance() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setHttpConnectionTimeout(15 * 1000);
        cb.setHttpReadTimeout(60 * 1000);
        cb.setUser(user);
        cb.setPassword(pass);
        cb.setOAuthAccessToken(accessToken);
        cb.setOAuthAccessTokenSecret(accessTokenSecret);
        cb.setOAuthConsumerSecret(consumerSecret);
        cb.setOAuthConsumerKey(consumerkey);
        return new TwitterStreamFactory(cb.build()).getInstance();

    }
}
