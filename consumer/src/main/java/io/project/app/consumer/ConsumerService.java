package io.project.app.consumer;

import com.project.tw.domain.StatusModel;
import java.time.Duration;
import static java.time.Duration.ofSeconds;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import static reactor.util.retry.Retry.backoff;

@Service
@Slf4j
public class ConsumerService {

    private final RSocketRequester requesterMono;

    public ConsumerService(RSocketRequester.Builder builder) {
        this.requesterMono = builder
                .dataMimeType(MediaType.APPLICATION_CBOR)
                .tcp("producer", 2050);

    }

    public Flux<StatusModel> get() {
        return this.requesterMono.route("find.data")
                .data("nodata")
                .retrieveFlux(StatusModel.class).take(Duration.ZERO).cache(Duration.ZERO).retryWhen(backoff(5, ofSeconds(1)).maxBackoff(ofSeconds(20)));

    }

}
