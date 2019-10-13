package io.project.app.consumer;

import com.project.tw.domain.StatusModel;
import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class ConsumerController {

    @Autowired
    private final ConsumerService consumerServiceController;

    @CrossOrigin
    @GetMapping(path = "/data", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<?> stream() {
        log.info("Received data from producer");
        final Flux<StatusModel> dataStream = consumerServiceController.get()
                //.onBackpressureDrop()
                .limitRate(1)
                .limitRequest(10)
                .delayElements(Duration.ofSeconds(1)).repeat();
        return dataStream;
    }

}
