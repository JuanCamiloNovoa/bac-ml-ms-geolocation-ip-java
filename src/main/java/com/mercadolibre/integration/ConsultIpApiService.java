package com.mercadolibre.integration;

import com.mercadolibre.integration.dto.ip.ResponseIpInformationDto;
import reactor.core.publisher.Mono;

/**
 * Servicio para consultar información de una IP.
 */
public interface ConsultIpApiService {

    /**
     * Obtiene información detallada de una IP.
     *
     * @param ip la dirección IP a consultar
     * @return información de la IP
     */
    Mono<ResponseIpInformationDto> getIpInformation(String ip);
}
