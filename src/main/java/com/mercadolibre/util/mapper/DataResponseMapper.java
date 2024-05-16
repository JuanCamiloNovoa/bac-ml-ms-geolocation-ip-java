package com.mercadolibre.util.mapper;

import com.mercadolibre.domain.dto.response.DataServiceResponse;
import com.mercadolibre.domain.dto.response.StatusResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.mercadolibre.util.constants.Constants.SUCCESS_CODE;
import static com.mercadolibre.util.constants.Constants.SUCCESS_RESPONSE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataResponseMapper {
    public static StatusResponse buildSuccessfulStatusResponse() {
        return StatusResponse.builder()
                        .statusCode(SUCCESS_CODE)
                        .statusDescription(SUCCESS_RESPONSE)
                        .build();

    }


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
