package com.mercadolibre.domain;

import com.mercadolibre.integration.dto.country.CountryCurrency;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;

import java.util.Map;

public interface FinancialService {
    String getCountryCurrency(Map<String, CountryCurrency> currencyMap);
    String getUsdConversionRate(ResponseCurrencyInformationDto currencyInformationDto);
}
