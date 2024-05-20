package com.mercadolibre.integration.impl;

import com.mercadolibre.config.ConfigVariable;
import com.mercadolibre.exception.server.ApiException;
import com.mercadolibre.integration.ConsultCountryApiService;
import com.mercadolibre.integration.dto.country.ResponseCountryInformationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static com.mercadolibre.util.constants.Constants.*;

/**
 * Implementación del servicio para consultar información de un país basado en su nombre.
 */
@Service
@RequiredArgsConstructor
public class ConsultCountryApiServiceImpl implements ConsultCountryApiService {

    private final WebClient webClient;
    private final ConfigVariable configVariable;

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable(value = "countryInfoCache", key = "#countryName")
    public Mono<ResponseCountryInformationDto> getCountryInformation(String countryName) {
        return webClient
                .get()
                .uri(configVariable.getConsultCountryHost().concat(configVariable.getConsultCountryUrl())
                        .concat(countryName)
                        .concat("?access_key=").concat(configVariable.getConsultCountryAccessKey()))
                .retrieve()
                .onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals,
                        clientResponse -> Mono.error(new ApiException(INTERNAL_SERVER_ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR,
                                ERROR_API_CONSULT_COUNTRY)))
                .bodyToFlux(ResponseCountryInformationDto.class)
                .collectList()
                .flatMap(list -> {
                    if (list.isEmpty()) {
                        return Mono.error(new ApiException(INTERNAL_SERVER_ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR,
                                String.format(ERROR_GETTING_INFORMATION, NAME_CONSULT_COUNTRY_API, "No country information found")));
                    }
                    return Mono.just(list.getFirst());
                })
                .timeout(Duration.ofMillis(configVariable.getTimeoutConfig()))
                .onErrorResume(Exception.class, exception -> Mono.error(new ApiException(INTERNAL_SERVER_ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR,
                        String.format(ERROR_GETTING_INFORMATION, NAME_CONSULT_COUNTRY_API, exception.getClass().getSimpleName()))));
    }
}
