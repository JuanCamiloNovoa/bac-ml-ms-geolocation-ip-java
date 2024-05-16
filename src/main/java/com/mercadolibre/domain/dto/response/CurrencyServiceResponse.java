package com.mercadolibre.domain.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyServiceResponse {
    private String codigo;
    private double cotizacionDolar;
}
