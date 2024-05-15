package com.mercadolibre.integration.dto.ip;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseIpInformationDto implements Serializable {
    private String country;
    private String countryCode;
}
