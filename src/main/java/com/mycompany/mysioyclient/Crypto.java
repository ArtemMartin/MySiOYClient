/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mysioyclient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static  org.apache.commons.lang3.ArrayUtils.shuffle;

/**
 * @author user
 */
public class Crypto {

    private static final char[] massCrypto = new char[]{'L', '!', 'я', 'b', 'O', 'G', 'б', '7', 'c', ',', 'д',
            'Е', 'Р', '?', 'Н', ' ', 'Q', 'Ф', 'l', 'd', 'л', 'k', 'E', 'a', 'З', 'ч', 'K', '1', 'ш', 'Ш',
            'B', 'Э', 'ь', 'Л', '.', 'R', 'В', 'S', 'V', 'Ы', 'т', 'D', '№', '$', 'ж', 'm', '-', '2', 'И',
            '=', 's', 'ц', 'Г', 'Ь', '0', 'U', 'w', 'f', 'э', 'I', 'к', 'у', 'ё', '\n', 'z', 'м', '/', 'М',
            'п', '@', 'Й', 'J', '#', 'W', '4', '6', 'o', 'r', 'с', 'X', 'Т', ';', 'о', 'й', 'Щ', 'Y', 'Ъ',
            'j', 'г', 'i', 'Я', 'g', 'p', '&', 'x', 'С', 'Б', 'Х', '5', 'П', ':', '>', '%', 'A', 'ю', 'х',
            'H', ')', 'Д', '*', 'р', 'F', '+', 'К', 'н', '8', 't', '9', 'е', 'n', 'y', 'ъ', 'N', 'Ж', 'h',
            'v', 'и', 'P', 'u', 'А', 'Ю', 'У', 'M', 'e', 'q', 'а', 'Ё', 'T', 'О', '<', 'C', '3', 'з', 'Z',
            'ы', '(', 'ф', 'Ц', 'щ', 'Ч', 'в',
};
    private static final DateFormat dateFormat = new SimpleDateFormat("dd");

    public static void main1(String[] args) {
        //перемешать массив
        shuffle(massCrypto);
        for (char c : massCrypto) {
            System.out.print("'" + c + "'" + ", ");
        }
        System.out.println("\n");
        System.out.println("Mass 'massCrypto' length: " + massCrypto.length);


    }

    public static void main(String[] args) {
        char a;
        char b;
        for (int i = 0; i < massCrypto.length; i++) {
            a = massCrypto[i];
            for (int j = i + 1; j < massCrypto.length; j++) {
                b = massCrypto[j];
                if (a == b) {
                    System.out.println("Coincidence: " + b);
                }
            }
        }
        System.out.println("Mass 'massCrypto' length: " + massCrypto.length);
    }

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
