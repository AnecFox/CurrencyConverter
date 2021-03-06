package com.anec;

import com.anec.currency_converters.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.ConnectException;
import java.util.Arrays;

public class MainWindow extends JFrame {

    private final static String VERSION = "0.1.4";

    private final JTextField textFieldValue = new JTextField();
    private final JTextField textFieldResult = new JTextField();

    private final JButton buttonConvert = new JButton("Перевести");
    private final JButton buttonAbout = new JButton("О программе");

    public MainWindow() {
        initialize();
    }

    private void initialize() {
        setTitle("CurrencyConverter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        buttonConvert.addActionListener(e -> {
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
                    result = Bgn.toRubles(Double.parseDouble(value));
                } else if (firstSelectedCurrency.equals(currencies[1])) {
                    result = Usd.toRubles(Double.parseDouble(value));
                } else if (firstSelectedCurrency.equals(currencies[2])) {
                    result = Eur.toRubles(Double.parseDouble(value));
                } else if (firstSelectedCurrency.equals(currencies[3])) {
                    result = Kzt.toRubles(Double.parseDouble(value));
                } else if (firstSelectedCurrency.equals(currencies[4])) {
                    result = Cny.toRubles(Double.parseDouble(value));
                } else if (firstSelectedCurrency.equals(currencies[5])) {
                    result = Ron.toRubles(Double.parseDouble(value));
                } else if (firstSelectedCurrency.equals(currencies[7])) {
                    result = Uah.toRubles(Double.parseDouble(value));
                } else if (firstSelectedCurrency.equals(currencies[8])) {
                    result = Krw.toRubles(Double.parseDouble(value));
                } else if (firstSelectedCurrency.equals(currencies[9])) {
                    result = Jpy.toRubles(Double.parseDouble(value));
                }

                if (secondSelectedCurrency.equals(currencies[0])) {
                    result = Bgn.fromRubles(result);
                } else if (secondSelectedCurrency.equals(currencies[1])) {
                    result = Usd.fromRubles(result);
                } else if (secondSelectedCurrency.equals(currencies[2])) {
                    result = Eur.fromRubles(result);
                } else if (secondSelectedCurrency.equals(currencies[3])) {
                    result = Kzt.fromRubles(result);
                } else if (secondSelectedCurrency.equals(currencies[4])) {
                    result = Cny.fromRubles(result);
                } else if (secondSelectedCurrency.equals(currencies[5])) {
                    result = Ron.fromRubles(result);
                } else if (secondSelectedCurrency.equals(currencies[7])) {
                    result = Uah.fromRubles(result);
                } else if (secondSelectedCurrency.equals(currencies[8])) {
                    result = Krw.fromRubles(result);
                } else if (secondSelectedCurrency.equals(currencies[9])) {
                    result = Jpy.fromRubles(result);
                }

                if (secondSelectedCurrency.equals(currencies[6])) {
                    if (firstSelectedCurrency.equals(currencies[0])) {
                        result = Bgn.toRubles(Double.parseDouble(value));
                    } else if (firstSelectedCurrency.equals(currencies[1])) {
                        result = Usd.toRubles(Double.parseDouble(value));
                    } else if (firstSelectedCurrency.equals(currencies[2])) {
                        result = Eur.toRubles(Double.parseDouble(value));
                    } else if (firstSelectedCurrency.equals(currencies[3])) {
                        result = Kzt.toRubles(Double.parseDouble(value));
                    } else if (firstSelectedCurrency.equals(currencies[4])) {
                        result = Cny.toRubles(Double.parseDouble(value));
                    } else if (firstSelectedCurrency.equals(currencies[5])) {
                        result = Ron.toRubles(Double.parseDouble(value));
                    } else if (firstSelectedCurrency.equals(currencies[7])) {
                        result = Uah.toRubles(Double.parseDouble(value));
                    } else if (firstSelectedCurrency.equals(currencies[8])) {
                        result = Krw.toRubles(Double.parseDouble(value));
                    } else if (firstSelectedCurrency.equals(currencies[9])) {
                        result = Jpy.toRubles(Double.parseDouble(value));
                    } else if (firstSelectedCurrency.equals(currencies[6])) {
                        result = Double.parseDouble(value);
                    }
                }

                if (firstSelectedCurrency.equals(currencies[6])) {
                    if (secondSelectedCurrency.equals(currencies[0])) {
                        result = Bgn.fromRubles(Double.parseDouble(value));
                    } else if (secondSelectedCurrency.equals(currencies[1])) {
                        result = Usd.fromRubles(Double.parseDouble(value));
                    } else if (secondSelectedCurrency.equals(currencies[2])) {
                        result = Eur.fromRubles(Double.parseDouble(value));
                    } else if (secondSelectedCurrency.equals(currencies[3])) {
                        result = Kzt.fromRubles(Double.parseDouble(value));
                    } else if (secondSelectedCurrency.equals(currencies[4])) {
                        result = Cny.fromRubles(Double.parseDouble(value));
                    } else if (secondSelectedCurrency.equals(currencies[5])) {
                        result = Ron.fromRubles(Double.parseDouble(value));
                    } else if (secondSelectedCurrency.equals(currencies[7])) {
                        result = Uah.fromRubles(Double.parseDouble(value));
                    } else if (secondSelectedCurrency.equals(currencies[8])) {
                        result = Krw.fromRubles(Double.parseDouble(value));
                    } else if (secondSelectedCurrency.equals(currencies[9])) {
                        result = Jpy.fromRubles(Double.parseDouble(value));
                    } else if (secondSelectedCurrency.equals(currencies[6])) {
                        result = Double.parseDouble(value);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Нужно вводить число", getTitle(),
                        JOptionPane.ERROR_MESSAGE);
                return;
            } catch (ConnectException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Возникла ошибка при получении курса валют",
                        getTitle(), JOptionPane.ERROR_MESSAGE);
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
