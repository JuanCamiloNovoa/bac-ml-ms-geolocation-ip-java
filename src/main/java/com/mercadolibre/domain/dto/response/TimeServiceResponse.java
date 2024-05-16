package com.mercadolibre.domain.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeServiceResponse {
    private String valor;
    private String zonaHoraria;
}
