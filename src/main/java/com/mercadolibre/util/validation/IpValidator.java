package com.mercadolibre.util.validation;

import com.mercadolibre.exception.business.BusinessException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

import static com.mercadolibre.util.constants.Constants.BAD_REQUEST_CODE;
import static com.mercadolibre.util.constants.Constants.ERROR_INVALID_IP;

/**
 * Clase utilitaria para la validación de direcciones IP.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IpValidator {

    /**
     * Valida una dirección IP.
     *
     * @param ip la dirección IP a validar
     * @throws BusinessException si la dirección IP es inválida
     */
    public static void validateIp(String ip) {
        try {
            InetAddress address = InetAddress.getByName(ip);
            if (Objects.isNull(address) || Objects.isNull(address.getHostAddress()) || !address.getHostAddress().equals(ip)) {
                throw new BusinessException(BAD_REQUEST_CODE, HttpStatus.BAD_REQUEST, ERROR_INVALID_IP);
            }
        } catch (UnknownHostException e) {
            throw new BusinessException(BAD_REQUEST_CODE, HttpStatus.BAD_REQUEST, ERROR_INVALID_IP);
        }
    }
}
