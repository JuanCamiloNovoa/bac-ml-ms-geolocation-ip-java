package com.mercadolibre.util.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CountryMapper {

    private static final Map<String, String> COUNTRY_MAP = new HashMap<>();

    static {
        COUNTRY_MAP.put("Spain", "España");
        COUNTRY_MAP.put("United States", "Estados Unidos");
        COUNTRY_MAP.put("France", "Francia");
        COUNTRY_MAP.put("Germany", "Alemania");
        COUNTRY_MAP.put("Italy", "Italia");
        COUNTRY_MAP.put("Brazil", "Brasil");
        COUNTRY_MAP.put("Argentina", "Argentina");
        // Agregar más mapeos de países según sea necesario
    }

    public static String getCountryName(String name) {
        return COUNTRY_MAP.getOrDefault(name, name);
    }
}
