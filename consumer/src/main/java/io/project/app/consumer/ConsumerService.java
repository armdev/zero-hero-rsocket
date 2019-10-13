package io.project.app.consumer;

import com.project.tw.domain.StatusModel;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ConsumerService {

    private final Mono<RSocketRequester> requesterMono;

    public ConsumerService(RSocketRequester.Builder builder) {
        this.requesterMono = builder
                .dataMimeType(MediaType.APPLICATION_CBOR)
                .connectTcp("producer", 2050).retry(5).cache();
    }

    public Flux<StatusModel> get() {
        return this.requesterMono
                .flatMapMany(req
                        -> req.route("find.data")
                        .data("nodata")
                        .retrieveFlux(StatusModel.class))
                .take(4);

    }

}
