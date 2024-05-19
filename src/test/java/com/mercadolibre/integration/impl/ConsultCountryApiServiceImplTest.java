package com.mercadolibre.integration.impl;

import com.mercadolibre.config.ConfigVariable;
import com.mercadolibre.exception.server.ApiException;
import com.mercadolibre.integration.dto.country.ResponseCountryInformationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class ConsultCountryApiServiceImplTest {
    @Mock
    WebClient webClient;
    @Mock
    ConfigVariable configVariable;
    @InjectMocks
    ConsultCountryApiServiceImpl consultCountryApiServiceImpl;

    @BeforeEach
    void setUp() {
        webClient = WebClient.create("");
        configVariable = new ConfigVariable();
        ReflectionTestUtils.setField(configVariable, "consultCountryHost", "https://api-test.com");
        ReflectionTestUtils.setField(configVariable, "consultCountryUrl", "/prueba");
        ReflectionTestUtils.setField(configVariable, "timeoutConfig", 50000L);
        consultCountryApiServiceImpl = new ConsultCountryApiServiceImpl(webClient, configVariable);
    }

    @Test
    void getCurrencyInformationTest() {
        Mono<ResponseCountryInformationDto> result = consultCountryApiServiceImpl.getCountryInformation("countryName");
        Assertions.assertNotNull(result);
    }

    @Test
    void getCurrencyInformationFailedTest() {
        ReflectionTestUtils.setField(configVariable, "consultCountryHost", "invalidHost");
        Mono<ResponseCountryInformationDto> result = consultCountryApiServiceImpl.getCountryInformation("countryName");
        Assertions.assertThrows(ApiException.class, result::block);
    }
}