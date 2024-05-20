package com.mercadolibre;

import com.mercadolibre.domain.dto.response.*;
import com.mercadolibre.integration.dto.country.CountryCurrency;
import com.mercadolibre.integration.dto.country.LanguageInformation;
import com.mercadolibre.integration.dto.country.ResponseCountryInformationDto;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
import com.mercadolibre.integration.dto.ip.ResponseIpInformationDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DummyMock {
    public static ResponseCurrencyInformationDto buildGetCurrencyInformation() {
        return ResponseCurrencyInformationDto.builder()
                .conversionRates(Map.of("USD", 1.0868))
                .build();
    }

    public static ResponseCountryInformationDto buildGetCountryInformation() {

        return ResponseCountryInformationDto.builder()
                .latlng(List.of(40D, 30D))
                .currencies(List.of(CountryCurrency.builder().name("peso").code("COP").symbol("$").build()))
                .languages(List.of(LanguageInformation.builder().name("Spanish").iso("es").build()))
                .timezones(List.of("UTC", "UTC+01:00"))
                .alpha2Code("co")
                .build();
    }

    public static ResponseIpInformationDto buildGetIpInformation() {
        return ResponseIpInformationDto.builder()
                .country("Spain")
                .countryCode("ES")
                .build();
    }

    public static Statistics buildStatisticsOk() {
        return Statistics.builder()
                .statusResponse(buildSuccessfulStatus())
                .distanciaPromedio("123")
                .distanciaMinima(1L)
                .distanciaMaxima(2L)
                .paisDistanciaMinima("TestMaxima")
                .paisDistanciaMinima("TestMinima")
                .build();
    }

    public static GeoLocationDataResponse buildGeoLocationDataResponse() {
        return GeoLocationDataResponse.builder()
                .ip("1.1.1.1")
                .bandera("test")
                .distanciaEstimada("123")
                .fechaActual("test")
                .hora(Collections.singletonList(TimeServiceResponse.builder()
                        .valor("123")
                        .zonaHoraria("123")
                        .build()))
                .idiomas(Map.of("es", "español"))
                .isoCode("123")
                .moneda(CurrencyServiceResponse.builder()
                        .codigo("123")
                        .cotizacionDolar("123")
                        .build())
                .pais("123")
                .build();
    }

    public static ResponseEntity<DataServiceResponse> buildDataServiceResponse() {
        return ResponseEntity.ok(DataServiceResponse.builder()
                .status(buildSuccessfulStatus())
                .data(buildGeoLocationDataResponse())
                .build());
    }

    private static StatusResponse buildSuccessfulStatus() {
        return StatusResponse.builder()
                .statusCode("200")
                .statusDescription("test")
                .build();
    }
}
