package com.mercadolibre.domain.service;

import com.mercadolibre.DummyMock;
import com.mercadolibre.domain.DataResponseBuildService;
import com.mercadolibre.domain.FinancialService;
import com.mercadolibre.domain.StatisticsService;
import com.mercadolibre.domain.TranslationService;
import com.mercadolibre.domain.dto.response.DataServiceResponse;
import com.mercadolibre.domain.dto.response.GeoLocationDataResponse;
import com.mercadolibre.integration.ConsultCountryApiService;
import com.mercadolibre.integration.ConsultCurrencyApiService;
import com.mercadolibre.integration.ConsultIpApiService;
import com.mercadolibre.integration.dto.country.ResponseCountryInformationDto;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
import com.mercadolibre.integration.dto.ip.ResponseIpInformationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GeoLocationIpServiceImplTest {

    @Mock
    ConsultIpApiService consultIpApiService;
    @Mock
    ConsultCountryApiService consultCountryApiService;
    @Mock
    ConsultCurrencyApiService consultCurrencyApiService;
    @Mock
    DataResponseBuildService dataResponseBuildService;
    @Mock
    FinancialService financialService;
    @Mock
    StatisticsService statisticsService;
    @Mock
    TranslationService translationService;
    @InjectMocks
    GeoLocationIpServiceImpl geoLocationIpServiceImpl;

    @Test
    void testGetInformation() {
        ResponseIpInformationDto ipInfo = DummyMock.buildGetIpInformation();
        ResponseCountryInformationDto countryInfo = DummyMock.buildGetCountryInformation();
        ResponseCurrencyInformationDto currencyInfo = DummyMock.buildGetCurrencyInformation();
        GeoLocationDataResponse geoLocationResponse = DummyMock.buildGeoLocationDataResponse();


        when(consultIpApiService.getIpInformation(anyString())).thenReturn(Mono.just(ipInfo));
        when(consultCountryApiService.getCountryInformation(anyString())).thenReturn(Mono.just(countryInfo));
        when(consultCurrencyApiService.getCurrencyInformation(anyString())).thenReturn(Mono.just(currencyInfo));
        when(dataResponseBuildService.buildGeoLocationResponse(anyString(), any(), any(), any(), any(), anyLong())).thenReturn(geoLocationResponse);
        when(financialService.getCountryCurrency(any())).thenReturn("EUR");
        when(translationService.translateCountry(anyString())).thenReturn("España");
        when(statisticsService.updateStatistics(anyString(), anyLong())).thenReturn(Mono.empty());

        Mono<ResponseEntity<DataServiceResponse>> result = geoLocationIpServiceImpl.getInformation("83.44.196.93");

        ResponseEntity<DataServiceResponse> responseEntity = result.block();
        Assertions.assertNotNull(responseEntity);
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(geoLocationResponse, responseEntity.getBody().getData());

        verify(consultIpApiService).getIpInformation(anyString());
        verify(consultCountryApiService).getCountryInformation(anyString());
        verify(consultCurrencyApiService).getCurrencyInformation(anyString());
        verify(dataResponseBuildService).buildGeoLocationResponse(anyString(), any(), any(), any(), any(), anyLong());
        verify(financialService).getCountryCurrency(any());
        verify(translationService).translateCountry(anyString());
        verify(statisticsService).updateStatistics(anyString(), anyLong());
    }
}
