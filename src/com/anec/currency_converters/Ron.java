package com.anec.currency_converters;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;

public class Ron {

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
            throw new ConnectException("Failed to get RON rate");
        }

        int index = content.indexOf("<CharCode>RON</CharCode>");

        int indexOfStartRonRate = index + 77;
        int indexOfEndRonRate = index + 83;

        StringBuilder ronRate = new StringBuilder();

        for (int i = indexOfStartRonRate; i <= indexOfEndRonRate; i++) {
        	ronRate.append(content.charAt(i));
        }

        return Double.parseDouble(String.valueOf(ronRate).replace(',', '.'));
    }

    public static double toRubles(double value) throws ConnectException {
        return value * getCurrencyRate();
    }

    public static double fromRubles(double value) throws ConnectException {
        return value / getCurrencyRate();
    }
}
