package com.mercadolibre.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {

    private final ConfigVariable configVariable;
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("ipInfoCache", "countryInfoCache","currencyInfoCache");
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(configVariable.getCacheTime(), TimeUnit.MINUTES)
                .maximumSize(250));
        return cacheManager;
    }
}
