package com.mercadolibre.integration;

import com.mercadolibre.integration.dto.country.ResponseCountryInformationDto;
import reactor.core.publisher.Mono;

public interface ConsultCountryApiService {
    Mono<ResponseCountryInformationDto> getCountryInformation(String countryName);
}
