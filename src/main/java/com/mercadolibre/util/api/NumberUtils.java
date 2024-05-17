package com.mercadolibre.util.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NumberUtils {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.####");

    public static String formatDecimal(double number) {
        return decimalFormat.format(number);
    }
}
