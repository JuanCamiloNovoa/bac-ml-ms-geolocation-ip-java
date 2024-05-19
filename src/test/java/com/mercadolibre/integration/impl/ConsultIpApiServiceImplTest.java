package com.mercadolibre.integration.impl;

import com.mercadolibre.config.ConfigVariable;
import com.mercadolibre.exception.server.ApiException;
import com.mercadolibre.integration.dto.ip.ResponseIpInformationDto;
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
class ConsultIpApiServiceImplTest {
    @Mock
    WebClient webClient;
    @Mock
    ConfigVariable configVariable;
    @InjectMocks
    ConsultIpApiServiceImpl consultIpApiServiceImpl;

    @BeforeEach
    void setUp() {
        webClient = WebClient.create("");
        configVariable = new ConfigVariable();
        ReflectionTestUtils.setField(configVariable, "consultIpHost", "https://api-test.com");
        ReflectionTestUtils.setField(configVariable, "consultIpUrl", "/prueba");
        ReflectionTestUtils.setField(configVariable, "timeoutConfig", 50000L);
        consultIpApiServiceImpl = new ConsultIpApiServiceImpl(webClient, configVariable);
    }

    @Test
    void getCurrencyInformationTest() {
        Mono<ResponseIpInformationDto> result = consultIpApiServiceImpl.getIpInformation("ip");
        Assertions.assertNotNull(result);
    }

    @Test
    void getCurrencyInformationFailedTest() {
        ReflectionTestUtils.setField(configVariable, "consultIpHost", "invalidHost");
        Mono<ResponseIpInformationDto> result = consultIpApiServiceImpl.getIpInformation("ip");
        Assertions.assertThrows(ApiException.class, result::block);
    }
}