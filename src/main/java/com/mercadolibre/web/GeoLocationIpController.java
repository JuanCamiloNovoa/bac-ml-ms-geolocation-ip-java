package com.mercadolibre.web;

import com.mercadolibre.domain.GeoLocationIpService;
import com.mercadolibre.domain.dto.response.DataServiceResponse;
import com.mercadolibre.util.validation.IpValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class GeoLocationIpController {
    private final GeoLocationIpService geoLocationIpService;

    @GetMapping("/api/info")
    public Mono<ResponseEntity<DataServiceResponse>> getGeoLocalizationInfo(@RequestParam String ip){
        IpValidator.validateIp(ip);
        return geoLocationIpService.getInformation(ip);
    }
}
