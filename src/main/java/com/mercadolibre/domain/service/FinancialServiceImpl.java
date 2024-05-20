package com.mercadolibre.domain.service;

import com.mercadolibre.domain.FinancialService;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
import com.mercadolibre.util.api.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.mercadolibre.util.constants.Constants.CURRENCY_CONVERSION_RATE;
import static com.mercadolibre.util.constants.Constants.EMPTY_CURRENCY_INFORMATION;

/**
 * Implementación del servicio financiero que proporciona información sobre la moneda y las tasas de conversión.
 */
@Service
public class FinancialServiceImpl implements FinancialService {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsdConversionRate(ResponseCurrencyInformationDto currencyInformationDto) {
        if (Objects.isNull(currencyInformationDto) || Objects.isNull(currencyInformationDto.getConversionRates()) || currencyInformationDto.getConversionRates().isEmpty()) {
            return EMPTY_CURRENCY_INFORMATION;
        }
        return NumberUtils.formatDecimal(currencyInformationDto.getConversionRates().get(CURRENCY_CONVERSION_RATE));
    }
}
