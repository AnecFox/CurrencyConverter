package com.anec.currencies;

public class Ron {

    private static double getRate(String currencyRates) {
        int index = currencyRates.indexOf("<CharCode>RON</CharCode>");

        int indexOfStartRonRate = index + 77;
        int indexOfEndRonRate = index + 83;

        StringBuilder ronRate = new StringBuilder();

        for (int i = indexOfStartRonRate; i <= indexOfEndRonRate; i++) {
            ronRate.append(currencyRates.charAt(i));
        }

        return Double.parseDouble(String.valueOf(ronRate).replace(',', '.'));
    }

    public static double toRubles(double value, String currencyRates) {
        return value * getRate(currencyRates);
    }

    public static double fromRubles(double value, String currencyRates) {
        return value / getRate(currencyRates);
    }
}
