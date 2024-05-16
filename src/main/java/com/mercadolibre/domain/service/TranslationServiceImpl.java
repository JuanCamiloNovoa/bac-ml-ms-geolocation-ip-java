package com.mercadolibre.domain.service;

import com.mercadolibre.util.mapper.CountryMapper;
import com.mercadolibre.util.mapper.LanguageMapper;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TranslationServiceImpl implements com.mercadolibre.domain.TranslationService {
    @Override
    public String translateCountry(String country) {
        return CountryMapper.getCountryName(country);
    }

    @Override
    public Map<String, String> translateLanguages(Map<String, String> languages) {
        return languages.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> LanguageMapper.getLanguageCode(entry.getKey()),
                        entry -> LanguageMapper.getLanguageName(entry.getValue())
                ));
    }
}