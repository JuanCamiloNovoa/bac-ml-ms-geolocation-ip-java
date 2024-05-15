package com.mercadolibre.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
public class ConfigVariable {
    @Value("${services.siebel.url}")
    private String testUrl;

    @Value("${services.timeout}")
    private long timeoutConfig;
}
