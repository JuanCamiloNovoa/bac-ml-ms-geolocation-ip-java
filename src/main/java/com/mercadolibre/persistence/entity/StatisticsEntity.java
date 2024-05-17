package com.mercadolibre.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "statistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StatisticsEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private String country;

    @Indexed
    private long distance;

    @Indexed
    private long invocation;

    public void incrementInvocations() {
        this.invocation++;
    }
}
