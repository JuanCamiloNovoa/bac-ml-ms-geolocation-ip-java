package com.mercadolibre.domain;

import com.mercadolibre.domain.dto.response.Statistics;
import reactor.core.publisher.Mono;

/**
 * Servicio que gestiona las estadísticas de consumo del servicio.
 */
public interface StatisticsService {
    /**
     * Actualiza/crea las estadísticas para un país dado.
     *
     * @param country  nombre del país.
     * @param distance distancia desde Buenos Aires al pais.
     * @return no retorna información.
     */
    Mono<Void> updateStatistics(String country, long distance);

    /**
     * Obtiene las estadísticas generales de consumo.
     *
     * @return estadísticas de consumo.
     */
    Mono<Statistics> getStatistics();
}
