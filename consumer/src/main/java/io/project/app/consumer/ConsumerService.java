package io.project.app.consumer;

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
                .connectTcp("127.0.0.1", 2050).retry(5).cache();
    }

    public Flux<String> get(String id) {

        return this.requesterMono
                .flatMapMany(req
                        -> req.route("find.data.{id}", id)
                        .data(id)
                        .retrieveFlux(String.class))
                .take(4);

    }

}
