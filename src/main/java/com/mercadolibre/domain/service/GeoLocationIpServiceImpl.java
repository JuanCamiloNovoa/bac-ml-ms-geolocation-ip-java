package com.mercadolibre.domain.service;

import com.mercadolibre.domain.*;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class GeoLocationIpServiceImpl implements GeoLocationIpService {

    private final ConsultIpApiService consultIpApiService;
    private final ConsultCountryApiService consultCountryApiService;
    private final ConsultCurrencyApiService consultCurrencyApiService;
    private final DataResponseBuildService dataResponseBuildService;
    private final FinancialService financialService;
    private final StatisticsService statisticsService;
    private final TranslationService translationService;
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

    private Mono<ResponseCountryInformationDto> getCountryInformation(ResponseIpInformationDto ipInfo) {
        String countryName = ipInfo.getCountry();
        return consultCountryApiService.getCountryInformation(countryName);
    }

    private Mono<ResponseCurrencyInformationDto> getCurrencyInformation(ResponseCountryInformationDto countryInfo) {
        String currencyCode = financialService.getCountryCurrency(countryInfo.getCurrencies());
        return consultCurrencyApiService.getCurrencyInformation(currencyCode);
    }

    private Mono<List<TimeServiceResponse>> getTimeInformation(ResponseCountryInformationDto countryInfo) {
        List<String> timezones = countryInfo.getTimezones();
        return ApiUtils.getCurrentTimeForTimezones(timezones).collectList();
    }

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

    private Mono<ResponseEntity<DataServiceResponse>> updateStatisticsAndReturnResponse(ResponseIpInformationDto ipInfo,
                                                                                        long distance,
                                                                                        ResponseEntity<DataServiceResponse> response) {
        return statisticsService.updateStatistics(translationService.translateCountry(ipInfo.getCountry()), distance)
                .thenReturn(response);
    }

}
