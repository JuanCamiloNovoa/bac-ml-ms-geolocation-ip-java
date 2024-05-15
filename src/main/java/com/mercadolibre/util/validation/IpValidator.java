package com.mercadolibre.util.validation;

import com.mercadolibre.exception.business.BusinessException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.http.HttpStatus;

import static com.mercadolibre.util.constants.Constants.BAD_REQUEST_CODE;
import static com.mercadolibre.util.constants.Constants.ERROR_INVALID_IP;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IpValidator {
    private static final InetAddressValidator validator = InetAddressValidator.getInstance();

    public static void validateIp(String ip) {
        if (!validator.isValid(ip)) {
            throw new BusinessException(BAD_REQUEST_CODE, HttpStatus.BAD_REQUEST,ERROR_INVALID_IP);
        }
    }
}
