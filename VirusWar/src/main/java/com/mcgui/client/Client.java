package com.mcgui.client;

import com.mcgui.ApplicationConfig;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private final static ApplicationConfig conf = ApplicationConfig.DEFAULT;
    public static void main(String args[]) {
        try(Socket socket = new Socket(conf.host(), conf.port())){
            System.out.println("Connected com.mcgui.server host: "+ conf.host()+":"+conf.port());
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