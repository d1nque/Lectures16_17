package ua.d1nque.mongodb.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import ua.d1nque.mongodb.repository.PepEntityRepositoryCustom;

import java.util.List;

@Repository
public class PepEntityRepositoryCustomImpl implements PepEntityRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Object> getTopTenPepNames() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("isPep").is(true)),
                Aggregation.group("firstName").count().as("count"),
                Aggregation.sort(Sort.Direction.DESC, "count"),
                Aggregation.limit(10)
        );
        return mongoTemplate.aggregate(
                aggregation, "pepEntity", Object.class
        ).getMappedResults();
    }

    @Override
    public List<Object> findByFullNameEn(String fullNameEn) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("isPep").is(true).and("fullNameEn").regex(fullNameEn)),
                Aggregation.project("fullNameEn", "fullName", "lastJobTitleEn", "lastWorkplaceEn")
        );
        return mongoTemplate.aggregate(
                aggregation, "pepEntity", Object.class
        ).getMappedResults();
    }

}
