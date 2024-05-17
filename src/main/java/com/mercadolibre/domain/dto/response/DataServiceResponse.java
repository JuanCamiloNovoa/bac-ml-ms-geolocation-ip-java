package com.mercadolibre.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataServiceResponse {
    @Schema(description = "Respues general del servicio", example = "{\"statusCode\": \"200\", \"statusDescription\":\"Solicitud procesada correctamente\"}")
    private StatusResponse status;
    private GeoLocationDataResponse data;
}
