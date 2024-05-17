package com.mercadolibre.domain;

import com.mercadolibre.domain.dto.response.Statistics;
import reactor.core.publisher.Mono;

public interface StatisticsService {
    Mono<Void> updateStatistics(String country, long distance);
    Mono<Statistics> getStatistics();
}
