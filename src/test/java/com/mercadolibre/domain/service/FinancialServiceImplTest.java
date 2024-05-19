package com.mercadolibre.domain.service;

import com.mercadolibre.integration.dto.country.CountryCurrency;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class FinancialServiceImplTest {
    @InjectMocks
    private FinancialServiceImpl financialServiceImpl;

    @Test
    void testGetCountryCurrency() {
        String result = financialServiceImpl.getCountryCurrency(Map.of("EUR", CountryCurrency.builder().symbol("E").name("Euro").build()));
        Assertions.assertEquals("EUR", result);
    }

    @Test
    void testGetUsdConversionRate() {
        String result = financialServiceImpl.getUsdConversionRate(ResponseCurrencyInformationDto.builder()
                .conversionRates(Map.of("USD", 1.08))
                .build());
        Assertions.assertEquals("1.08", result);
    }

    @Test
    void testGetUsdConversionRateNotExist() {
        String result = financialServiceImpl.getUsdConversionRate(ResponseCurrencyInformationDto.builder()
                .build());
        Assertions.assertEquals("Información no disponible", result);
    }
}