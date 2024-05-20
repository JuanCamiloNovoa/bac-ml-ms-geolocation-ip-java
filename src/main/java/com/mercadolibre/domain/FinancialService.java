package com.mercadolibre.domain;

import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;

/**
 * Servicio financiero que proporciona información sobre la moneda y las tasas de conversión.
 */
public interface FinancialService {


    /**
     * Obtiene la tasa de conversión a USD de la información de la moneda.
     *
     * @param currencyInformationDto DTO que contiene la información de la moneda.
     * @return tasa de conversión a USD formateada a solo 4 decimales.
     */
    String getUsdConversionRate(ResponseCurrencyInformationDto currencyInformationDto);
}
