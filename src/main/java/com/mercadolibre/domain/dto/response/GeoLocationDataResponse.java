package com.mercadolibre.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * Esta clase se utiliza para representar la respuesta de datos de geolocalización.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeoLocationDataResponse {


    @Schema(description = "Dirección ip del request", example = "1.1.1.1")
    private String ip;

    @Schema(description = "Fecha actual", example = "18/05/2024 19:14:14")
    private String fechaActual;

    @Schema(description = "País correspondiente a la IP", example = "España")
    private String pais;

    @Schema(description = "Código ISO del país", example = "ESP")
    private String isoCode;

    @Schema(description = "Idiomas oficiales del país", example = "\"zh\": \"Chino\"")
    private Map<String, String> idiomas;

    /**
     * {@link CurrencyServiceResponse}.
     */
    @Schema(description = "Información de la moneda del país", example = "{\"codigo\": \"TWD\",\"cotizacionDolar\": \"0.0311\"}")
    private CurrencyServiceResponse moneda;

    /**
     * {@link TimeServiceResponse}.
     */
    @Schema(description = "Lista de zonas horarias y su respectivo valor en el país", example = "[{\"valor\": \"08:14:14\",\"zonaHoraria\": \"UTC+08:00\"}]")
    private List<TimeServiceResponse> hora;

    @Schema(description = "Distancia estimada entre Buenos Aires y el país correspondiente a la IP", example = "18750 kms (-34.0,-64.0) a (23.5,121.0)")
    private String distanciaEstimada;

    @Schema(description = "URL de la bandera del país que usa el frontend", example = "https://flagcdn.com/tw.svg")
    private String bandera;
}
