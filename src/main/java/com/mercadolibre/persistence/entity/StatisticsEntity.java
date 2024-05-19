package com.mercadolibre.persistence.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entidad que representa las estadísticas de las consultas en la base de datos MongoDB.
 * Esta clase almacena información sobre el país, la distancia y el número de invocaciones.
 */
@Document(collection = "statistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StatisticsEntity {
    @Id
    @Schema(description = "Identificador único de la entidad")
    private String id;

    @Schema(description = "Nombre del país. Debe ser único")
    @Indexed(unique = true)
    private String country;

    @Schema(description = "Distancia en kilómetros desde Buenos Aires al país")
    @Indexed
    private long distance;


    @Schema(description = "Número de invocaciones realizadas")
    @Indexed
    private long invocation;

    /**
     * Incrementa el número de invocaciones en uno.
     */
    public void incrementInvocations() {
        this.invocation++;
    }
}
