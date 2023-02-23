package ua.d1nque.mongodb.repository;

import ua.d1nque.mongodb.model.PepEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PepEntityRepository extends MongoRepository<PepEntity, String>, PepEntityRepositoryCustom {

}


