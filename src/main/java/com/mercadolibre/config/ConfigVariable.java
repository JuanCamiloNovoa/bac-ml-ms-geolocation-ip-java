package com.mercadolibre.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 * Clase de configuración para guardar las propiedades de la aplicación.
 * Esta clase se utiliza para cargar las propiedades de los archivos de configuración de la aplicación.
 */
@Setter
@Getter
@Configuration
public class ConfigVariable {
    /**
     * URL del host para el servicio de consulta de IP.
     */
    @Value("${services.consult-ip.host}")
    private String consultIpHost;

    /**
     * Endpoint URL para el servicio de consulta de IP.
     */
    @Value("${services.consult-ip.url}")
    private String consultIpUrl;

    /**
     * URL del host para el servicio de consulta de país.
     */
    @Value("${services.consult-country.host}")
    private String consultCountryHost;

    /**
     * Endpoint URL para el servicio de consulta de país.
     */
    @Value("${services.consult-country.url}")
    private String consultCountryUrl;

    /**
     * URL del host para el servicio de consulta de moneda.
     */
    @Value("${services.consult-currency.host}")
    private String consultCurrencyHost;

    /**
     * Endpoint URL para el servicio de consulta de moneda.
     */
    @Value("${services.consult-currency.url}")
    private String consultCurrencyUrl;

    /**
     * Configuración del tiempo de espera para los servicios.
     */
    @Value("${services.timeout}")
    private long timeoutConfig;

    /**
     * Tiempo de expiración de la caché.
     */
    @Value("${services.cache.time}")
    private long cacheTime;
}
