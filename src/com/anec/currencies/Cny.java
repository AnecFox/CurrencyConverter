package com.anec.currencies;

public class Cny {

    private static double getRate(String currencyRates) {
        int index = currencyRates.indexOf("<CharCode>CNY</CharCode>");

        int indexOfStartCnyRate = index + 78;
        int indexOfEndCnyRate = index + 84;

        StringBuilder cnyRate = new StringBuilder();

        for (int i = indexOfStartCnyRate; i <= indexOfEndCnyRate; i++) {
            cnyRate.append(currencyRates.charAt(i));
        }

        return Double.parseDouble(String.valueOf(cnyRate).replace(',', '.'));
    }

    public static double toRubles(double value, String currencyRates) {
        return value * getRate(currencyRates);
    }

    public static double fromRubles(double value, String currencyRates) {
        return value / getRate(currencyRates);
    }
}
