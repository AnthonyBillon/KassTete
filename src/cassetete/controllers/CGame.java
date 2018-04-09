package cassetete.controllers;

import cassetete.models.MGame;
import cassetete.models.MSymbol;
import cassetete.vues.VGridManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CGame implements Initializable {
    private MGame mGame;
    private VGridManager gridManager;

    @FXML
    VBox mainPane;

    CGame(int i) {
        MSymbol mSymbol = new MSymbol(2, 0, 2);
        MSymbol mSymbol12 = new MSymbol(3, 2, 2);
        MSymbol mSymbol13 = new MSymbol(2, 4, 2);
        MSymbol mSymbol14 = new MSymbol(3, 4, 0);
        ArrayList<MSymbol> l = new ArrayList<>();
        l.add(mSymbol);
        l.add(mSymbol12);
        l.add(mSymbol13);
        l.add(mSymbol14);


        mGame = new MGame(5, l);
        gridManager = new VGridManager(mGame);

    }

    public void setControllers() {
        for (int i = 0; i < gridManager.getCanvasHandlers().length; i++) {
            for (int j = 0; j < gridManager.getCanvasHandlers()[i].length; j++) {
                Canvas c =  gridManager.getCanvasHandlers()[i][j].getCanvas();
                c.addEventFilter(MouseEvent.DRAG_DETECTED , new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        c.getScene().startFullDrag();
                        System.out.println("Started with : " + mouseEvent.getSource().getClass());
                    }
                });

                c.setOnMouseDragEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("aaaaa");
                        System.out.println(event.getSource().getClass() + " Object : " + event.getSource());

                    }
                });
            }
        }
    }




    public void setttt() {

        mainPane.getChildren().add(gridManager.getGridPane());
        setControllers();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}