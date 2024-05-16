package com.mercadolibre.util.api;

import com.mercadolibre.domain.dto.response.TimeServiceResponse;
import com.mercadolibre.integration.dto.country.CountryCurrency;
import com.mercadolibre.integration.dto.currency.ResponseCurrencyInformationDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static com.mercadolibre.util.constants.Constants.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ApiUtils {


    public static Flux<TimeServiceResponse> getCurrentTimeForTimezones(List<String> timezones) {
        return Flux.fromIterable(timezones)
                .map(zone -> {
                    LocalDateTime now = LocalDateTime.now(ZoneId.of(zone));
                    String formattedTime = now.format(TIME_FORMATTER);
                    return new TimeServiceResponse(formattedTime, zone);
                });
    }
    /**
     * @param currencyMap
     * @description
     * @return
     * */
    public static String getCountryCurrency(Map<String, CountryCurrency> currencyMap){
        Map.Entry<String,CountryCurrency> entry = currencyMap.entrySet().iterator().next();
        log.info("COUNTRY_CURRENCY: {}", entry.getKey());
        return entry.getKey();
    }

    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of(TIMEZONE));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return now.format(formatter);
    }

    public static double getDistanceToBuenosAires(List<Double> countryDistance){
        return calculateDistance(countryDistance.get(0),countryDistance.get(1));
    }

    private static double calculateDistance(double latitudeCountry, double longitudeCountry) {
        GeodesicData g = Geodesic.WGS84.Inverse(LATITUDE_BUENOS_AIRES, LONGITUDE_BUENOS_AIRES, latitudeCountry, longitudeCountry);
        log.info("DISTNACIA EN KILOMETROS: {}", g.s12 / 1000);
        return g.s12 / 1000;
    }

    public static Double getUsdConversionRate(ResponseCurrencyInformationDto currencyInformationDto) {
        return currencyInformationDto.getConversionRates().get(CURRENCY_CONVERSION_RATE);
    }
}
