package com.anec.currency_converters;

public class Usd {

    private static double getRate(String currencyRates) {
        int index = currencyRates.indexOf("<CharCode>USD</CharCode>");

        int indexOfStartUsdRate = index + 74;
        int indexOfEndUsdRate = index + 80;

        StringBuilder usdRate = new StringBuilder();

        for (int i = indexOfStartUsdRate; i <= indexOfEndUsdRate; i++) {
            usdRate.append(currencyRates.charAt(i));
        }

        return Double.parseDouble(String.valueOf(usdRate).replace(',', '.'));
    }

    public static double toRubles(double value, String currencyRates) {
        return value * getRate(currencyRates);
    }

    public static double fromRubles(double value, String currencyRates) {
        return value / getRate(currencyRates);
    }
}
