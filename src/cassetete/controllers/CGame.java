package cassetete.controllers;

import cassetete.models.MGame;
import cassetete.models.MSymbol;
import cassetete.vues.VGridManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class CGame implements Initializable {
    private MGame mGame;
    private VGridManager gridManager;
    private Canvas current;
    private Scene s;

    @FXML
    private
    VBox mainPane;

    @FXML
    private
    Label timerLabel;

    @FXML
    private
    MenuItem annulerMenu;

    @FXML
    private MenuItem newGame;
    @FXML
    private MenuItem close;

    CGame(int size, ArrayList<MSymbol> l) {
        mGame = new MGame(size, l);
        gridManager = new VGridManager(mGame);
        mGame.start();
    }

    private void setControllers() {
        for (int i = 0; i < gridManager.getCanvasHandlers().length; i++) {
            for (int j = 0; j < gridManager.getCanvasHandlers()[i].length; j++) {
                Canvas c = gridManager.getCanvasHandlers()[i][j].getCanvas();
                c.setUserData(new Point(j, i));
                c.addEventFilter(MouseEvent.DRAG_DETECTED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        mGame.beginLine((Point) c.getUserData());
                        c.getScene().startFullDrag();
                        current = c;
                    }
                });

                c.setOnMouseDragEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (c == current) return;
                        mGame.writeLinePart((Point) c.getUserData());
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
                System.out.println(mainPane.getScene());
            }
        });

        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/vgrid.fxml"));
                ArrayList<MSymbol> l = new ArrayList<>();
                l.add(new MSymbol(2, 0, 2));
                l.add(new MSymbol(2, 2, 4));
                l.add(new MSymbol(3, 4, 4));
                l.add(new MSymbol(3, 3, 4));

                CGame cGame = new CGame(5, l);
                loader.setController(cGame);
                Parent p = null;
                try {
                    p = loader.load();

                    Stage app_stage = (Stage) s.getWindow();
                    Scene sc = new Scene(p);
                    cGame.set(sc);
                    app_stage.setScene(sc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
    }


    void set(Scene scene) {

        mainPane.getChildren().add(gridManager.getGridPane());
        System.out.println(s);
        setControllers();

        mGame.getmTimer().addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                timerLabel.setText("Temps : " + mGame.getmTimer().getFormatedTime());
            }
        });
        this.s = scene;
        final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);

        scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (keyComb1.match(event)) mGame.back();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
