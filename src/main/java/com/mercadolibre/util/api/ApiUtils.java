package com.mercadolibre.util.api;

import com.mercadolibre.domain.dto.response.TimeServiceResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.mercadolibre.util.constants.Constants.*;

/**
 * Clase utilitaria para realizar varias operaciones relacionadas con la API.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiUtils {
    private static final double LAT_BUENOS_AIRES_RAD = Math.toRadians(LATITUDE_BUENOS_AIRES);
    private static final double LON_BUENOS_AIRES_RAD = Math.toRadians(LONGITUDE_BUENOS_AIRES);

    /**
     * Obtiene la hora actual para una lista de zonas horarias.
     *
     * @param timezones Lista de zonas horarias.
     * @return Un {@link Flux} de {@link TimeServiceResponse} con la hora actual y la zona horaria.
     */
    public static Flux<TimeServiceResponse> getCurrentTimeForTimezones(List<String> timezones) {
        return Flux.fromIterable(timezones)
                .map(zone -> {
                    LocalDateTime now = LocalDateTime.now(ZoneId.of(zone));
                    String formattedTime = now.format(TIME_FORMATTER);
                    return new TimeServiceResponse(formattedTime, zone);
                });
    }

    /**
     * Obtiene la hora actual en la zona horaria predeterminada.
     *
     * @return Una cadena con la hora actual formateada.
     */
    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of(TIMEZONE));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return now.format(formatter);
    }

    /**
     * Calcula la distancia desde una ubicación dada hasta Buenos Aires.
     *
     * @param countryDistance Lista con la latitud y longitud del país.
     * @return La distancia en kilómetros redondeada.
     */
    public static long getDistanceToBuenosAires(List<Double> countryDistance) {
        return calculateDistance(countryDistance.get(0), countryDistance.get(1));
    }

    /**
     * Calcula la distancia en kilómetros entre Buenos Aires y una ubicación dada con la Fórmula Haversine.
     *
     * @param latitudeCountry Latitud del país.
     * @param longitudeCountry Longitud del país.
     * @return La distancia en kilómetros redondeada.
     */
    private static long calculateDistance(double latitudeCountry, double longitudeCountry) {
        double latCountryRad = Math.toRadians(latitudeCountry);
        double lonCountryRad = Math.toRadians(longitudeCountry);

        double lat = latCountryRad - LAT_BUENOS_AIRES_RAD;
        double lon = lonCountryRad - LON_BUENOS_AIRES_RAD;

        double a = Math.sin(lat / 2) * Math.sin(lat / 2) +
                Math.cos(LAT_BUENOS_AIRES_RAD) * Math.cos(latCountryRad) *
                        Math.sin(lon / 2) * Math.sin(lon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;
        return Math.round(distance);
    }
}
