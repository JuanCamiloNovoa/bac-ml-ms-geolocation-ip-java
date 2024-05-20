package com.mercadolibre.integration.dto.country;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LanguageInformation implements Serializable {
    @JsonProperty(value = "iso639_1")
    @Schema(description = "codigo del idioma",example = "es")
    private String iso;

    @Schema(description = "Nombre del idioma",example = "Spanish")
    private String name;
}
