package com.mercadolibre.domain.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class TranslationServiceImplTest {
    @InjectMocks
    private TranslationServiceImpl translationServiceImpl;

    @Test
    void testTranslateCountry() {
        String result = translationServiceImpl.translateCountry("Colombia");
        Assertions.assertEquals("Colombia", result);
    }

    @Test
    void testTranslateLanguages() {
        Map<String, String> result = translationServiceImpl.translateLanguages(Map.of("spa", "Spanish"));
        Assertions.assertEquals(Map.of("es", "Español"), result);
    }
}