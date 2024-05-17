package com.mercadolibre.domain.service;

import com.mercadolibre.domain.StatisticsService;
import com.mercadolibre.domain.dto.response.Statistics;
import com.mercadolibre.persistence.entity.StatisticsEntity;
import com.mercadolibre.persistence.repository.StatisticsRepository;
import com.mercadolibre.util.api.NumberUtils;
import com.mercadolibre.util.mapper.DataResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple4;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsRepository statisticsRepository;

    @Override
    public Mono<Void> updateStatistics(String country, long distance) {
        return statisticsRepository.findByCountry(country)
                .flatMap(this::updateExistingStatistics)
                .switchIfEmpty(Mono.defer(() -> createNewStatistics(country, distance)))
                .then();
    }

    private Mono<StatisticsEntity> updateExistingStatistics(StatisticsEntity existingEntity) {
        existingEntity.incrementInvocations();
        return statisticsRepository.save(existingEntity);
    }

    private Mono<StatisticsEntity> createNewStatistics(String country, long distance) {
        StatisticsEntity newEntity = StatisticsEntity.builder()
                .country(country)
                .distance(distance)
                .invocation(1)
                .build();
        return statisticsRepository.save(newEntity);
    }

    @Override
    public Mono<Statistics> getStatistics() {
        return Mono.zip(
                statisticsRepository.getTotalInvocations(),
                statisticsRepository.getTotalDistance(),
                statisticsRepository.getMaxDistanceEntity(),
                statisticsRepository.getMinDistanceEntity()
        ).map(this::mapToStatistics);
    }

    private Statistics mapToStatistics(Tuple4<Long, Long, StatisticsEntity, StatisticsEntity> tuple) {
        long totalInvocations = Optional.of(tuple.getT1()).orElse(0L);
        long totalDistance = Optional.of(tuple.getT2()).orElse(0L);

        long maxDistance = Optional.of(tuple.getT3()).map(StatisticsEntity::getDistance).orElse(0L);
        long minDistance = Optional.of(tuple.getT4()).map(StatisticsEntity::getDistance).orElse(0L);

        String maxDistanceCountry = Optional.of(tuple.getT3()).map(StatisticsEntity::getCountry).orElse("N/A");
        String minDistanceCountry = Optional.of(tuple.getT4()).map(StatisticsEntity::getCountry).orElse("N/A");

        double averageDistance = totalInvocations > 0 ? (double) totalDistance / totalInvocations : 0.0;

        return Statistics.builder()
                .statusResponse(DataResponseMapper.buildSuccessfulStatusResponse())
                .maxDistance(maxDistance)
                .minDistance(minDistance)
                .averageDistance(NumberUtils.formatDecimal(averageDistance))
                .maxDistanceCountry(maxDistanceCountry)
                .minDistanceCountry(minDistanceCountry)
                .build();
    }
}