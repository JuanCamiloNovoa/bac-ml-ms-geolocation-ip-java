package com.mercadolibre.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Clase de configuración para el almacenamiento en caché utilizando Caffeine.
 * Esta clase habilita el almacenamiento en caché y define un bean CaffeineCacheManager
 * para gestionar las configuraciones de caché.
 */
@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {

    private final ConfigVariable configVariable;

    /**
     * Crea y configura un bean CaffeineCacheManager y se registran las clases que van.
     * a guardar la informacion en cache
     *
     * @return el CacheManager configurado
     */
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("ipInfoCache", "countryInfoCache", "currencyInfoCache");
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(configVariable.getCacheTime(), TimeUnit.MINUTES)
                .maximumSize(250));
        cacheManager.setAsyncCacheMode(true);
        return cacheManager;
    }
}
