/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mysioyclient;

/**
 *
 * @author Артем
 */
import java.awt.Color;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

    static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final ClientFrame clientFrame = new ClientFrame();
    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;
    private final AudioPlayer player = new AudioPlayer();
    private final Crypto crypto = new Crypto();

    public Client() {
    }

    public Client(String serverAddress, int serverPort) throws IOException {
        this.socket = new Socket(serverAddress, serverPort);
        this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
    }

    public String getTimeName() {
        return dateFormat.format(new Date()) + ": " + clientFrame.getPoleName().getText() + " -> ";
    }

    // Метод для проверки состояния соединения
    public boolean isConnectionAlive() {
        try {
            socket.sendUrgentData(0xFF); // Отправка "пинг"-байта
            return true;
        } catch (IOException e) {
            return false; // Соединение прервано
        }
    }

    public ClientFrame getClientFrame() {
        return clientFrame;
    }

    // Метод для отправки сообщений на сервер
    public void sendMessage(String message) {
        //закодировать сообщение
        message = crypto.getCryptoMessage(message);
        out.println(message);
    }

    // Метод для прослушивания сообщений от сервера
    public void listenToServer() {
        new Thread(() -> {
            String response;
            try {
                while ((response = in.readLine()) != null) {
                    //раскодировать сообщение
                    response = crypto.getDeCryptoMessage(response);
                    clientFrame.getPoleChat().append("\n" + response);
                    cursorPoleChatVNiz();
                    player.playAudio("razriv.wav");
                }
            } catch (IOException e) {
                clientFrame.getPoleChat().append("\nError reading from server: " + e.getMessage());
                cursorPoleChatVNiz();
            }
        }).start();
    }

    //перевод курсора вниз в поле чат
    public void cursorPoleChatVNiz() {
        clientFrame.getPoleChat().setCaretPosition(
                clientFrame.getPoleChat().getDocument().getLength());
    }

    public void connectToServer() {
        new Thread(() -> {
            while (true) {
                try {
                    if (socket == null || socket.isClosed()) {
                        connect();
                    }
                    if (!isConnectionAlive()) {
                        clientFrame.getPoleStatus().setText("Connection lost. Reconnecting...");
                        clientFrame.getPoleStatus().setBackground(new Color(255, 102, 102));
                        closeResources();
                        connect();
                    }
                    Thread.sleep(5000); // Проверка каждые 5 секунд
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }

    private void connect() {
        try {
            Client client = new Client(clientFrame.getPoleIP().getText(), 59867);
            client.listenToServer();
            //отправить имя
            client.sendMessage(clientFrame.getPoleName().getText());
            clientFrame.getPoleStatus().setText("Есть соединение...");
            clientFrame.getPoleStatus().setBackground(new Color(153, 255, 153));
        } catch (UnknownHostException e) {
            clientFrame.getPoleStatus().setText("Unknown host: " + clientFrame.getPoleIP().getText());
            clientFrame.getPoleStatus().setBackground(new Color(255, 102, 102));
        } catch (IOException e) {
            clientFrame.getPoleStatus().setText("Unable to connect to server: " + e.getMessage());
            clientFrame.getPoleStatus().setBackground(new Color(255, 102, 102));
        }
    }

    private void closeResources() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }

}
