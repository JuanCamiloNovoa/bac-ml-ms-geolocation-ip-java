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

/**
 * Implementacion de servicio que gestiona las estadísticas de consumo del servicio.
 */
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Void> updateStatistics(String country, long distance) {
        return statisticsRepository.findByCountry(country)
                .flatMap(this::updateExistingStatistics)
                .switchIfEmpty(Mono.defer(() -> createNewStatistics(country, distance)))
                .then();
    }

    /**
     * Actualiza las estadísticas existentes.
     *
     * @param existingEntity entidad de estadísticas existente.
     * @return entidad de estadísticas actualizada.
     */
    private Mono<StatisticsEntity> updateExistingStatistics(StatisticsEntity existingEntity) {
        existingEntity.incrementInvocations();
        return statisticsRepository.save(existingEntity);
    }

    /**
     * Crea la enitdad estadísticas para un país dado.
     *
     * @param country   nombre del país.
     * @param distance distancia desde Buenos Aires al pais.
     * @return nueva entidad de estadísticas.
     */
    private Mono<StatisticsEntity> createNewStatistics(String country, long distance) {
        StatisticsEntity newEntity = StatisticsEntity.builder()
                .country(country)
                .distance(distance)
                .invocation(1)
                .build();
        return statisticsRepository.save(newEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Statistics> getStatistics() {
        return Mono.zip(
                statisticsRepository.getTotalInvocations(),
                statisticsRepository.getTotalDistance(),
                statisticsRepository.getMaxDistanceEntity(),
                statisticsRepository.getMinDistanceEntity()
        ).map(this::mapToStatistics);
    }

    /**
     * Mapea los resultados obtenidos a un objeto Statistics.
     *
     * @param tuple una tupla que contiene las invocaciones totales, la distancia total, y las entidades con la distancia máxima y mínima.
     * @return objeto Statistics con los datos mapeados.
     */
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
                .distanciaMaxima(maxDistance)
                .distanciaMinima(minDistance)
                .distanciaPromedio(NumberUtils.formatDecimal(averageDistance))
                .paisDistanciaMaxima(maxDistanceCountry)
                .paisDistanciaMinima(minDistanceCountry)
                .build();
    }
}