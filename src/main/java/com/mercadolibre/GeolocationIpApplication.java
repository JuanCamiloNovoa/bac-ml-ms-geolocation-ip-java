package com.mercadolibre;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@OpenAPIDefinition(
        info = @Info(
				title= "Geolocalización - Mercadolibre",
                description = "Servicio que dada una IP obtiene la información asociada y sus estadisticas de consumo",
                version = "1.0"
        )
)
@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "com.mercadolibre.persistence.repository")
public class GeolocationIpApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeolocationIpApplication.class, args);
    }

}
