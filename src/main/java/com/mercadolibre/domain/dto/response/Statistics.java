package com.mercadolibre.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Statistics(
        StatusResponse statusResponse,
        long maxDistance,
        long minDistance,
        String averageDistance,
        String maxDistanceCountry,
        String minDistanceCountry
) {}
