package com.mercadolibre.domain;

import com.mercadolibre.domain.dto.response.DataServiceResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface GeoLocationIpService {
    Mono<ResponseEntity<DataServiceResponse>> getInformation(String ip);
}
