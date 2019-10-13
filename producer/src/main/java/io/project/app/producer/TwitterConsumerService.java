package io.project.app.producer;

import com.project.tw.domain.StatusModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class TwitterConsumerService {

    private final Mono<RSocketRequester> requesterMono;

    public TwitterConsumerService(RSocketRequester.Builder builder) {
        this.requesterMono = builder
                .dataMimeType(MediaType.APPLICATION_CBOR)
                .connectTcp("twitter", 2051).retry(5).cache();
    }

    public Flux<StatusModel> get() {
        log.info("Connecting with twitter");
        return this.requesterMono
                .flatMapMany(req
                        -> req.route("find.stream")
                        .data("NODATA")
                        .retrieveFlux(StatusModel.class))
                .take(4);

    }

}
