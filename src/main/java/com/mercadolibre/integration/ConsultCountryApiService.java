package com.mercadolibre.integration;

import com.mercadolibre.integration.dto.country.ResponseCountryInformationDto;
import reactor.core.publisher.Mono;

/**
 * Servicio para consultar información de un país basado en su nombre.
 */
public interface ConsultCountryApiService {

    /**
     * Obtiene información detallada de un país dado su nombre.
     *
     * @param countryName el nombre del país
     * @return información del país
     */
    Mono<ResponseCountryInformationDto> getCountryInformation(String countryName);
}
