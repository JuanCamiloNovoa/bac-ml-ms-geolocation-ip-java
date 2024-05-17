package com.mercadolibre.web;

import com.mercadolibre.domain.GeoLocationIpService;
import com.mercadolibre.domain.StatisticsService;
import com.mercadolibre.domain.dto.response.DataServiceResponse;
import com.mercadolibre.domain.dto.response.Statistics;
import com.mercadolibre.util.mapper.DataResponseMapper;
import com.mercadolibre.util.validation.IpValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Tag(name = "Geolocalización de  IP", description = "Endpoints para acceder a la información del servicio")
public class GeoLocationIpController {
    private final GeoLocationIpService geoLocationIpService;
    private final StatisticsService statisticsService;

    @Operation(summary = "Obtener información de geolocalización de una IP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(examples = {
                            @ExampleObject(name = "La información se consulta correctamente",
                                    summary = "Petición exitosa",
                                    value = "{\"status\":{\"statusCode\":\"200\",\"statusDescription\": \"Solicitud procesada correctamente\"},\"data\": {\"ip\": \"36.112.0.0\",\"fechaActual\":" +
                                            " \"17/05/2024 17:16:52\",\"pais\": \"China (China)\",\"isoCode\": \"CN\",\"idiomas\": {\"zh\": \"Chino\"},\"moneda\": {\"codigo\": \"TWD\"," +
                                            "\"cotizacionDolar\": \"0.0311\"},\"hora\": [{ \"valor\": \"06:16:52\"," +
                                            "\"zonaHoraria\": \"UTC+08:00\"}],\"distanciaEstimada\": \"18750 kms (-34.0,-64.0) a (23.5,121.0)\",\"bandera\": \"https://flagcdn.com/tw.svg\"}}"
                            )
                    }, schema = @Schema(implementation = DataServiceResponse.class), mediaType = "application/json")
            }, description = "Respuesta Exitosa"),
            @ApiResponse(responseCode = "400", content = {
                    @Content(examples = {
                            @ExampleObject(name = "Petición inválida por parámetro",
                                    summary = "La IP no es válida",
                                    value = "{\"status\": {\"statusCode\": \"400\",\"statusDescription\": \"The specified ip is not valid\"}}"
                            )
                    }, schema = @Schema(implementation = DataServiceResponse.class), mediaType = "application/json")
            }, description = "El request no presenta los parámetros requeridos por la transacción"),
            @ApiResponse(responseCode = "500", content = {
                    @Content(examples = {
                            @ExampleObject(name = "Error interno del servidor",
                                    summary = "Error en el servicio",
                                    value = "{\"status\": {\"statusCode\": \"500\",\"statusDescription\": \"Error al obtener la información de la IP. Por favor inténtelo nuevamente\"}}"
                            )
                    }, schema = @Schema(implementation = DataServiceResponse.class), mediaType = "application/json")
            }, description = "Error interno del servidor")
    })
    @GetMapping(value = "/api/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<DataServiceResponse>> getGeoLocalizationInfo(@RequestParam String ip) {
        IpValidator.validateIp(ip);
        return geoLocationIpService.getInformation(ip);
    }


    @Operation(summary = "Obtener estadísticas de geolocalización")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(examples = {
                            @ExampleObject(name = "La información se consulta correctamente",
                                    summary = "Petición exitosa",
                                    value = "{\"status\":{\"statusCode\":\"200\",\"statusDescription\": \"Solicitud procesada correctamente\"},\"maxDistance\": 18750, \"minDistance\": 4308,\"averageDistance\": \"14826\",\"maxDistanceCountry\": \"China\",\"minDistanceCountry\": \"Colombia\""
                            )
                    }, schema = @Schema(implementation = DataServiceResponse.class), mediaType = "application/json")
            }, description = "Respuesta Exitosa"),
            @ApiResponse(responseCode = "500", content = {
                    @Content(examples = {
                            @ExampleObject(name = "Error interno del servidor",
                                    summary = "Error en el servicio",
                                    value = "{\"status\": {\"statusCode\": \"500\",\"statusDescription\": \"Error al obtener la información de la IP. Por favor inténtelo nuevamente\"}}"
                            )
                    }, schema = @Schema(implementation = DataServiceResponse.class), mediaType = "application/json")
            }, description = "Error interno del servidor")
    })
    @GetMapping(value = "/api/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Statistics>> getStatistics() {
        return statisticsService.getStatistics()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body(Statistics.builder()
                        .statusResponse(DataResponseMapper.buildStatusResponseEmpty())
                        .build()));
    }
}
