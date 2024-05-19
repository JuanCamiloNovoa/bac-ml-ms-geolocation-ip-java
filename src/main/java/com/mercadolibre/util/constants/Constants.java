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
    public static final String NOT_FOUND_CODE = "404";

    // Geolocation
    public static final double LATITUDE_BUENOS_AIRES = -34;
    public static final double LONGITUDE_BUENOS_AIRES = -64;
    public static final double EARTH_RADIUS = 6371.0;


    // Currency Configuration
    public static final String CURRENCY_CONVERSION_RATE = "USD";


    // API Name
    public static final String NAME_CONSULT_IP_API = "Consultat IP";
    public static final String NAME_CONSULT_COUNTRY_API = "Consultar Pais";
    public static final String NAME_CONSULT_CURRENCY_API = "Consultar Moneda";
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");


    // Time Params
    public static final String TIMEZONE = "America/Bogota";
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    //Responses
    public static final String ERROR_RESPONSE = "Error al obtener la información de la IP. Por favor inténtelo nuevamente";
    public static final String ERROR_INVALID_IP = "La ip ingresada no es válida";
    public static final String ERROR_API_CONSULT_IP = "Se ha producido un error al llamar al servicio de consulta IP.";
    public static final String ERROR_API_CONSULT_COUNTRY = "Se ha producido un error al llamar al servicio de consulta de país.";
    public static final String ERROR_API_CONSULT_CURRENCY = "Se ha producido un error al llamar al servicio de consulta de divisas.";
    public static final String ERROR_GETTING_INFORMATION = "Error al consumir el servicio: [%s], motivo: [%s]";
    public static final String SUCCESS_RESPONSE = "Solicitud procesada correctamente";
    public static final String DISTANCE_INFORMATION = "%d kms (%.1f,%.1f) a (%.1f,%.1f)";
    public static final String EMPTY_CURRENCY_INFORMATION = "Información no disponible";
    public static final String COUNTRY_INFORMATION = "%s (%s)";
    public static final String NOT_FOUND_DESCRIPTION = "No hay información registrada";

    // Language files
    public static final String LANGUAGE_CODES_FILE= "language-codes.properties";
    public static final String LANGUAGE_NAMES_FILE= "language-names.properties";
    public static final String COUNTRY_NAMES_FILE = "country-names.properties";
    public static final String FILE_NOT_FOUND = "No se pudo encontrar el archivo: %s";
    public static final String LOAD_MAP_FAILED = "Error al cargar contenido del archivo: %s";


}
