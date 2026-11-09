package com.mcgui.server;

import com.mcgui.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread{
    private final DataInputStream in;
    private final DataOutputStream out;
    private final Socket clientSocket;
    private final ServerEngine serverEngine;

    public ClientHandler(Socket aClientSocket, ServerEngine engine) throws IOException {
        clientSocket = aClientSocket;
        serverEngine = engine;
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());
        this.start();
    }

    @Override
    public void run(){
        try{
            String msg;
            while(!(msg=in.readUTF()).isEmpty()){
                serverEngine.processCommand(this, msg);
            }
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("ERROR: "+e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public void send(String msg) throws IOException {
        out.writeUTF(msg);
    }
}
