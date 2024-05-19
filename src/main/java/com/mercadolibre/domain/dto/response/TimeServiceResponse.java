package com.mercadolibre.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Clase que representa la respuesta de un servicio de tiempo.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeServiceResponse {

    @Schema(description = "Hora actual", example = "08:14:14")
    private String valor;

    @Schema(description = "Zona horaria correspondiente al valor del tiempo", example = "UTC+08:00")
    private String zonaHoraria;
}
