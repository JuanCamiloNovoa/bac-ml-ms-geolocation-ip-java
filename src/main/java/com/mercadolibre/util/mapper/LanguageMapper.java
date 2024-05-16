package com.mercadolibre.util.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.mercadolibre.util.constants.Constants.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LanguageMapper {

    private static final Map<String, String> LANGUAGE_CODE_MAP = new HashMap<>();
    private static final Map<String, String> LANGUAGE_NAME_MAP = new HashMap<>();


    static {
        loadMappings(LANGUAGE_CODES_FILE, LANGUAGE_CODE_MAP);
        loadMappings(LANGUAGE_NAMES_FILE, LANGUAGE_NAME_MAP);
    }


    private static void loadMappings(String fileName, Map<String, String> map) {
        try (InputStream input = LanguageMapper.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalStateException(String.format(FILE_NOT_FOUND,fileName));
            }
            Properties prop = new Properties();
            prop.load(input);
            for (String key : prop.stringPropertyNames()) {
                map.put(key, prop.getProperty(key));
            }
        } catch (IOException ex) {
            throw new IllegalStateException(String.format(LOAD_MAP_FAILED,fileName), ex);
        }
    }


    public static String getLanguageCode(String code) {
        return LANGUAGE_CODE_MAP.getOrDefault(code, code);
    }

    public static String getLanguageName(String name) {
        return LANGUAGE_NAME_MAP.getOrDefault(name, name);
    }
}
