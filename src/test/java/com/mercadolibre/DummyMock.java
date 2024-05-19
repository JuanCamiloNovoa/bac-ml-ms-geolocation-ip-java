package com.mercadolibre;

import com.mercadolibre.domain.dto.response.*;
import com.mercadolibre.integration.dto.country.CountryCurrency;
import com.mercadolibre.integration.dto.country.CountryFlag;
import com.mercadolibre.integration.dto.country.ResponseCountryInformationDto;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
import com.mercadolibre.integration.dto.ip.ResponseIpInformationDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

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
                .currencies(Map.of("EUR", CountryCurrency.builder().name("Euro").symbol("E").build()))
                .flags(CountryFlag.builder().svg("https://flagcdn.com/es.svg").build())
                .languages(Map.of("spa", "Spanish"))
                .timezones(List.of("UTC", "UTC+01:00"))
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
