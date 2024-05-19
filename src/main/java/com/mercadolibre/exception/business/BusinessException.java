package com.mercadolibre.exception.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Excepción que representa un error de negocio en la aplicación.
 * Esta excepción debe ser lanzada cuando ocurra un error que
 * no esté relacionado con fallos técnicos, sino con la lógica
 * de negocio.
 */
@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException{
    private static final long SERIAL_VERSION_UID = 1l;

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
