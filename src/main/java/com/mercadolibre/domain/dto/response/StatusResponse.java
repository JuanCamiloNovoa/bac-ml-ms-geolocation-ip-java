package com.mercadolibre.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Clase que representa la respuesta de estado HTTP del servicio.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse {

    @Schema(description = "Código de respuesta HTTP", example = "200")
    private String statusCode;

    @Schema(description = "Descripción del código de respuesta", example = "Solicitud procesada correctamente")
    private String statusDescription;
}
