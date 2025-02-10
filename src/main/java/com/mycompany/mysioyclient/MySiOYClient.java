/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mysioyclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        client.getClientFrame().getBtnOtpravit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendMessage(client.getTimeName() + client.getClientFrame()
                        .getPoleMessage());
            }
        });
    }
}
