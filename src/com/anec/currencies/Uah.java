package com.anec.currencies;

public class Uah {

    private static double getRate(String currencyRates) {
        int index = currencyRates.indexOf("<CharCode>UAH</CharCode>");

        int indexOfStartUahRate = index + 82;
        int indexOfEndUahRate = index + 88;

        StringBuilder uahRate = new StringBuilder();

        for (int i = indexOfStartUahRate; i <= indexOfEndUahRate; i++) {
            uahRate.append(currencyRates.charAt(i));
        }

        return Double.parseDouble(String.valueOf(uahRate).replace(',', '.'));
    }

    public static double toRubles(double value, String currencyRates) {
        return value * getRate(currencyRates);
    }

    public static double fromRubles(double value, String currencyRates) {
        return value / getRate(currencyRates);
    }
}
