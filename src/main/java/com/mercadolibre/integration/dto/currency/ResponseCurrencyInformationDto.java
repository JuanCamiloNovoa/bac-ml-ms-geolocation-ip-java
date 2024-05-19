package com.mercadolibre.integration.dto.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * Clase que representa la información de las tasas de conversión de moneda obtenida de una API pública.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCurrencyInformationDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @JsonProperty("conversion_rates")
    @Schema(description = "Tasas de conversión de una moneda a otras",example = "{}")
    private Map<String, Double> conversionRates;
}
