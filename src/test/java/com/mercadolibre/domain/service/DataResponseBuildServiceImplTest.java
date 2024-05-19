package com.mercadolibre.domain.service;

import com.mercadolibre.DummyMock;
import com.mercadolibre.domain.FinancialService;
import com.mercadolibre.domain.TranslationService;
import com.mercadolibre.domain.dto.response.GeoLocationDataResponse;
import com.mercadolibre.domain.dto.response.TimeServiceResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataResponseBuildServiceImplTest {
    @Mock
    FinancialService financialService;
    @Mock
    TranslationService translationService;
    @InjectMocks
    DataResponseBuildServiceImpl dataResponseBuildServiceImpl;


    @Test
    void testBuildGeoLocationResponse() {
        when(financialService.getCountryCurrency(anyMap())).thenReturn("EUR");
        when(financialService.getUsdConversionRate(any())).thenReturn("1.08");
        when(translationService.translateLanguages(anyMap())).thenReturn(Map.of("es", "Español"));
        when(translationService.translateCountry(anyString())).thenReturn("España");

        GeoLocationDataResponse result = dataResponseBuildServiceImpl.buildGeoLocationResponse("ip",
                DummyMock.buildGetIpInformation(),
                DummyMock.buildGetCountryInformation(),
                DummyMock.buildGetCurrencyInformation(),
                List.of(TimeServiceResponse.builder()
                        .valor("123")
                        .zonaHoraria("123").build()),
                1L
        );
        Assertions.assertNotNull(result);
    }
}