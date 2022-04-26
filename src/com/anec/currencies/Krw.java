package com.anec.currencies;

public class Krw {

    private static double getRate(String currencyRates) {
        int index = currencyRates.indexOf("<CharCode>KRW</CharCode>");

        int indexOfStartKrwRate = index + 87;
        int indexOfEndKrwRate = index + 93;

        StringBuilder krwRate = new StringBuilder();

        for (int i = indexOfStartKrwRate; i <= indexOfEndKrwRate; i++) {
            krwRate.append(currencyRates.charAt(i));
        }

        return Double.parseDouble(String.valueOf(krwRate).replace(',', '.'));
    }

    public static double toRubles(double value, String currencyRates) {
        return value * getRate(currencyRates);
    }

    public static double fromRubles(double value, String currencyRates) {
        return value / getRate(currencyRates);
    }
}
