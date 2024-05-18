package com.mercadolibre.web;

import com.mercadolibre.domain.dto.response.DataServiceResponse;
import com.mercadolibre.exception.business.BusinessException;
import com.mercadolibre.exception.server.ApiException;
import org.bson.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class GeoLocationIpControllerAdviceTest {

    @InjectMocks
    private GeoLocationIpControllerAdvice controllerAdvice;

    @Test
    void handleBusinessException() {
        Mono<ResponseEntity<DataServiceResponse>> handledException = controllerAdvice.handleBusinessException(new BusinessException("400",
                HttpStatus.BAD_REQUEST, "IP inválida"));
        Assertions.assertNotNull(handledException);
    }

    @ParameterizedTest
    @ValueSource(strings = {"500", "asd", "299"})
    void handleApiException(String arg) {
        Mono<ResponseEntity<DataServiceResponse>> handledException = controllerAdvice.handleApiException(new ApiException(arg,
                HttpStatus.INTERNAL_SERVER_ERROR, "Error interno"));
        Assertions.assertNotNull(handledException);
    }

    @Test
    void handleException() {
        Mono<ResponseEntity<DataServiceResponse>> handledException = controllerAdvice.handleGeneralException(new Exception("Excepcion general"));
        Assertions.assertNotNull(handledException);
    }


}
