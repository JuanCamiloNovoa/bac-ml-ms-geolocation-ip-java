package com.mercadolibre.util.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.mercadolibre.util.constants.Constants.*;

/**
 * Clase utilitaria para el mapeo y traducción de nombres de países.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CountryMapper {

    private static final Map<String, String> COUNTRY_MAP = new HashMap<>();

    static {
        loadMappings();
    }

    /**
     * Carga los mapeos de un archivo de propiedades en un mapa.
     */
    private static void loadMappings() {
        try (InputStream input = CountryMapper.class.getClassLoader().getResourceAsStream(COUNTRY_NAMES_FILE)) {
            if (input == null) {
                throw new IllegalStateException(String.format(FILE_NOT_FOUND, COUNTRY_NAMES_FILE));
            }
            Properties prop = new Properties();
            prop.load(input);

            COUNTRY_MAP.putAll(prop.stringPropertyNames().stream()
                    .collect(Collectors.toMap(
                            key -> key.replace(" ", ""),
                            prop::getProperty
                    )));
        } catch (IOException ex) {
            throw new IllegalStateException(String.format(LOAD_MAP_FAILED, COUNTRY_NAMES_FILE), ex);
        }
    }

    /**
     * Obtiene el nombre del país traducido.
     *
     * @param name el nombre del país en inglés
     * @return el nombre del país traducido al español
     */
    public static String getCountryName(String name) {
        return COUNTRY_MAP.getOrDefault(name.replace(" ", ""), name);
    }
}