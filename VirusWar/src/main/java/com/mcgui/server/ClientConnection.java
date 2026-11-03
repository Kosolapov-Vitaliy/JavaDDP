package com.mcgui.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection extends Thread{
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public ClientConnection(Socket cSocket){
        try{
            clientSocket = cSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        }catch (IOException e){
            System.out.println("Client get connection"+e.getMessage());
        }
    }

    public void run(){
        try {
            String data = in.readUTF();
            System.out.println("received: " + data);
            out.writeUTF(data);
            clientSocket.close();
        }
        catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("readline: " +e.getMessage());
        }
    }
}
