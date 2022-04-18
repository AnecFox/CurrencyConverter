package com.anec.currency_converters;

public class Jpy {

    private static double getRate(String currencyRates) {
        int index = currencyRates.indexOf("<CharCode>JPY</CharCode>");

        int indexOfStartJpyRate = index + 78;
        int indexOfEndJpyRate = index + 83;

        StringBuilder jpyRate = new StringBuilder();

        for (int i = indexOfStartJpyRate; i <= indexOfEndJpyRate; i++) {
            jpyRate.append(currencyRates.charAt(i));
        }

        return Double.parseDouble(String.valueOf(jpyRate).replace(',', '.'));
    }

    public static double toRubles(double value, String currencyRates) {
        return value * getRate(currencyRates);
    }

    public static double fromRubles(double value, String currencyRates) {
        return value / getRate(currencyRates);
    }
}
