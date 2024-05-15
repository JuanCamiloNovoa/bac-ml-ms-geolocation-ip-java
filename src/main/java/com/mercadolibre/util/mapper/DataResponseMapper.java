package com.mercadolibre.util.mapper;

import com.mercadolibre.domain.dto.response.DataServiceResponse;
import com.mercadolibre.domain.dto.response.StatusResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataResponseMapper {
    public static DataServiceResponse buildDataResponseErrorObject(String status,
                                                                   String description) {
        return DataServiceResponse.builder()
                .status(StatusResponse.builder()
                        .statusCode(status)
                        .statusDescription(description)
                        .build())
                .build();
    }
}
