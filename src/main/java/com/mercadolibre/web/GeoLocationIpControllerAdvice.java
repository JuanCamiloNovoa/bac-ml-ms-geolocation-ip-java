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

@ControllerAdvice
public class GeoLocationIpControllerAdvice {


    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<DataServiceResponse>> handleBusinessException(BusinessException businessException) {
        HttpStatus status = determineHttpStatus(businessException.getHttpStatus(), businessException.getCode());
        DataServiceResponse response = DataResponseMapper.buildDataResponseErrorObject(
                businessException.getCode(), businessException.getMessage());
        return Mono.just(new ResponseEntity<>(response, status));
    }

    @ExceptionHandler(ApiException.class)
    public Mono<ResponseEntity<DataServiceResponse>> handleApiException(ApiException apiException) {
        HttpStatus status = determineHttpStatus(null, apiException.getCode());
        DataServiceResponse response = DataResponseMapper.buildDataResponseErrorObject(
                apiException.getCode(), apiException.getMessage());
        return Mono.just(new ResponseEntity<>(response, status));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<DataServiceResponse>> handleGeneralException(Exception exception) {
        DataServiceResponse response = DataResponseMapper.buildDataResponseErrorObject(
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ERROR_RESPONSE);
        return Mono.just(new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR));
    }

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
