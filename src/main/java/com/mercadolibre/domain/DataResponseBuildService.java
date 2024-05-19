package com.mercadolibre.domain;

import com.mercadolibre.domain.dto.response.GeoLocationDataResponse;
import com.mercadolibre.domain.dto.response.TimeServiceResponse;
import com.mercadolibre.integration.dto.country.ResponseCountryInformationDto;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
import com.mercadolibre.integration.dto.ip.ResponseIpInformationDto;

import java.util.List;

/**
 * Servicio que construye las respuestas de geolocalización basadas en la información proporcionada.
 */
public interface DataResponseBuildService {
    /**
     * Construye la respuesta de geolocalización.
     *
     * @param ip           dirección IP consultada.
     * @param ipInfo       información de la IP.
     * @param countryInfo  información del país.
     * @param currencyInfo información de la moneda.
     * @param times        lista de tiempos en las zonas horarias del país.
     * @param distance     distancia calculada de Buenos Aires al país.
     * @return respuesta de geolocalización.
     */
    GeoLocationDataResponse buildGeoLocationResponse(String ip,
                                                     ResponseIpInformationDto ipInfo,
                                                     ResponseCountryInformationDto countryInfo,
                                                     ResponseCurrencyInformationDto currencyInfo,
                                                     List<TimeServiceResponse> times,
                                                     long distance);
}
