package com.mercadolibre.domain.service;

import com.mercadolibre.domain.DataResponseBuildService;
import com.mercadolibre.domain.FinancialService;
import com.mercadolibre.domain.TranslationService;
import com.mercadolibre.domain.dto.response.CurrencyServiceResponse;
import com.mercadolibre.domain.dto.response.GeoLocationDataResponse;
import com.mercadolibre.domain.dto.response.TimeServiceResponse;
import com.mercadolibre.integration.dto.country.ResponseCountryInformationDto;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
import com.mercadolibre.integration.dto.ip.ResponseIpInformationDto;
import com.mercadolibre.util.api.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.mercadolibre.util.constants.Constants.*;
@Service
@RequiredArgsConstructor
public class DataResponseBuildServiceImpl implements DataResponseBuildService {

    private final FinancialService financialService;
    private final TranslationService translationService;

    @Override
    public GeoLocationDataResponse buildGeoLocationResponse(String ip,
                                                            ResponseIpInformationDto ipInfo,
                                                            ResponseCountryInformationDto countryInfo,
                                                            ResponseCurrencyInformationDto currencyInfo,
                                                            List<TimeServiceResponse> times,
                                                            long distance) {
        return GeoLocationDataResponse.builder()
                .ip(ip)
                .fechaActual(ApiUtils.getCurrentTime())
                .pais(buildCountryString(ipInfo.getCountry()))
                .isoCode(ipInfo.getCountryCode())
                .idiomas(buildLanguageMap(countryInfo.getLanguages()))
                .moneda(CurrencyServiceResponse.builder()
                        .codigo(financialService.getCountryCurrency(countryInfo.getCurrencies()))
                        .cotizacionDolar(financialService.getUsdConversionRate(currencyInfo))
                        .build())
                .hora(times)
                .distanciaEstimada(buildDistanceString(distance, countryInfo))
                .bandera(countryInfo.getFlags().getSvg())
                .build();
    }

    private String buildDistanceString(long distance, ResponseCountryInformationDto countryInfo) {
        return String.format(
                DISTANCE_INFORMATION,
                distance,
                LATITUDE_BUENOS_AIRES,
                LONGITUDE_BUENOS_AIRES,
                countryInfo.getLatlng().get(0),
                countryInfo.getLatlng().get(1)
        );
    }

    private String buildCountryString(String country) {
        return String.format(
                COUNTRY_INFORMATION,
                translationService.translateCountry(country),
                country
        );
    }

    private Map<String,String> buildLanguageMap(Map<String,String> languages){
        return translationService.translateLanguages(languages);
    }
}
