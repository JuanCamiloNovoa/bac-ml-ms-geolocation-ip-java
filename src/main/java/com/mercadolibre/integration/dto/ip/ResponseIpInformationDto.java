package com.mercadolibre.integration.dto.ip;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * Clase que representa la información de IP obtenida de una API pública.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseIpInformationDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Nombre del país asociado a la dirección IP", example = "Spain")
    private String country;

    @Schema(description = "Código del país asociado a la dirección IP", example = "ES")
    private String countryCode;
}
