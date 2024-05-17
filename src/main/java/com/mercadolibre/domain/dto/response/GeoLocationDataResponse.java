package com.mercadolibre.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Map;

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
    private Map<String,String> idiomas;
    private CurrencyServiceResponse moneda;
    private List<TimeServiceResponse> hora;
    private String distanciaEstimada;
    private String bandera;
}
