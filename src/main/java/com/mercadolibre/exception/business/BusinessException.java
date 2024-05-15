package com.mercadolibre.exception.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException{
    private static final long SERIAL_VERSION_UID = 1l;

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
