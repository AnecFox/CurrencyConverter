package com.anec.currencies;

public class Eur {

    private static double getRate(String currencyRates) {
        int index = currencyRates.indexOf("<CharCode>EUR</CharCode>");

        int indexOfStartEurRate = index + 68;
        int indexOfEndEurRate = index + 74;

        StringBuilder eurRate = new StringBuilder();

        for (int i = indexOfStartEurRate; i <= indexOfEndEurRate; i++) {
            eurRate.append(currencyRates.charAt(i));
        }

        return Double.parseDouble(String.valueOf(eurRate).replace(',', '.'));
    }

    public static double toRubles(double value, String currencyRates) {
        return value * getRate(currencyRates);
    }

    public static double fromRubles(double value, String currencyRates) {
        return value / getRate(currencyRates);
    }
}
