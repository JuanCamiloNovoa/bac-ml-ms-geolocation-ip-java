package com.mercadolibre.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Clase de configuración para crear y configurar el bean WebClient.
 * Esta clase proporciona un cliente HTTP reactivo con configuración SSL y de tiempo de espera.
 */
@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final ConfigVariable configVariable;

    /**
     * Crea y configura un bean WebClient con soporte SSL y configuración de tiempos de espera.
     *
     * @return una instancia configurada de WebClient
     * @throws SSLException si ocurre un error al configurar el contexto SSL
     */
    @Bean
    public WebClient webClient() throws SSLException {
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create()
                .secure(t -> t.sslContext(sslContext).handshakeTimeout(Duration.ofMillis(configVariable.getTimeoutConfig())))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) configVariable.getTimeoutConfig())
                .disableRetry(false)
                .responseTimeout(Duration.ofMillis((int) configVariable.getTimeoutConfig()))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler((int) configVariable.getTimeoutConfig(), TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler((int) configVariable.getTimeoutConfig(), TimeUnit.MILLISECONDS)));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
