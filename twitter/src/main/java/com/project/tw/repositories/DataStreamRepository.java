package com.project.tw.repositories;

import com.project.tw.domain.StatusModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Component
@Repository
@Transactional
public interface DataStreamRepository extends MongoRepository<StatusModel, String> {

}
