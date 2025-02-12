/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mysioyclient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author user
 */
public class Crypto {

    private static final char[] massCrypto = new char[]{'Д', 'Е', '$', 'д',
            'Ё', 'Ж', 'З', 'И', '%', 'Й', 'К', 'ш', 'щ', 'ъ', 'ы', 'е', 'ё', 'ж',
            'П', 'Р', 'С', 'Т', 'и', ',', 'й', 'к', 'л', 'Ы', 'Л', 'М', 'Н', 'О',
            'У', '5', '&', '6', '7', '(', '8', 'Ч', 'Ш', 'Щ', 'Ъ', '-', 'Ю', '\n',
            'Ц', '4', ';', 'U', 'V', 'W', 'X', 'Y', 'Z', 'p', 'q', 'r', 's', '=',
            'а', ' ', 'б', 'в', 'г', 'з', '@', 'м', 'н', 'о', 'п', 'В', 'Г', 'Э', '/',
            'р', 'с', 'т', '*', 'у', 'ф', 'х', 'ц', 'ч', '#', 'ь', 'э', 'ю', 'я', ')',
            '1', '2', '3', '.', '9', '0', ':', 'w', '>', 'x', 'y', 'z', 't', 'u', 'v',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', '<', 'Ь', 'А', 'Б', 'Ф', '!', 'Я', 'Х', '№',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o'
    };
    private static final DateFormat dateFormat = new SimpleDateFormat("dd");
    


    //разкодировать сообщение
    public static String getDeCryptoMessage(String message) {
        int key = Integer.parseInt(dateFormat.format(new Date())) + 9;
        String deCryptoMessage = "";
        char[] charMassMessage = message.toCharArray();
        char c;
        int pribavka;
        for (int i = 0; i < charMassMessage.length; i++) {
            c = charMassMessage[i];
            for (int j = 0; j < massCrypto.length; j++) {
                if (c == massCrypto[j]) {
                    if (j - key < 0) {
                        pribavka = Math.abs(j - key);
                        deCryptoMessage += massCrypto[massCrypto.length - pribavka];
                    } else {
                        deCryptoMessage += massCrypto[j - key];
                    }
                }
            }
        }
        return deCryptoMessage;
    }

    //закодировать сообщение
    public static String getCryptoMessage(String message) {
        int key = Integer.parseInt(dateFormat.format(new Date())) + 9;
        String cryptoMessage = "";
        char[] charMassMessage = message.toCharArray();
        char c;
        int pribavka;
        for (int i = 0; i < charMassMessage.length; i++) {
            c = charMassMessage[i];
            for (int j = 0; j < massCrypto.length; j++) {
                if (c == massCrypto[j]) {
                    if (j + key > massCrypto.length - 1) {
                        pribavka = j + key - (massCrypto.length);
                        cryptoMessage += massCrypto[pribavka];
                    } else {
                        cryptoMessage += massCrypto[j + key];
                    }
                }
            }
        }
        return cryptoMessage;
    }
}
