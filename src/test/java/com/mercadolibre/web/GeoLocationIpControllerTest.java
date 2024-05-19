package com.mercadolibre.web;

import com.mercadolibre.DummyMock;
import com.mercadolibre.domain.GeoLocationIpService;
import com.mercadolibre.domain.StatisticsService;
import com.mercadolibre.domain.dto.response.DataServiceResponse;
import com.mercadolibre.domain.dto.response.Statistics;
import com.mercadolibre.exception.business.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class GeoLocationIpControllerTest {

    @InjectMocks
    private GeoLocationIpController geoLocationIpController;

    @Mock
    private GeoLocationIpService geoLocationIpService;

    @Mock
    private StatisticsService statisticsService;

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(geoLocationIpService.getInformation(any())).thenReturn(Mono.just(DummyMock.buildDataServiceResponse()));
    }

    @Test
    void getGeoLocalizationInfoTestBadIp() {
        Assertions.assertThrows(BusinessException.class, () -> geoLocationIpController.getGeoLocalizationInfo("asd"));
    }

    @Test
    void getGeoLocalizationInfoTest() {
        Mono<ResponseEntity<DataServiceResponse>> response = geoLocationIpController.getGeoLocalizationInfo("1.1.1.1");
        Assertions.assertNotNull(response);
    }

    @Test
    void getStatisticsTest() {
        Mockito.lenient().when(statisticsService.getStatistics()).thenReturn(Mono.just(DummyMock.buildStatisticsOk()));
        Mono<ResponseEntity<Statistics>> response = geoLocationIpController.getStatistics();
        Assertions.assertNotNull(response);
    }


}
