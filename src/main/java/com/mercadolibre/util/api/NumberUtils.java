package com.mercadolibre.util.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

/**
 * Clase utilitaria para operaciones con números.
 * Esta clase proporciona métodos estáticos para formatear números decimales.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NumberUtils {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.####");

    /**
     * Formatea un número decimal a una cadena con cuatro decimales.
     *
     * @param number el número decimal a formatear
     * @return el número formateado como cadena
     */
    public static String formatDecimal(double number) {
        return decimalFormat.format(number);
    }
}
