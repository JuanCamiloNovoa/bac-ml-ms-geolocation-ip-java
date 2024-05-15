package com.mercadolibre.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
public class ConfigVariable {
    @Value("${services.consult-ip.host}")
    private String consultIpHost;

    @Value("${services.consult-ip.url}")
    private String consultIpUrl;

    @Value("${services.consult-country.host}")
    private String consultCountryHost;

    @Value("${services.consult-country.url}")
    private String consultCountryUrl;

    @Value("${services.consult-currency.host}")
    private String consultCurrencyHost;

    @Value("${services.consult-currency.url}")
    private String consultCurrencyUrl;

    @Value("${services.timeout}")
    private long timeoutConfig;

    @Value("${services.cache.time}")
    private long cacheTime;
}
