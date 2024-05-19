package com.mercadolibre.util.mapper;

import com.mercadolibre.domain.dto.response.DataServiceResponse;
import com.mercadolibre.domain.dto.response.StatusResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.mercadolibre.util.constants.Constants.*;

/**
 * Clase utilitaria para la construcción de objetos de respuesta del servicio.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataResponseMapper {

    /**
     * Construye una respuesta de estado exitosa.
     *
     * @return un objeto {@link StatusResponse} con el código y descripción de éxito.
     */
    public static StatusResponse buildSuccessfulStatusResponse() {
        return StatusResponse.builder()
                .statusCode(SUCCESS_CODE)
                .statusDescription(SUCCESS_RESPONSE)
                .build();
    }

    /**
     * Construye un objeto de respuesta de datos con información de error.
     *
     * @param status      el código de estado del error
     * @param description la descripción del error
     * @return un objeto {@link DataServiceResponse} con el estado del error
     */
    public static DataServiceResponse buildDataResponseErrorObject(String status,
                                                                   String description) {
        return DataServiceResponse.builder()
                .status(StatusResponse.builder()
                        .statusCode(status)
                        .statusDescription(description)
                        .build())
                .build();
    }

    /**
     * Construye una respuesta de estado cuando no se encuentra información.
     *
     * @return un objeto {@link StatusResponse} con el código y descripción de "no encontrado".
     */
    public static StatusResponse buildStatusResponseEmpty() {
        return StatusResponse.builder()
                .statusCode(NOT_FOUND_CODE)
                .statusDescription(NOT_FOUND_DESCRIPTION)
                .build();
    }
}
