package com.mercadolibre.domain;

import com.mercadolibre.domain.dto.response.DataServiceResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * Servicio de geolocalización por IP.
 * Proporciona información sobre el país, moneda, hora y distancia estimada con Buenos Aires para una IP.
 */
public interface GeoLocationIpService {
    /**
     * Obtiene la información de geolocalización para una IP dada.
     *
     * @param ip dirección IP a consultar.
     * @return información de geolocalización.
     */
    Mono<ResponseEntity<DataServiceResponse>> getInformation(String ip);
}
