package com.mercadolibre.integration;

import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
import reactor.core.publisher.Mono;

/**
 * Servicio para consultar información de una moneda basada en su código.
 */
public interface ConsultCurrencyApiService {

    /**
     * Obtiene información detallada de una moneda dado su código.
     *
     * @param currencyCode el código de la moneda
     * @return información de la moneda
     */
    Mono<ResponseCurrencyInformationDto> getCurrencyInformation(String currencyCode);
}
