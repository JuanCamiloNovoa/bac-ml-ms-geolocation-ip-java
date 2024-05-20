package com.mercadolibre.domain.service;

import com.mercadolibre.domain.DataResponseBuildService;
import com.mercadolibre.domain.FinancialService;
import com.mercadolibre.domain.TranslationService;
import com.mercadolibre.domain.dto.response.CurrencyServiceResponse;
import com.mercadolibre.domain.dto.response.GeoLocationDataResponse;
import com.mercadolibre.domain.dto.response.TimeServiceResponse;
import com.mercadolibre.integration.dto.country.LanguageInformation;
import com.mercadolibre.integration.dto.country.ResponseCountryInformationDto;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
import com.mercadolibre.integration.dto.ip.ResponseIpInformationDto;
import com.mercadolibre.util.api.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mercadolibre.util.constants.Constants.*;

/**
 * Implementación del servicio que construye las respuestas de geolocalización basadas en la información proporcionada.
 */
@Service
@RequiredArgsConstructor
public class DataResponseBuildServiceImpl implements DataResponseBuildService {

    private final FinancialService financialService;
    private final TranslationService translationService;

    /**
     * {@inheritDoc}
     */
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
                        .codigo(countryInfo.getCurrencies().getFirst().getCode())
                        .cotizacionDolar(financialService.getUsdConversionRate(currencyInfo))
                        .build())
                .hora(times)
                .distanciaEstimada(buildDistanceString(distance, countryInfo))
                .bandera(String.format("https://flagcdn.com/%s.svg", countryInfo.getAlpha2Code().toLowerCase()))
                .build();
    }

    /**
     * Construye una cadena que describe la distancia de Buenos Aires al país.
     *
     * @param distance    distancia calculada.
     * @param countryInfo información del país.
     * @return cadena de distancia formateada.
     */
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

    /**
     * Construye una cadena con la traducción del nombre del pais.
     *
     * @param country nombre del país.
     * @return cadena de país formateada.
     */
    private String buildCountryString(String country) {
        return String.format(
                COUNTRY_INFORMATION,
                translationService.translateCountry(country),
                country
        );
    }

    /**
     * Construye un mapa de idiomas traducidos.
     *
     * @param languages mapa original de idiomas.
     * @return mapa de idiomas traducidos.
     */
    private Map<String, String> buildLanguageMap(List<LanguageInformation> languages) {
        Map<String, String> languageMap = languages.stream()
                .collect(Collectors.toMap(LanguageInformation::getIso, LanguageInformation::getName));
        return translationService.translateLanguages(languageMap);
    }
}