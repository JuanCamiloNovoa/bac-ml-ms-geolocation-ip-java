package com.mercadolibre.integration.dto.country;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
public class ResponseCountryInformationDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * {@link CountryCurrency}.
     */
    @Schema(description = "Mapa de las monedas del país, donde la clave es el código de la moneda y el valor es un objeto",
            example = "\"currencies\": {\"EUR\": {\"name\": \"Euro\",\"symbol\": \"€\"}}")
    private Map<String, CountryCurrency> currencies;


    @Schema(description = "Mapa de los idiomas del país, donde la clave es el código del idioma y el valor es el nombre del idioma",
            example = "\"languages\": {\"spa\": \"Spanish\",\"cat\": \"Catalan\",\"eus\": \"Basque\",\"glc\": \"Galician\"}")
    private Map<String, String> languages;


    @Schema(description = "Lista de coordenadas de latitud y longitud del país",
            example = "\"latlng\": [40.0,-4.0]")
    private List<Double> latlng;


    @Schema(description = "Lista de zonas horarias del país",
            example = "\"timezones\": [\"UTC\",\"UTC+01:00\"]")
    private List<String> timezones;

    /**
     * {@link CountryFlag}.
     */
    @Schema(description = "Objeto que representa la bandera del país",
            example = "\"flags\": {\"svg\": \"https://flagcdn.com/es.svg\"}")
    private CountryFlag flags;
}
