package com.mercadolibre.domain.service;

import com.mercadolibre.domain.DataResponseBuildService;
import com.mercadolibre.domain.GeoLocationIpService;
import com.mercadolibre.domain.StatisticsService;
import com.mercadolibre.domain.TranslationService;
import com.mercadolibre.domain.dto.response.DataServiceResponse;
import com.mercadolibre.domain.dto.response.TimeServiceResponse;
import com.mercadolibre.integration.ConsultCountryApiService;
import com.mercadolibre.integration.ConsultCurrencyApiService;
import com.mercadolibre.integration.ConsultIpApiService;
import com.mercadolibre.integration.dto.country.ResponseCountryInformationDto;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
import com.mercadolibre.integration.dto.ip.ResponseIpInformationDto;
import com.mercadolibre.util.api.ApiUtils;
import com.mercadolibre.util.mapper.DataResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Implementación del servicio de geolocalización por IP.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GeoLocationIpServiceImpl implements GeoLocationIpService {

    private final ConsultIpApiService consultIpApiService;
    private final ConsultCountryApiService consultCountryApiService;
    private final ConsultCurrencyApiService consultCurrencyApiService;
    private final DataResponseBuildService dataResponseBuildService;
    private final StatisticsService statisticsService;
    private final TranslationService translationService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<ResponseEntity<DataServiceResponse>> getInformation(String ip) {
        return consultIpApiService.getIpInformation(ip)
                .flatMap(ipInfo -> getCountryInformation(ipInfo)
                        .flatMap(countryInfo -> {
                            long distance = ApiUtils.getDistanceToBuenosAires(countryInfo.getLatlng());
                            return getCurrencyInformation(countryInfo)
                                    .flatMap(currencyInfo -> getTimeInformation(countryInfo)
                                            .flatMap(times -> buildResponse(ip, ipInfo, countryInfo, currencyInfo, times, distance)
                                                    .flatMap(response -> updateStatisticsAndReturnResponse(ipInfo, distance, response))));
                        }));
    }

    /**
     * Obtiene la información del país para una IP dada.
     *
     * @param ipInfo información de la IP.
     * @return información del país.
     */
    private Mono<ResponseCountryInformationDto> getCountryInformation(ResponseIpInformationDto ipInfo) {
        String countryName = ipInfo.getCountry();
        return consultCountryApiService.getCountryInformation(countryName);
    }

    /**
     * Obtiene la información de la moneda para un país dado.
     *
     * @param countryInfo información del país.
     * @return información de la moneda.
     */
    private Mono<ResponseCurrencyInformationDto> getCurrencyInformation(ResponseCountryInformationDto countryInfo) {
        String currencyCode = countryInfo.getCurrencies().getFirst().getCode();
        return consultCurrencyApiService.getCurrencyInformation(currencyCode);
    }

    /**
     * Obtiene la información de las zonas horarias  y horas para un país dado.
     *
     * @param countryInfo información del país.
     * @return lista de las zonas horarias y su hora correspondiente.
     */
    private Mono<List<TimeServiceResponse>> getTimeInformation(ResponseCountryInformationDto countryInfo) {
        List<String> timezones = countryInfo.getTimezones();
        return ApiUtils.getCurrentTimeForTimezones(timezones).collectList();
    }

    /**
     * Construye la respuesta de geolocalización.
     *
     * @param ip dirección IP.
     * @param ipInfo información de la IP.
     * @param countryInfo información del país.
     * @param currencyInfo información de la moneda.
     * @param times lista de zonas horarias.
     * @param distance distancia estimada a Buenos Aires.
     * @return urespuesta de geolocalización.
     */
    private Mono<ResponseEntity<DataServiceResponse>> buildResponse(String ip,
                                                                    ResponseIpInformationDto ipInfo,
                                                                    ResponseCountryInformationDto countryInfo,
                                                                    ResponseCurrencyInformationDto currencyInfo,
                                                                    List<TimeServiceResponse> times,
                                                                    long distance) {
        return Mono.just(ResponseEntity.ok(DataServiceResponse.builder()
                .status(DataResponseMapper.buildSuccessfulStatusResponse())
                .data(dataResponseBuildService.buildGeoLocationResponse(ip, ipInfo, countryInfo, currencyInfo, times, distance))
                .build()));
    }

    /**
     * Actualiza/Crea las estadísticas de consumo del servicio.
     *
     * @param ipInfo información de la IP.
     * @param distance distancia estimada.
     * @param response respuesta de geolocalización.
     * @return respuesta de geolocalización.
     */
    private Mono<ResponseEntity<DataServiceResponse>> updateStatisticsAndReturnResponse(ResponseIpInformationDto ipInfo,
                                                                                        long distance,
                                                                                        ResponseEntity<DataServiceResponse> response) {
        return statisticsService.updateStatistics(translationService.translateCountry(ipInfo.getCountry()), distance)
                .thenReturn(response);
    }
}
