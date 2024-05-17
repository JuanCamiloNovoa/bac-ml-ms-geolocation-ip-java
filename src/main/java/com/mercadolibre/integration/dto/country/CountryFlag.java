package com.mercadolibre.integration.dto.country;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryFlag implements Serializable {
    private String svg;
}
