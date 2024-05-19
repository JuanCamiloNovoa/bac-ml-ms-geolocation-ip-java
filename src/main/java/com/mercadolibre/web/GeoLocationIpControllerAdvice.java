package com.mercadolibre.web;

import com.mercadolibre.domain.dto.response.DataServiceResponse;
import com.mercadolibre.exception.business.BusinessException;
import com.mercadolibre.exception.server.ApiException;
import com.mercadolibre.util.mapper.DataResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import static com.mercadolibre.util.constants.Constants.ERROR_RESPONSE;

/**
 * Controlador para manejar excepciones en toda la aplicación.
 * Proporciona métodos para manejar diferentes tipos de excepciones y devolver respuestas adecuadas.
 */
@ControllerAdvice
public class GeoLocationIpControllerAdvice {

    /**
     * Maneja excepciones de tipo BusinessException.
     *
     * @param businessException la excepción lanzada
     * @return una respuesta con el código de estado y mensaje de error apropiado
     */
    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<DataServiceResponse>> handleBusinessException(BusinessException businessException) {
        HttpStatus status = determineHttpStatus(businessException.getHttpStatus(), businessException.getCode());
        DataServiceResponse response = DataResponseMapper.buildDataResponseErrorObject(
                businessException.getCode(), businessException.getMessage());
        return Mono.just(new ResponseEntity<>(response, status));
    }

    /**
     * Maneja excepciones de tipo ApiException.
     *
     * @param apiException la excepción lanzada
     * @return una respuesta con el código de estado y mensaje de error apropiado
     */
    @ExceptionHandler(ApiException.class)
    public Mono<ResponseEntity<DataServiceResponse>> handleApiException(ApiException apiException) {
        HttpStatus status = determineHttpStatus(null, apiException.getCode());
        DataServiceResponse response = DataResponseMapper.buildDataResponseErrorObject(
                apiException.getCode(), apiException.getMessage());
        return Mono.just(new ResponseEntity<>(response, status));
    }

    /**
     * Maneja excepciones genéricas.
     *
     * @param exception la excepción lanzada
     * @return una respuesta con el código de estado y mensaje de error interno del servidor
     */
    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<DataServiceResponse>> handleGeneralException(Exception exception) {
        DataServiceResponse response = DataResponseMapper.buildDataResponseErrorObject(
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ERROR_RESPONSE);
        return Mono.just(new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * Determina el código de estado HTTP adecuado a partir del código proporcionado o un código predeterminado.
     *
     * @param providedStatus el código de estado proporcionado
     * @param statusCode el código de estado en formato String
     * @return el código de estado HTTP adecuado
     */
    private HttpStatus determineHttpStatus(HttpStatus providedStatus, String statusCode) {
        if (providedStatus != null) {
            return providedStatus;
        }
        try {
            int code = Integer.parseInt(statusCode);
            HttpStatus status = HttpStatus.resolve(code);
            if (status != null) {
                return status;
            }
        } catch (NumberFormatException ignored) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
