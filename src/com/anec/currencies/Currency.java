package com.anec.currencies;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

public class Currency {

    public static String getCurrencyRates() throws ConnectException {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp");
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "windows-1251"));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            throw new ConnectException("Failed to get currency rates");
        }
        return content.toString();
    }

    public static boolean isCurrencySelected(JComboBox<String> comboBox, String currency) {
        return Objects.requireNonNull(comboBox.getSelectedItem()).toString().contains(currency);
    }
}
