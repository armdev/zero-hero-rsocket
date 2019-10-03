package com.project.tw.repositories;

import com.project.tw.domain.TweeterUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Component
@Repository
public interface TwitterUserRepository extends ReactiveMongoRepository<TweeterUser, String> {
    
    Flux<TweeterUser> findTop50ByOrderByTimestampDesc();
    
}
