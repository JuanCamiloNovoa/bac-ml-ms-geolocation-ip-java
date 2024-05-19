package com.mercadolibre.persistence.repository;

import com.mercadolibre.persistence.entity.StatisticsEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Repository para gestionar las operaciones de la entidad {@link StatisticsEntity} en MongoDB.
 */
@Repository
public interface StatisticsRepository extends ReactiveMongoRepository<StatisticsEntity, String> {

    /**
     * Consulta si existe una entidad de estadísticas por el nombre del país.
     *
     * @param country el nombre del país
     * @return un {@link Mono} de {@link StatisticsEntity} con la entidad de estadísticas encontrada, o vacío si no se encuentra
     */
    Mono<StatisticsEntity> findByCountry(String country);

    /**
     * Calcula la distancia total promedio multiplicando la columna distancia e invocacion sumando este resultado para todas las filas.
     *
     * @return un {@link Mono} de {@link Long} que emite la distancia total promedio
     */
    @Aggregation("{ $group: { _id: null, totalDistance: { $sum: { $multiply: ['$distance', '$invocation'] } } } }")
    Mono<Long> getTotalDistance();

    /**
     * Calcula el número total de invocaciones.
     *
     * @return un {@link Mono} de {@link Long} que emite el número total de invocaciones
     */
    @Aggregation("{ $group: { _id: null, totalInvocations: { $sum: '$invocation' } } }")
    Mono<Long> getTotalInvocations();

    /**
     * Encuentra la entidad de estadísticas con la mayor distancia.
     *
     * @return un {@link Mono} de {@link StatisticsEntity} que emite la entidad de estadísticas con la mayor distancia
     */
    @Aggregation(pipeline = {
            "{ $sort: { distance: -1 } }",
            "{ $limit: 1 }"
    })
    Mono<StatisticsEntity> getMaxDistanceEntity();

    /**
     * Encuentra la entidad de estadísticas con la menor distancia.
     *
     * @return un {@link Mono} de {@link StatisticsEntity} que emite la entidad de estadísticas con la menor distancia
     */
    @Aggregation(pipeline = {
            "{ $sort: { distance: 1 } }",
            "{ $limit: 1 }"
    })
    Mono<StatisticsEntity> getMinDistanceEntity();
}
