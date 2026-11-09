package com.mcgui.server;

import com.mcgui.ApplicationConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final static int port = ApplicationConfig.DEFAULT.port();

    public static void main(String[] args){
        try (ServerSocket server = new ServerSocket(port)){
            ServerEngine engine= new ServerEngine();
            System.out.println("Start server");
            while(true){
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client, engine);
            }

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
