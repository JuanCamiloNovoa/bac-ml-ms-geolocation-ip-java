package com.mercadolibre.integration.dto.country;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryCurrency implements Serializable {
    private String name;
    private String symbol;
}
