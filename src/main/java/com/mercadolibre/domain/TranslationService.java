package com.mercadolibre.domain;

import java.util.Map;

public interface TranslationService {
    Map<String, String> translateLanguages(Map<String, String> languages);
    String translateCountry(String country);
}
