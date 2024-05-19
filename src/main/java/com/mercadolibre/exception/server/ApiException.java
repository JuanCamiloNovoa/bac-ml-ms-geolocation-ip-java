package com.mercadolibre.exception.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Excepción que representa un error relacionado con la API.
 * Esta excepción debe ser lanzada cuando ocurra un error que
 * esté relacionado con fallos en la comunicación con servicios
 * externos o con la propia API.
 */
@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {
    private static final long SERIAL_VERSION_UID = 1L;

    /**
     * Código del error.
     */
    private final String code;

    /**
     * Estado HTTP asociado al error.
     */
    private final HttpStatus httpStatus;

    /**
     * Mensaje detallado del error.
     */
    private final String message;
}
