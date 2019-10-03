package io.project.app.producer;

import reactor.core.publisher.Flux;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
@Service
public class DataController {

    @MessageMapping("find.data.{id}")
    public Flux<String> dataGenerator(@DestinationVariable String id) {
        return Flux.just("Data Stream From ", " The Best Producer=> ", id);
    }

}
