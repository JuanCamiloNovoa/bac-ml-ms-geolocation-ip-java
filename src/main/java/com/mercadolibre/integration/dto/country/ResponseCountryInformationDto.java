package com.mercadolibre.integration.dto.country;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCountryInformationDto implements Serializable {
    private Map<String, CountryCurrency> currencies;
    private Map<String, String> languages;
    private List<Double> latlng;
    private List<String> timezones;
    private CountryFlag flags;
}
