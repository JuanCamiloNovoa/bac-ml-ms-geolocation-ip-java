package com.mercadolibre.integration.dto.country;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * Clase que representa la moneda de un país.
 * Esta clase se utiliza para almacenar el nombre y el símbolo
 * de la moneda de un país específico.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryCurrency implements Serializable {
    @Schema(description = "Codigo de la moneda", example = "EUR")
    private String code;

    @Schema(description = "Nombre de la moneda", example = "Euro")
    private String name;

    @Schema(description = "Símbolo de la moenda", example = "€")
    private String symbol;
}
