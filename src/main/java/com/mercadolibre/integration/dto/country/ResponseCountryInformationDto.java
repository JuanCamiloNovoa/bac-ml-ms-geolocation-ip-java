package com.mercadolibre.integration.dto.country;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Clase que representa la información de un país obtenida de una respuesta de API.
 * Esta clase se utiliza para almacenar información detallada sobre un país,
 * incluyendo monedas, idiomas, coordenadas de latitud y longitud, zonas horarias y banderas.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseCountryInformationDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * {@link CountryCurrency}.
     */
    @JsonProperty("currencies")
    @Schema(description = "Mapa de las monedas del país, donde la clave es el código de la moneda y el valor es un objeto",
            example = "\"currencies\": {\"EUR\": {\"name\": \"Euro\",\"symbol\": \"€\"}}")
    private List<CountryCurrency> currencies;


    /**
     * {@link LanguageInformation}.
     */
    @Schema(description = "Mapa de los idiomas del país, donde la clave es el código del idioma y el valor es el nombre del idioma",
            example = "[{\"iso639_1\": \"es\",\"iso639_2\": \"spa\",\"name\": \"Spanish\",\"nativeName\": \"Español\"}]")
    @JsonProperty("languages")
    private List<LanguageInformation> languages;


    @Schema(description = "Lista de coordenadas de latitud y longitud del país",
            example = "\"latlng\": [40.0,-4.0]")
    @JsonProperty("latlng")
    private List<Double> latlng;


    @Schema(description = "Lista de zonas horarias del país",
            example = "\"timezones\": [\"UTC\",\"UTC+01:00\"]")
    @JsonProperty("timezones")
    private List<String> timezones;


    @Schema(description = "Objeto que representa la bandera del país",
            example = "\"flags\": {\"svg\": \"https://flagcdn.com/es.svg\"}")
    @JsonProperty("alpha2Code")
    private String alpha2Code;
}
