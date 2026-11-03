package com.mcgui.server;

import com.mcgui.ApplicationConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        try{
            final ApplicationConfig conf = ApplicationConfig.DEFAULT;
            ServerSocket socket = new ServerSocket(conf.port());
            while(true){
                Socket clientSocket = socket.accept();
                ClientConnection clientConnection = new ClientConnection(clientSocket);
            }

        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    }
}
