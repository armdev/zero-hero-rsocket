package com.project.tw.resources;

import com.project.tw.domain.TweeterUser;
import com.project.tw.services.DataService;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 *
 * @author armena
 */
@RestController
@RequestMapping("/api/v2/streams")
@Slf4j
public class StreamController {

    @Autowired
    private DataService dataService;

    @CrossOrigin
    @GetMapping(path = "/data", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<?> stream() {
        final Flux<TweeterUser> dataStream = dataService.fetchStream()
                .onBackpressureDrop()
                .limitRate(10)
                .limitRequest(1000)
                .delayElements(Duration.ofSeconds(3));
        return dataStream;
    }

}
