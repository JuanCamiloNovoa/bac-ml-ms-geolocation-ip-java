package com.mercadolibre.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    // HTTP Status Codes
    public static final String INTERNAL_SERVER_ERROR_CODE = "500";
    public static final String BAD_REQUEST_CODE = "400";
    public static final String SUCCESS_CODE = "200";

    // Geolocation
    public static final double LATITUDE_BUENOS_AIRES = -34;
    public static final double LONGITUDE_BUENOS_AIRES = -64;

    // Currency Configuration
    public static final String CURRENCY_CONVERSION_RATE = "USD";

    // API Name
    public static final String NAME_CONSULT_IP_API = "Consult IP";
    public static final String NAME_CONSULT_COUNTRY_API = "Consult Country";
    public static final String NAME_CONSULT_CURRENCY_API = "Consult Country";
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");


    // TIME PARAMS
    public static final String TIMEZONE = "America/Bogota";
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    //Responses
    public static final String ERROR_RESPONSE = "Error retrieving ip information, please try again.";
    public static final String ERROR_INVALID_IP = "The specified ip is not valid";
    public static final String ERROR_API_CONSULT_IP = "An error occurred when calling the IP consult service.";
    public static final String ERROR_API_CONSULT_COUNTRY = "An error occurred when calling the country consult service.";
    public static final String ERROR_API_CONSULT_CURRENCY = "An error occurred when calling the currency consult service.";
    public static final String ERROR_GETTING_INFORMATION = "Error consuming service: [%s], reason: [%s]";
    public static final String SUCCESS_RESPONSE = "Solicitud procesada correctamente";
    public static final String DISTANCE_INFORMATION = "[%s] kms ([%s],[%s]) a ([%s],[%s])";


}
