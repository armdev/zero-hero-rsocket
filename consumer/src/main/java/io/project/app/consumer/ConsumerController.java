package io.project.app.consumer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ConsumerController {

    @Autowired
    private ConsumerService consumerServiceController;

    @GetMapping("/data")
    public Flux<String> get(@RequestParam String id) {
        return consumerServiceController.get(id);

    }

}
