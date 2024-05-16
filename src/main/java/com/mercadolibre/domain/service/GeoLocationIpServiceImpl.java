package com.mercadolibre.domain.service;

import com.mercadolibre.domain.GeoLocationIpService;
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

    @Override
    public Mono<ResponseEntity<DataServiceResponse>> getInformation(String ip) {
        return consultIpApiService.getIpInformation(ip)
                .flatMap(ipInfo -> {
                    String countryCode = ipInfo.getCountryCode();
                    return consultCountryApiService.getCountryInformation(countryCode)
                            .flatMap(countryInfo -> {
                                String currencyCode = ApiUtils.getCountryCurrency(countryInfo.getCurrencies());
                                return consultCurrencyApiService.getCurrencyInformation(currencyCode)
                                        .flatMap(currencyInfo -> {
                                            List<String> timezones = countryInfo.getTimezones();
                                            List<Double> countryDistances = countryInfo.getLatlng();
                                            double distance = ApiUtils.getDistanceToBuenosAires(countryDistances);
                                            return ApiUtils.getCurrentTimeForTimezones(timezones)
                                                    .collectList()
                                                    .flatMap(times -> buildResponse(ip, ipInfo, countryInfo, currencyInfo, times, distance));
                                        });
                            });
                });
    }

    private Mono<ResponseEntity<DataServiceResponse>> buildResponse(String ip,
                                                                    ResponseIpInformationDto ipInfo,
                                                                    ResponseCountryInformationDto countryInfo,
                                                                    ResponseCurrencyInformationDto currencyInfo,
                                                                    List<TimeServiceResponse> times,
                                                                    double distance) {
        return Mono.just(ResponseEntity.ok(DataServiceResponse.builder()
                .status(DataResponseMapper.buildSuccessfulStatusResponse())
                .data(DataResponseMapper.buildGeoLocationResponse(ip, ipInfo, countryInfo, currencyInfo, times, distance))
                .build()));
    }

}
