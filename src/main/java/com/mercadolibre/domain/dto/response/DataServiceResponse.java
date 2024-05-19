package com.mercadolibre.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
/**
 * Esta clase se utilza para mostrar al usuario la respuesta general de consulta del servicio
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataServiceResponse {
    /**
     * {@link StatusResponse}.
     */
    @Schema(description = "Respuesta general del servicio", example = "{\"statusCode\": \"200\", \"statusDescription\":\"Solicitud procesada correctamente\"}")
    private StatusResponse status;

    /**
     * {@link GeoLocationDataResponse}.
     */
    @Schema(description = "Datos de geolocalización de la respuesta")
    private GeoLocationDataResponse data;
}
