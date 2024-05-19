package com.mercadolibre.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Esta clase se utiliza para transferir datos relacionados con la moneda y su cotización en dólares.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyServiceResponse {

    @Schema(description = "Código de la moneda", example = "EUR")
    private String codigo;

    @Schema(description = "Cotización moneda", example = "1.08")
    private String cotizacionDolar;
}