package com.project.tw.resources;

import com.project.tw.domain.StatusModel;
import com.project.tw.services.DataService;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
@Service
@Slf4j
public class RsocketDataProvider {

    @Autowired
    private DataService dataService;

    @MessageMapping("find.stream")
    public Flux<?> stream() {
        log.info("Some one want data from me");
        final Flux<StatusModel> dataStream = dataService.fetchStream()
                .onBackpressureDrop()
                .limitRate(1)
                .limitRequest(10)
                .delayElements(Duration.ofSeconds(3));
        return dataStream;
    }


}
