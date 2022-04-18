package com.anec.currency_converters;

public class Bgn {

    private static double getRate(String currencyRates) {
        int index = currencyRates.indexOf("<CharCode>BGN</CharCode>");

        int indexOfStartBgnRate = index + 78;
        int indexOfEndBgnRate = index + 84;

        StringBuilder bgnRate = new StringBuilder();

        for (int i = indexOfStartBgnRate; i <= indexOfEndBgnRate; i++) {
            bgnRate.append(currencyRates.charAt(i));
        }

        return Double.parseDouble(String.valueOf(bgnRate).replace(',', '.'));
    }

    public static double toRubles(double value, String currencyRates) {
        return value * getRate(currencyRates);
    }

    public static double fromRubles(double value, String currencyRates) {
        return value / getRate(currencyRates);
    }
}
