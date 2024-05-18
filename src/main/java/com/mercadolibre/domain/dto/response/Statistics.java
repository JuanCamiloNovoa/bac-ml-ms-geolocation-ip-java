package com.mercadolibre.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Statistics(
        StatusResponse statusResponse,
        long distanciaMaxima,
        long distanciaMinima,
        String distanciaPromedio,
        String paisDistanciaMaxima,
        String paisDistanciaMinima
) {}
