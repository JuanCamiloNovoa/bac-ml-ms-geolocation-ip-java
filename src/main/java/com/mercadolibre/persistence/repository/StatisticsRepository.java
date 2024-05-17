package com.mercadolibre.persistence.repository;

import com.mercadolibre.persistence.entity.StatisticsEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StatisticsRepository extends ReactiveMongoRepository<StatisticsEntity, String> {

    Mono<StatisticsEntity> findByCountry(String country);

    @Aggregation("{ $group: { _id: null, totalDistance: { $sum: { $multiply: ['$distance', '$invocation'] } } } }")
    Mono<Long> getTotalDistance();

    @Aggregation("{ $group: { _id: null, totalInvocations: { $sum: '$invocation' } } }")
    Mono<Long> getTotalInvocations();

    @Aggregation(pipeline = {
            "{ $sort: { distance: -1 } }",
            "{ $limit: 1 }"
    })
    Mono<StatisticsEntity> getMaxDistanceEntity();

    @Aggregation(pipeline = {
            "{ $sort: { distance: 1 } }",
            "{ $limit: 1 }"
    })
    Mono<StatisticsEntity> getMinDistanceEntity();
}