package com.mercadolibre.integration;

import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
import reactor.core.publisher.Mono;

public interface ConsultCurrencyApiService {
    Mono<ResponseCurrencyInformationDto> getCurrencyInformation(String currencyCode);
}
