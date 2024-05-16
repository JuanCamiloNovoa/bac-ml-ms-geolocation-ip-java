package com.mercadolibre.integration.dto.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCurrencyInformationDto implements Serializable {
    @JsonProperty("conversion_rates")
    private Map<String, Double> conversionRates;

}
