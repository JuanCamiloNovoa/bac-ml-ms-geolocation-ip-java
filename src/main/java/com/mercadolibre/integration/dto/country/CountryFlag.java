package com.mercadolibre.integration.dto.country;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * Clase que representa la bandera de un país.
 * Esta clase se utiliza para almacenar la URL del archivo SVG de la bandera de un país específico.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryFlag implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "URL del archivo SVG de la bandera",example = "https://flagcdn.com/es.svg")
    private String svg;
}
