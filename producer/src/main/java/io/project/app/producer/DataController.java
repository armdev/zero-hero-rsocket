package io.project.app.producer;

import com.project.tw.domain.StatusModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
@Service
@Slf4j
public class DataController {
    
    @Autowired
    private TwitterConsumerService twitterConsumerService;

    @MessageMapping("find.data")
    public Flux<StatusModel> dataGenerator() {
        log.info("I am receiving data from twitter");
        return twitterConsumerService.get();
    }

}
