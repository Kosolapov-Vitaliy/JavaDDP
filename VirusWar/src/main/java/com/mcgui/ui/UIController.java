package com.mcgui.ui;

import com.google.gson.Gson;
import com.mcgui.ApplicationConfig;
import com.mcgui.CellType;
import com.mcgui.Message;
import com.mcgui.MessageType;
import com.mcgui.client.NetClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.Optional;

public class UIController {

    private final static ApplicationConfig conf = ApplicationConfig.DEFAULT;
    private String playerName;
    private boolean isGoesFirst = true;
    private NetClient client;
    private static final Gson gson = new Gson();

    @FXML
    private TilePane tilePane;
    @FXML
    private Button startButton;
    @FXML
    private TextFlow playerInfo;
    private final CellType[][] board = new CellType[conf.boardSize()][conf.boardSize()];
    private final StackPane[][] symbolLayers = new StackPane[conf.boardSize()][conf.boardSize()];


    @FXML
    public void initialize(){
        client = new NetClient(this);
        client.start();
    }

    public void ConnectionSuccess() throws IOException {
        setPaneParam();
        double maxCellSize = (tilePane.getMaxHeight()- conf.boardSize()*2)/ conf.boardSize();
        for(int i=0; i< conf.boardSize()* conf.boardSize();i++){
            int row = i/ conf.boardSize();
            int col = i%conf.boardSize();
            board[row][col]=CellType.EMPTY;
            Rectangle cell = new Rectangle(maxCellSize-2,maxCellSize-2);
            cell.setFill(Color.WHITE);
            cell.setStroke(Color.BLACK);

            StackPane symbolLayer = new StackPane();
            symbolLayer.setMouseTransparent(true);

            StackPane container = new StackPane(cell, symbolLayer);

            symbolLayers[row][col]=symbolLayer;

            container.setOnMouseClicked(event-> {
                cellHandler(row, col);
                renderCell(row, col);
            });

            tilePane.getChildren().add(container);
        }
        AskName("Введите ваше имя:");
    }

    public void ConnectionError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("ERROR");
        alert.setContentText("Сервер выключен");
        alert.showAndWait();

        Platform.exit();
    }



    @FXML
    private void onStartGame(){

    }

    private void cellHandler(int row, int col){
        switch (board[row][col]){
            case EMPTY -> board[row][col]=CellType.CROSSLIFE;
            case CROSSLIFE -> {
                if(isGoesFirst){
                    System.out.println("cannot do");
                }else{
                    board[row][col]=CellType.CROSSDIE;
                }
            }
            case CIRCLELIFE -> {
                if (isGoesFirst) {
                    board[row][col] = CellType.CROSSDIE;
                } else {
                    System.out.println("cannot do");
                }
            }
            case CIRCLEDIE, CROSSDIE -> System.out.println("cannot do");
        }
    }

    private void renderCell(int row, int col){
        StackPane symbolLayer = symbolLayers[row][col];
        double sizeCell = symbolLayer.getHeight();
        symbolLayer.getChildren().clear();
        FigureService figureService = new FigureService();
        Node symbol = switch (board[row][col]){
            case EMPTY -> null;
            case CROSSLIFE -> figureService.createCross(sizeCell);
            case CIRCLELIFE -> figureService.createCircle(sizeCell);
            case CROSSDIE -> figureService.createCircleAndCross(sizeCell);
            case CIRCLEDIE -> figureService.createShadedCircle(sizeCell);
        };
        symbolLayer.getChildren().add(symbol);
    }


    private void setPaneParam(){
        tilePane.setPrefColumns(conf.boardSize());
        tilePane.setPrefRows(conf.boardSize());
        tilePane.setPadding(new Insets(0,0,0,0));
        tilePane.setHgap(2);
        tilePane.setVgap(2);
    }

    private void AskName(String headerText) throws IOException {
        TextInputDialog dialog =new TextInputDialog("Player");
        dialog.setTitle("Вход в игру");
        dialog.setHeaderText(headerText);
        Optional<String> name =dialog.showAndWait();
        if(name.isEmpty()){
            Platform.exit();
            return;
        }
        String finalName = name.get().trim();
        if (finalName.isEmpty()){
            AskName(headerText);
            return;
        }
        Message msg = new Message(MessageType.CONNECT);
        msg.setPlayerName(finalName);
        client.send(gson.toJson(msg));

    }

    public void update(Message msg){
        if(msg==null||msg.getMsgType()==null){
            return;
        }
        switch(msg.getMsgType()){
            case ACCEPT_CONNECT -> {
                playerName = msg.getPlayerName();
                Platform.runLater(()->{
                    Text nameText = new Text("Имя игрока: "+ playerName);
                    playerInfo.getChildren().add(nameText);
                });

            }
            case ERROR_CONNECT -> {
                System.out.println("Error");
                Platform.runLater(()->{
                    try {
                        AskName("Имя занято, введите другое:");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

        }

    }

    public void Shutdown() {

    }
}
