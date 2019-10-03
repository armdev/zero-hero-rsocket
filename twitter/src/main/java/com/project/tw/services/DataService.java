/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.tw.services;

import com.project.tw.domain.TweeterUser;
import com.project.tw.repositories.TwitterUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 *
 * @author armena
 */
@Service
@Slf4j
@Lazy
public class DataService {
    
    @Autowired
    private TwitterUserRepository twitterUserRepository;
    
    public Flux<TweeterUser> fetchStream(){
        return twitterUserRepository.findTop50ByOrderByTimestampDesc();
    }
    
}
