package com.mercadolibre.integration.impl;

import com.mercadolibre.config.ConfigVariable;
import com.mercadolibre.exception.server.ApiException;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
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
class ConsultCurrencyApiServiceImplTest {
    @Mock
    WebClient webClient;
    @Mock
    ConfigVariable configVariable;
    @InjectMocks
    ConsultCurrencyApiServiceImpl consultCountryApiServiceImpl;

    @BeforeEach
    void setUp() {
        webClient = WebClient.create("");
        configVariable = new ConfigVariable();
        ReflectionTestUtils.setField(configVariable, "consultCurrencyHost", "https://api-test.com");
        ReflectionTestUtils.setField(configVariable, "consultCurrencyUrl", "/prueba");
        ReflectionTestUtils.setField(configVariable, "timeoutConfig", 50000L);
        consultCountryApiServiceImpl = new ConsultCurrencyApiServiceImpl(webClient, configVariable);
    }

    @Test
    void getCurrencyInformationTest() {
        Mono<ResponseCurrencyInformationDto> result = consultCountryApiServiceImpl.getCurrencyInformation("currencyCode");
        Assertions.assertNotNull(result);
    }

    @Test
    void getCurrencyInformationFailedTest() {
        ReflectionTestUtils.setField(configVariable, "consultCurrencyHost", "invalidHost");
        Mono<ResponseCurrencyInformationDto> result = consultCountryApiServiceImpl.getCurrencyInformation("currencyCode");
        Assertions.assertThrows(ApiException.class, result::block);
    }
}