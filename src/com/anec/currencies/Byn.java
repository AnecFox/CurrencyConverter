package com.anec.currencies;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Byn {

    private static double getRate(String currencyRates) {
        Pattern pattern = Pattern.compile("<Name>Белорусский рубль</Name><Value>(.*?)</Value>");
        Matcher matcher = pattern.matcher(currencyRates);

        if (!matcher.find()) {
            System.err.println("Pattern is not valid!");
        }

        return Double.parseDouble(matcher.group(1).replace(',', '.'));
    }

    public static double toRubles(double value, String currencyRates) {
        return value * getRate(currencyRates);
    }

    public static double fromRubles(double value, String currencyRates) {
        return value / getRate(currencyRates);
    }
}
