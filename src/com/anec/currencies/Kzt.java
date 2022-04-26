package com.anec.currencies;

public class Kzt {

    private static double getRate(String currencyRates) {
        int index = currencyRates.indexOf("<CharCode>KZT</CharCode>");

        int indexOfStartKztRate = index + 85;
        int indexOfEndKztRate = index + 91;

        StringBuilder kztRate = new StringBuilder();

        for (int i = indexOfStartKztRate; i <= indexOfEndKztRate; i++) {
            kztRate.append(currencyRates.charAt(i));
        }

        return Double.parseDouble(String.valueOf(kztRate).replace(',', '.'));
    }

    public static double toRubles(double value, String currencyRates) {
        return value * getRate(currencyRates);
    }

    public static double fromRubles(double value, String currencyRates) {
        return value / getRate(currencyRates);
    }
}
