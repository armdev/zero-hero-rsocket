package com.project.tw.repositories;

import com.project.tw.domain.StatusModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Component
@Repository
@Transactional
public interface StatusStreamRepository extends ReactiveMongoRepository<StatusModel, String> {
    
    //Flux<StatusModel> findTop50ByOrderByTimestampDesc();
    
}
