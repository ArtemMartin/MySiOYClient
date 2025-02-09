/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mysioyclient;

/**
 *
 * @author Артем
 */
import java.io.*;
import static java.lang.Thread.sleep;
import java.net.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void connectToServer() {
        String serverAddress = "localhost";
        int serverPort = 5252;

        while (true) {
            try {
                try (Socket socket = new Socket(serverAddress, serverPort);
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream())); BufferedReader stdIn = new BufferedReader(
                        new InputStreamReader(System.in))) {

                    System.out.println("Connected to server at " + serverAddress
                            + ":" + serverPort);

                    out.println("Шляпа");
                    
                    String userInput;
                    while ((userInput = stdIn.readLine()) != null) {
                        if (userInput.equalsIgnoreCase("exit")) {
                            break;
                        }
                        out.println(new Date().getTime() + "Шляпа: " + userInput);
                        System.out.println("Received from server: " + in.readLine());
                    }
                } catch (UnknownHostException e) {
                    System.err.println("Don't know about host " + serverAddress);
                } catch (IOException e) {
                    System.err.println("Couldn't get I/O for the connection to " + serverAddress);
                }
                sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
