package com.anec;

import com.anec.currencies.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.ConnectException;
import java.util.Arrays;

public class MainWindow extends JFrame {

    private final static String VERSION = "0.1.7";

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
                "Азербайджанский манат (AZN)", "Армянский драм (AMD)", "Белорусский рубль (BYN)",
                "Болгарский лев (BGN)", "Доллар США (USD)", "Евро (EUR)", "Индийская рупия (INR)",
                "Казахский тенге (KZT)", "Китайский юань (CNY)", "Молдавский лей (MDL)", "Польский злотый (PLN)",
                "Румынский лей (RON)", "Российский рубль (RUB)", "Таджикский сомони (TJS)",
                "Узбекский сум (UZS)", "Украинская гривна (UAH)", "Южнокорейская вона (KRW)", "Японская иена (JPY)"
        };

        JComboBox<String> comboBoxFirstCurrency = new JComboBox<>(currencies);
        comboBoxFirstCurrency.setSelectedItem("Доллар США (USD)");

        JComboBox<String> comboBoxSecondCurrency = new JComboBox<>(currencies);
        comboBoxSecondCurrency.setSelectedItem("Российский рубль (RUB)");

        container.add(comboBoxFirstCurrency);
        container.add(comboBoxSecondCurrency);

        String currencyRates = "";

        try {
            currencyRates = Currency.getCurrencyRates();
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
                    if (Currency.isCurrencySelected(comboBoxFirstCurrency, "AZN")) {
                        result = Azn.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "AMD")) {
                        result = Amd.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "BYN")) {
                        result = Byn.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "BGN")) {
                        result = Bgn.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "USD")) {
                        result = Usd.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "EUR")) {
                        result = Eur.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "INR")) {
                        result = Inr.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "KZT")) {
                        result = Kzt.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "CNY")) {
                        result = Cny.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "MDL")) {
                        result = Mdl.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "PLN")) {
                        result = Pln.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "RON")) {
                        result = Ron.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "TJS")) {
                        result = Tjs.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "UZS")) {
                        result = Uzs.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "UAH")) {
                        result = Uah.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "KRW")) {
                        result = Krw.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxFirstCurrency, "JPY")) {
                        result = Jpy.toRubles(Double.parseDouble(value), finalCurrencyRates);
                    }

                    if (Currency.isCurrencySelected(comboBoxSecondCurrency, "AZN")) {
                        result = Azn.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "AMD")) {
                        result = Amd.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "BYN")) {
                        result = Byn.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "BGN")) {
                        result = Bgn.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "USD")) {
                        result = Usd.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "EUR")) {
                        result = Eur.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "INR")) {
                        result = Inr.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "KZT")) {
                        result = Kzt.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "CNY")) {
                        result = Cny.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "MDL")) {
                        result = Mdl.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "PLN")) {
                        result = Pln.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "RON")) {
                        result = Ron.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "TJS")) {
                        result = Tjs.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "UZS")) {
                        result = Uzs.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "UAH")) {
                        result = Uah.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "KRW")) {
                        result = Krw.fromRubles(result, finalCurrencyRates);
                    } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "JPY")) {
                        result = Jpy.fromRubles(result, finalCurrencyRates);
                    }

                    if (Currency.isCurrencySelected(comboBoxFirstCurrency, "RUB")) {
                        if (Currency.isCurrencySelected(comboBoxSecondCurrency, "AZN")) {
                            result = Azn.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "AMD")) {
                            result = Amd.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "BYN")) {
                            result = Byn.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "BGN")) {
                            result = Bgn.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "USD")) {
                            result = Usd.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "EUR")) {
                            result = Eur.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "INR")) {
                            result = Inr.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "KZT")) {
                            result = Kzt.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "CNY")) {
                            result = Cny.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "MDL")) {
                            result = Mdl.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "PLN")) {
                            result = Pln.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "RON")) {
                            result = Ron.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "TJS")) {
                            result = Tjs.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "UZS")) {
                            result = Uzs.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "UAH")) {
                            result = Uah.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "KRW")) {
                            result = Krw.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "JPY")) {
                            result = Jpy.fromRubles(Double.parseDouble(value), finalCurrencyRates);
                        } else if (Currency.isCurrencySelected(comboBoxSecondCurrency, "RUB")) {
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
}
