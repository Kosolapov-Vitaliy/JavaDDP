package com.mcgui.server;

import com.google.gson.Gson;
import com.mcgui.ApplicationConfig;
import com.mcgui.CellType;
import com.mcgui.Message;
import com.mcgui.MessageType;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerEngine {
    private static final ApplicationConfig conf = ApplicationConfig.DEFAULT;
    private final CellType[][] board = new CellType[conf.boardSize()][conf.boardSize()];
    private final Set<String> activePlayers = new ConcurrentSkipListSet<>();
    private final List<ClientHandler> conectedClients = new CopyOnWriteArrayList<>();
    private final Gson gson = new Gson();


    public ServerEngine(){
        for(int i=0; i< conf.boardSize();i++){
            for(int j=0; j<conf.boardSize();j++){
                board[i][j]=CellType.EMPTY;
            }
        }
    }

    public void processCommand(ClientHandler client, String json) throws IOException {
        Message msg = gson.fromJson(json, Message.class);
        if (msg==null||msg.getMsgType()==null) return;
        switch(msg.getMsgType()){
            case CONNECT -> connectHandler(client, msg.getPlayerName());
            case TURN -> turnHandler(msg.getRow(),msg.getCol(), msg.isPlayerGoesFirst());
        }
    }

    private void turnHandler(int row, int col, boolean isPlayerGoesFirst) {
        Message msg;

    }

    public void connectHandler (ClientHandler client, String pName) throws IOException {
        Message msg;
        if(!activePlayers.contains(pName)){
            activePlayers.add(pName);
            conectedClients.add(client);
            msg = new Message(MessageType.ACCEPT_CONNECT);
            msg.setPlayerName(pName);
            client.send(gson.toJson(msg));
        }
        else{
            msg = new Message(MessageType.ERROR_CONNECT);
            msg.setErrorMessage("Failed to join. The name is already taken.");
            client.send(gson.toJson(msg));
        }
    }


}
