/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.tw.services;

import com.project.tw.domain.StatusModel;
import com.project.tw.repositories.DataStreamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import com.project.tw.repositories.StatusStreamRepository;
import org.springframework.scheduling.annotation.Async;
import reactor.core.publisher.Mono;

/**
 *
 * @author armena
 */
@Service
@Slf4j
@Lazy
public class DataService {

    @Autowired
    private StatusStreamRepository twitterUserRepository;
    @Autowired
    private DataStreamRepository dataStreamRepository;

    public Flux<StatusModel> fetchStream() {
        return twitterUserRepository.findAll();
    }

    @Async
    public StatusModel saveIt(StatusModel model) {
        return dataStreamRepository.save(model);
    }

}
