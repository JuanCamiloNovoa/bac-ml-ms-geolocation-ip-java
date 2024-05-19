package com.mercadolibre.domain;

import java.util.Map;

/**
 * Servicio para la traducción de nombres de países e idiomas.
 */
public interface TranslationService {

    /**
     * Traduce el nombre de un país utilizando el mapeador de países.
     *
     * @param country el nombre del país en su idioma original.
     * @return el nombre del país traducido.
     */
    String translateCountry(String country);

    /**
     * Traduce un mapa de idiomas utilizando el mapeador de idiomas.
     *
     * @param languages un mapa donde las claves son códigos de idioma y los valores son los nombres de los idiomas en su idioma original.
     * @return un mapa donde las claves son los códigos de idioma traducidos y los valores son los nombres de los idiomas traducidos.
     */
    Map<String, String> translateLanguages(Map<String, String> languages);
}
