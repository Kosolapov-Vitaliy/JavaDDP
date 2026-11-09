package com.mcgui;

public class Message {
    private String playerName;
    private String errorMessage;
    private MessageType msgType;
    private int row;
    private int col;
    private CellType cellType;
    private boolean isPlayerGoesFirst;

    public Message(MessageType msgType){
        this.msgType=msgType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public MessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(MessageType msgType) {
        this.msgType = msgType;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public boolean isPlayerGoesFirst() {
        return isPlayerGoesFirst;
    }

    public void setPlayerGoesFirst(boolean playerGoesFirst) {
        isPlayerGoesFirst = playerGoesFirst;
    }
}
