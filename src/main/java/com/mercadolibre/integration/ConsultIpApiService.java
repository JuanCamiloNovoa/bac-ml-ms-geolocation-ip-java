package com.mercadolibre.integration;

import com.mercadolibre.integration.dto.ip.ResponseIpInformationDto;
import reactor.core.publisher.Mono;

public interface ConsultIpApiService {
    Mono<ResponseIpInformationDto> getIpInformation(String ip);
}
