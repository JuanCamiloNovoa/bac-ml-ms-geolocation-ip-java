package com.mercadolibre.integration.dto.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.integration.dto.country.CountryCurrency;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCurrencyInformationDto implements Serializable {
    @JsonProperty("conversion_rates")
    private Map<String, String> conversionRates;

}
