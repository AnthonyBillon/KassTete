package cassetete.controllers;

import cassetete.models.MGame;
import cassetete.models.MSymbol;
import cassetete.vues.VGridManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class CGame implements Initializable {
    private MGame mGame;
    private VGridManager gridManager;
    private Canvas current;

    @FXML
    private
    VBox mainPane;

    @FXML
    private
    Label timerLabel;

    @FXML
    private
    MenuItem annulerMenu;

    CGame(int size, ArrayList<MSymbol> l) {
        mGame = new MGame(size, l);
        gridManager = new VGridManager(mGame);
        mGame.start();

    }

    private void setControllers() {
        for (int i = 0; i < gridManager.getCanvasHandlers().length; i++) {
            for (int j = 0; j < gridManager.getCanvasHandlers()[i].length; j++) {
                Canvas c =  gridManager.getCanvasHandlers()[i][j].getCanvas();
                c.setUserData(new Point(j,i));
                c.addEventFilter(MouseEvent.DRAG_DETECTED , new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        mGame.beginLine((Point)c.getUserData());
                        c.getScene().startFullDrag();
                        current=c;
                    }
                });

                c.setOnMouseDragEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(c==current)return;
                        mGame.writeLinePart((Point)c.getUserData());
                    }
                });

                c.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
                    @Override
                    public void handle(MouseDragEvent event) {
                        mGame.endLine();
                    }
                });
            }
        }

        annulerMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mGame.back();
            }
        });
    }




    void set() {

        mainPane.getChildren().add(gridManager.getGridPane());
        setControllers();

        mGame.getmTimer().addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                timerLabel.setText( "Temps : " + mGame.getmTimer().getFormatedTime());
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
