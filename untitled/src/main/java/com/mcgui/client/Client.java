package com.mcgui.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private final static int port = 8080;
    private final static String host ="localhost";
    public static void main(String args[]) {
        try(Socket socket = new Socket(host, port)){
            System.out.println("Connected server host: "+ host+":"+port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        }
        catch (UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        }

    }
}
