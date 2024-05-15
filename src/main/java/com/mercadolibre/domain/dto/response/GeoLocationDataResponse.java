package com.mercadolibre.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeoLocationDataResponse {
    private String ip;
    private String fechaActual;
    private String pais;
    private String isoCode;
    private String idiomas;
    private String moenda;
    private String hora;
    private String distanciaEstimada;
}
