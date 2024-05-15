package com.mercadolibre.exception.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException{
    private static final long SERIAL_VERSION_UID = 1l;

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
