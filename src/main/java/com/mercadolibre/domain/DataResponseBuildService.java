package com.mercadolibre.domain;

import com.mercadolibre.domain.dto.response.GeoLocationDataResponse;
import com.mercadolibre.domain.dto.response.TimeServiceResponse;
import com.mercadolibre.integration.dto.country.ResponseCountryInformationDto;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
import com.mercadolibre.integration.dto.ip.ResponseIpInformationDto;

import java.util.List;

public interface DataResponseBuildService {
    GeoLocationDataResponse buildGeoLocationResponse(String ip,
                                                     ResponseIpInformationDto ipInfo,
                                                     ResponseCountryInformationDto countryInfo,
                                                     ResponseCurrencyInformationDto currencyInfo,
                                                     List<TimeServiceResponse> times,
                                                     long distance);
}
