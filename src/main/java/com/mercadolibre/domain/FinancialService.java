package com.mercadolibre.domain;

import com.mercadolibre.integration.dto.country.CountryCurrency;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;

import java.util.Map;

/**
 * Servicio financiero que proporciona información sobre la moneda y las tasas de conversión.
 */
public interface FinancialService {
    /**
     * Obtiene la moneda del país a partir del mapa general que responde el api pública.
     *
     * @param currencyMap mapa de monedas del país.
     * @return clave de la moneda del país.
     */
    String getCountryCurrency(Map<String, CountryCurrency> currencyMap);

    /**
     * Obtiene la tasa de conversión a USD de la información de la moneda.
     *
     * @param currencyInformationDto DTO que contiene la información de la moneda.
     * @return tasa de conversión a USD formateada a solo 4 decimales.
     */
    String getUsdConversionRate(ResponseCurrencyInformationDto currencyInformationDto);
}
