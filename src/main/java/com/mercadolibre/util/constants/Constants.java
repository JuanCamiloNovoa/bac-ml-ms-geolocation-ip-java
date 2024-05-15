package com.mercadolibre.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    // HTTP Status Codes
    public static final String INTERNAL_SERVER_ERROR_CODE = "500";
    public static final String BAD_REQUEST_CODE = "400";
    public static final String SUCCESS_CODE = "200";

    // API Name
    public static final String NAME_CONSULT_IP_API = "Consult IP";
    public static final String NAME_CONSULT_COUNTRY_API = "Consult Country";
    public static final String NAME_CONSULT_CURRENCY_API = "Consult Country";

    //Responses
    public static final String ERROR_RESPONSE = "Error retrieving ip information, please try again.";
    public static final String ERROR_INVALID_IP = "The specified ip is not valid";
    public static final String ERROR_API_CONSULT_IP = "An error occurred when calling the IP consult service.";
    public static final String ERROR_API_CONSULT_COUNTRY = "An error occurred when calling the country consult service.";
    public static final String ERROR_API_CONSULT_CURRENCY = "An error occurred when calling the currency consult service.";
    public static final String ERROR_GETTING_INFORMATION = "Error consuming service: [%s], reason: [%s]";


}
