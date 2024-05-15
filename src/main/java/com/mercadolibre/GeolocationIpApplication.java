package com.mercadolibre;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
				title= "Geolocalización - Mercadolibre",
                description = "xxxxxx",
                version = "1.0.0"
        )
)
@SpringBootApplication
public class GeolocationIpApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeolocationIpApplication.class, args);
    }

}
