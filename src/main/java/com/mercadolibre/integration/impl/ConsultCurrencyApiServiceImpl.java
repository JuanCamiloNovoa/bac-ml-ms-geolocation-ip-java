package com.mercadolibre.integration.impl;

import com.mercadolibre.config.ConfigVariable;
import com.mercadolibre.exception.server.ApiException;
import com.mercadolibre.integration.ConsultCurrencyApiService;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static com.mercadolibre.util.constants.Constants.*;

@Service
@RequiredArgsConstructor
public class ConsultCurrencyApiServiceImpl implements ConsultCurrencyApiService {

    private final WebClient webClient;
    private final ConfigVariable configVariable;

    @Override
    @Cacheable(value = "currencyInfoCache", key = "#currencyCode")
    public Mono<ResponseCurrencyInformationDto> getCurrencyInformation(String currencyCode) {
        return webClient
                .get()
                .uri(configVariable.getConsultCurrencyHost().concat(configVariable.getConsultCurrencyUrl()).concat(currencyCode))
                .retrieve()
                .onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals,
                        clientResponse -> Mono.error(new ApiException(INTERNAL_SERVER_ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR,
                                    ERROR_API_CONSULT_CURRENCY)))
                .bodyToMono(ResponseCurrencyInformationDto.class)
                .timeout(Duration.ofMillis(configVariable.getTimeoutConfig()))
                .onErrorResume(Exception.class, exception -> Mono.error(new ApiException(INTERNAL_SERVER_ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR,
                        String.format(ERROR_GETTING_INFORMATION, NAME_CONSULT_CURRENCY_API, exception.getClass().getSimpleName()))));
    }
}
