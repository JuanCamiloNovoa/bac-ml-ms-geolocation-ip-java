package com.mercadolibre.web;

import com.mercadolibre.domain.dto.response.DataServiceResponse;
import com.mercadolibre.util.validation.IpValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.mercadolibre.util.constants.Constants.SUCCESS_CODE;

@RestController
public class GeoLocationIpController {

    @GetMapping("api/info")
    public Mono<ResponseEntity<DataServiceResponse>> getGeoLocalizationInfo(@RequestParam String ip){
        IpValidator.validateIp(ip);
        return null;
    }
}
