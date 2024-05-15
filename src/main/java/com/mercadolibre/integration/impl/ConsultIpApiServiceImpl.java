package com.mercadolibre.integration.impl;

import com.mercadolibre.config.ConfigVariable;
import com.mercadolibre.exception.server.ApiException;
import com.mercadolibre.integration.ConsultIpApiService;
import com.mercadolibre.integration.dto.ip.ResponseIpInformationDto;
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
public class ConsultIpApiServiceImpl implements ConsultIpApiService {

    private final WebClient webClient;
    private final ConfigVariable configVariable;

    @Override
    @Cacheable(value = "ipInfoCache", key = "#ip")
    public Mono<ResponseIpInformationDto> getIpInformation(String ip) {
        return webClient
                .get()
                .uri(configVariable.getConsultIpHost().concat(configVariable.getConsultIpUrl()).concat(ip))
                .retrieve()
                .onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals,
                        clientResponse -> Mono.error(new ApiException(INTERNAL_SERVER_ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR,
                                ERROR_API_CONSULT_IP)))
                .bodyToMono(ResponseIpInformationDto.class)
                .timeout(Duration.ofMillis(configVariable.getTimeoutConfig()))
                .onErrorResume(Exception.class, exception -> Mono.error(new ApiException(INTERNAL_SERVER_ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR,
                        String.format(ERROR_GETTING_INFORMATION, NAME_CONSULT_IP_API, exception.getClass().getSimpleName()))));
    }
}
