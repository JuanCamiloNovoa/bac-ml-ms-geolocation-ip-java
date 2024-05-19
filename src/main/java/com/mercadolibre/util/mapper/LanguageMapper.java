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
 * Clase utilitaria para el mapeo y traducción de códigos y nombres de idiomas.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LanguageMapper {

    private static final Map<String, String> LANGUAGE_CODE_MAP = new HashMap<>();
    private static final Map<String, String> LANGUAGE_NAME_MAP = new HashMap<>();

    static {
        loadMappings(LANGUAGE_CODES_FILE, LANGUAGE_CODE_MAP);
        loadMappings(LANGUAGE_NAMES_FILE, LANGUAGE_NAME_MAP);
    }

    /**
     * Carga los mapeos de un archivo de propiedades en un mapa.
     *
     * @param fileName el nombre del archivo de propiedades
     * @param map el mapa donde se almacenarán los mapeos
     */
    private static void loadMappings(String fileName, Map<String, String> map) {
        try (InputStream input = LanguageMapper.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalStateException(String.format(FILE_NOT_FOUND, fileName));
            }
            Properties prop = new Properties();
            prop.load(input);

            map.putAll(prop.stringPropertyNames().stream()
                    .collect(Collectors.toMap(
                            key -> key.replace(" ", ""),
                            prop::getProperty
                    )));
        } catch (IOException ex) {
            throw new IllegalStateException(String.format(LOAD_MAP_FAILED, fileName), ex);
        }
    }

    /**
     * Obtiene el código de idioma traducido.
     *
     * @param code el código de idioma original
     * @return el código de idioma traducido
     */
    public static String getLanguageCode(String code) {
        return LANGUAGE_CODE_MAP.getOrDefault(code.replace(" ", ""), code);
    }

    /**
     * Obtiene el nombre del idioma traducido.
     *
     * @param name el nombre del idioma original
     * @return el nombre del idioma traducido
     */
    public static String getLanguageName(String name) {
        return LANGUAGE_NAME_MAP.getOrDefault(name.replace(" ", ""), name);
    }
}