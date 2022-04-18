package com.anec;

import com.anec.currency_converters.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

public class MainWindow extends JFrame {

    private final static String VERSION = "0.1.5";

    private final JTextField textFieldValue = new JTextField();
    private final JTextField textFieldResult = new JTextField();

    private final JButton buttonConvert = new JButton("Перевести");
    private final JButton buttonAbout = new JButton("О программе");

    public MainWindow() {
        initialize();
    }

    private void initialize() {
        setTitle("CurrencyConverter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(512, 140);
        setMinimumSize(new Dimension(256, 128));
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getClassLoader()
                .getResource("com/anec/icon/icon.png")));

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 5, 5));

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        textFieldValue.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buttonConvert.doClick();
                }
            }
        });
        container.add(textFieldValue);

        textFieldResult.setEditable(false);
        container.add(textFieldResult);

        String[] currencies = new String[]{
                "Болгарский лев (BGN)", "Доллар США (USD)", "Евро (EUR)", "Казахский тенге (KZT)",
                "Китайский юань (CNY)", "Румынский лей (RON)", "Российский рубль (RUB)",
                "Украинская гривна (UAH)", "Южнокорейская вона (KRW)", "Японская иена (JPY)",
        };

        JComboBox<String> comboBoxFirstCurrency = new JComboBox<>(currencies);
        comboBoxFirstCurrency.setSelectedIndex(1);

        JComboBox<String> comboBoxSecondCurrency = new JComboBox<>(currencies);
        comboBoxSecondCurrency.setSelectedIndex(6);

        container.add(comboBoxFirstCurrency);
        container.add(comboBoxSecondCurrency);

        String currencyRates = "";

        try {
            currencyRates = getCurrencyRates();
        } catch (ConnectException e) {
            JOptionPane.showMessageDialog(null, "Возникла ошибка при получении курса валют",
                    getTitle(), JOptionPane.ERROR_MESSAGE);
        }

        String finalCurrencyRates = currencyRates;
        buttonConvert.addActionListener(e -> {
            if (finalCurrencyRates.equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Проверьте соединение с интернетом, перезапустите программу и конвертация будет работать :)",
                        this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
            } else {
                String value = textFieldValue.getText().contains(",") ?
                        textFieldValue.getText().trim().replace(',', '.') :
                        textFieldValue.getText().trim();

                double result = 0;

                try {
                    String firstSelectedCurrency = "";
                    String secondSelectedCurrency = "";

                    for (String currency : currencies) {
                        if (comboBoxFirstCurrency.getSelectedItem() == currency) {
                            firstSelectedCurrency = currency;
                        }
                        if (comboBoxSecondCurrency.getSelectedItem() == currency) {
                            secondSelectedCurrency = currency;
                        }
                    }

                    if (firstSelectedCurrency.equals(currencies[0])) {
                        result = Bgn.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (firstSelectedCurrency.equals(currencies[1])) {
                        result = Usd.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (firstSelectedCurrency.equals(currencies[2])) {
                        result = Eur.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (firstSelectedCurrency.equals(currencies[3])) {
                        result = Kzt.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (firstSelectedCurrency.equals(currencies[4])) {
                        result = Cny.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (firstSelectedCurrency.equals(currencies[5])) {
                        result = Ron.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (firstSelectedCurrency.equals(currencies[7])) {
                        result = Uah.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (firstSelectedCurrency.equals(currencies[8])) {
                        result = Krw.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (firstSelectedCurrency.equals(currencies[9])) {
                        result = Jpy.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    }

                    if (secondSelectedCurrency.equals(currencies[0])) {
                        result = Bgn.fromRubles(result, finalCurrencyRates);
                    } else if (secondSelectedCurrency.equals(currencies[1])) {
                        result = Usd.fromRubles(result, finalCurrencyRates);
                    } else if (secondSelectedCurrency.equals(currencies[2])) {
                        result = Eur.fromRubles(result, finalCurrencyRates);
                    } else if (secondSelectedCurrency.equals(currencies[3])) {
                        result = Kzt.fromRubles(result, finalCurrencyRates);
                    } else if (secondSelectedCurrency.equals(currencies[4])) {
                        result = Cny.fromRubles(result, finalCurrencyRates);
                    } else if (secondSelectedCurrency.equals(currencies[5])) {
                        result = Ron.fromRubles(result, finalCurrencyRates);
                    } else if (secondSelectedCurrency.equals(currencies[7])) {
                        result = Uah.fromRubles(result, finalCurrencyRates);
                    } else if (secondSelectedCurrency.equals(currencies[8])) {
                        result = Krw.fromRubles(result, finalCurrencyRates);
                    } else if (secondSelectedCurrency.equals(currencies[9])) {
                        result = Jpy.fromRubles(result, finalCurrencyRates);
                    }

                    if (secondSelectedCurrency.equals(currencies[6])) {
                        if (firstSelectedCurrency.equals(currencies[0])) {
                            result = Bgn.toRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (firstSelectedCurrency.equals(currencies[1])) {
                            result = Usd.toRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (firstSelectedCurrency.equals(currencies[2])) {
                            result = Eur.toRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (firstSelectedCurrency.equals(currencies[3])) {
                            result = Kzt.toRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (firstSelectedCurrency.equals(currencies[4])) {
                            result = Cny.toRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (firstSelectedCurrency.equals(currencies[5])) {
                            result = Ron.toRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (firstSelectedCurrency.equals(currencies[7])) {
                            result = Uah.toRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (firstSelectedCurrency.equals(currencies[8])) {
                            result = Krw.toRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (firstSelectedCurrency.equals(currencies[9])) {
                            result = Jpy.toRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (firstSelectedCurrency.equals(currencies[6])) {
                            result = Double.parseDouble(value);
                        }
                    }

                    if (firstSelectedCurrency.equals(currencies[6])) {
                        if (secondSelectedCurrency.equals(currencies[0])) {
                            result = Bgn.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (secondSelectedCurrency.equals(currencies[1])) {
                            result = Usd.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (secondSelectedCurrency.equals(currencies[2])) {
                            result = Eur.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (secondSelectedCurrency.equals(currencies[3])) {
                            result = Kzt.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (secondSelectedCurrency.equals(currencies[4])) {
                            result = Cny.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (secondSelectedCurrency.equals(currencies[5])) {
                            result = Ron.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (secondSelectedCurrency.equals(currencies[7])) {
                            result = Uah.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (secondSelectedCurrency.equals(currencies[8])) {
                            result = Krw.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (secondSelectedCurrency.equals(currencies[9])) {
                            result = Jpy.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (secondSelectedCurrency.equals(currencies[6])) {
                            result = Double.parseDouble(value);
                        }
                    }
                } catch (NumberFormatException ex) {
                    if (textFieldValue.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Значение не введено", getTitle(),
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Значение введено неправильно",
                                getTitle(), JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Произошла ошибка: " +
                            Arrays.toString(ex.getStackTrace()), getTitle(), JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String stringResult = textFieldValue.getText().contains(".") ? String.valueOf(result) :
                        String.valueOf(result).replace('.', ',');

                textFieldResult.setText(stringResult.endsWith(",0") || stringResult.endsWith(".0") ?
                        stringResult.replace((stringResult.contains(".") ? ".0" : ",0"), "") : stringResult);
            }
        });
        container.add(buttonConvert);

        buttonAbout.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "Эта программа предназначена для конвертации валюты.\n\n" +
                        "                                        Версия: " + VERSION + "\n\n" +
                        "                                       Created by Anec", "О программе",
                JOptionPane.PLAIN_MESSAGE));
        container.add(buttonAbout);

        for (Component c : this.getComponents()) {
            SwingUtilities.updateComponentTreeUI(c);
        }
    }

    private String getCurrencyRates() throws ConnectException {
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
            throw new ConnectException("Failed to get currency rates");
        }
        return content.toString();
    }
}
