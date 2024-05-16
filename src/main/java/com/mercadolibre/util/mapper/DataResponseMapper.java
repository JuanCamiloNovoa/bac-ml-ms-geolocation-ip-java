package com.mercadolibre.util.mapper;

import com.mercadolibre.domain.dto.response.*;
import com.mercadolibre.integration.dto.country.ResponseCountryInformationDto;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
import com.mercadolibre.integration.dto.ip.ResponseIpInformationDto;
import com.mercadolibre.util.api.ApiUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.mercadolibre.util.constants.Constants.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataResponseMapper {
    public static StatusResponse buildSuccessfulStatusResponse() {
        return StatusResponse.builder()
                        .statusCode(SUCCESS_CODE)
                        .statusDescription(SUCCESS_RESPONSE)
                        .build();

    }

    public static GeoLocationDataResponse buildGeoLocationResponse(String ip,
                                                                   ResponseIpInformationDto ipInfo,
                                                                   ResponseCountryInformationDto countryInfo,
                                                                   ResponseCurrencyInformationDto currencyInfo,
                                                                   List<TimeServiceResponse> times,
                                                                   double distance){
        return GeoLocationDataResponse.builder()
                .ip(ip)
                .fechaActual(ApiUtils.getCurrentTime())
                .pais(ipInfo.getCountry())
                .isoCode(ipInfo.getCountryCode())
                .idiomas(countryInfo.getLanguages())
                .moneda(CurrencyServiceResponse.builder()
                        .codigo(ApiUtils.getCountryCurrency(countryInfo.getCurrencies()))
                        .cotizacionDolar(ApiUtils.getUsdConversionRate(currencyInfo))
                        .build())
                .hora(times)
                .distanciaEstimada(String.format(
                        DISTANCE_INFORMATION,
                        distance,
                        LATITUDE_BUENOS_AIRES,
                        LONGITUDE_BUENOS_AIRES,
                        countryInfo.getLatlng().get(0),
                        countryInfo.getLatlng().get(1)
                ))
                .build();
    }

    public static DataServiceResponse buildDataResponseErrorObject(String status,
                                                                   String description) {
        return DataServiceResponse.builder()
                .status(StatusResponse.builder()
                        .statusCode(status)
                        .statusDescription(description)
                        .build())
                .build();
    }
}
