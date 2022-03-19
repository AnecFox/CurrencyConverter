package com.anec.currency_converters;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;

public class Eur {

    private static double getCurrencyRate() throws ConnectException {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp");
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            throw new ConnectException("Failed to get EUR rate");
        }

        int index = content.indexOf("<CharCode>EUR</CharCode>");

        int indexOfStartEurRate = index + 68;
        int indexOfEndEurRate = index + 74;

        StringBuilder eurRate = new StringBuilder();

        for (int i = indexOfStartEurRate; i <= indexOfEndEurRate; i++) {
            eurRate.append(content.charAt(i));
        }

        return Double.parseDouble(String.valueOf(eurRate).replace(',', '.'));
    }

    public static double toRubles(double value) throws ConnectException {
        return value * getCurrencyRate();
    }

    public static double fromRubles(double value) throws ConnectException {
        return value / getCurrencyRate();
    }
}
