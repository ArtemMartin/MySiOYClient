/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mysioyclient;

import java.awt.event.ActionEvent;

/**
 *
 * @author Артем
 */
public class MySiOYClient {

    public static void main(String[] args) {
        Client client = new Client();
        client.getClientFrame().setVisible(true);
        //слушаем искать сервер
        client.getClientFrame().getBtnIskatServer().addActionListener((ActionEvent e) -> {
            client.connectToServer();
        });
        //отправить сообщениее
        client.getClientFrame().getBtnOtpravit().addActionListener((ActionEvent e) -> {
            client.sendMessage(client.getTimeName() + client.getClientFrame()
                    .getPoleMessage().getText());
            client.getClientFrame().getPoleMessage().setText("");         
        });
    }
}
