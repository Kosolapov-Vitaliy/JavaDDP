package com.mcgui.client;

import com.google.gson.Gson;
import com.mcgui.ApplicationConfig;
import com.mcgui.Message;
import com.mcgui.ui.UIController;
import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetClient extends Thread {
    private final UIController ui;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;
    private static final ApplicationConfig conf = ApplicationConfig.DEFAULT;
    private static final Gson gson = new Gson();

    public NetClient(UIController ui){
        this.ui=ui;
    }

    @Override
    public void run(){
        try {
            clientSocket = new Socket(conf.host(), conf.port());
            Platform.runLater(()->{
                try{
                    ui.ConnectionSuccess();
                }
                catch(IOException e){
                    System.out.println("AskName Error: "+e.getMessage());
                }
            });
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            String msg;
            while(!(msg = in.readUTF()).isEmpty()){
                ui.update(gson.fromJson(msg, Message.class));
            }
        } catch (IOException e) {
            Platform.runLater(ui::ConnectionError);
        }
    }

    public void send(String msg) throws IOException {
        out.writeUTF(msg);

    }
}
