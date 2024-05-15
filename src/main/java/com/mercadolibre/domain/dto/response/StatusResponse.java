package com.mercadolibre.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse {
    @Schema(description = "HTTP response code", example = "200")
    private String statusCode;

    @Schema(description = "Description of response code", example = "Request processed correctly")
    private String statusDescription;
}
