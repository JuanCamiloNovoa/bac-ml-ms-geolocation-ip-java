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
    @Schema(description = "General service response", example = "{\"statusCode\": \"200\", \"statusDescription\":\"Request processed correctly\"}")
    private StatusResponse status;
    private GeoLocationDataResponse data;
}
