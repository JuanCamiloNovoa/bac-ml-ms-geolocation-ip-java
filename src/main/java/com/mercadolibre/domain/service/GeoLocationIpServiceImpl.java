package com.mercadolibre.domain.service;

import com.mercadolibre.domain.GeoLocationIpService;
import com.mercadolibre.domain.dto.response.DataServiceResponse;
import com.mercadolibre.integration.ConsultIpApiService;
import com.mercadolibre.integration.dto.ip.ResponseIpInformationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeoLocationIpServiceImpl implements GeoLocationIpService {

    private final ConsultIpApiService consultIpApiService;

    @Override
    public Mono<ResponseEntity<DataServiceResponse>> getInformation(String ip) {
        return consultIpApiService.getIpInformation(ip);

        return null;
    }


    private ResponseIpInformationDto getIpApiResponse(String ip){
        return consultIpApiService.getIpInformation(ip)
                .flatMap(ipResponse->{
                    return ResponseIpInformationDto.builder()
                            .country(ipResponse.getCountry())
                            .countryCode(ipResponse.getCountryCode())
                            .build();
                });
    }
}
