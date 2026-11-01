package com.mcgui.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        try{
            int port = 8080;
            ServerSocket socket = new ServerSocket(port);
            while(true){
                Socket clientSocket = socket.accept();
                ClientConnection clientConnection = new ClientConnection(clientSocket);
            }

        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    }
}
