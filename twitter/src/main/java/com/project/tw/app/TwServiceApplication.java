package com.project.tw.app;

import com.project.tw.services.StreamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
@EnableReactiveMongoRepositories("com.project.tw.repositories")
@ComponentScan(basePackages = {"com.project"})
@EntityScan("com.project.tw.domain")
@EnableAsync
@Slf4j
public class TwServiceApplication {

    @Autowired
    @Lazy
    private StreamService streamService;

    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(TwServiceApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.setWebApplicationType(WebApplicationType.REACTIVE);
        application.run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        log.info("Startup");
        streamService.startStream();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
