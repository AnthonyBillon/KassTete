package cassetete.controllers;

import cassetete.models.MGame;
import cassetete.models.MSymbol;
import cassetete.vues.VGridManager;
import cassetete.vues.canvasAssets.canvasAssetsShapes.CanvasAssetsShapeGenerical;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    CGame(String path) {
        mGame = new MGame(path);
        gridManager = new VGridManager(mGame);
        mGame.start();
    }

    private void setControllers() {
        for (int i = 0; i < gridManager.getCanvasHandlers().length; i++) {
            for (int j = 0; j < gridManager.getCanvasHandlers()[i].length; j++) {
                Canvas c = gridManager.getCanvasHandlers()[i][j].getCanvas();
                c.setUserData(new Point(j, i));
                c.addEventFilter(MouseEvent.DRAG_DETECTED, mouseEvent -> {
                    mGame.beginLine((Point) c.getUserData());
                    c.getScene().startFullDrag();
                    current = c;
                });

                c.setOnMouseDragEntered((EventHandler<MouseEvent>) event -> {
                    if (c == current) return;
                    mGame.writeLinePart((Point) c.getUserData());
                });

                c.setOnMouseDragReleased(event -> mGame.endLine());
            }
        }

        annulerMenu.setOnAction(event -> {
            mGame.back();
            System.out.println(mainPane.getScene());
        });

        newGame.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/vgrid.fxml"));
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../vues/vmodalSelectionItem.fxml"));

                Stage dialog = new Stage();
                Parent root = null;
                try {
                    root = loader1.load();


                    dialog.setTitle("Le kass tete");
                    dialog.setScene(new Scene(root));
                    dialog.initOwner(s.getWindow());
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    CModalSelectItem vmsi = loader1.getController();
                    System.out.println(vmsi);
                    vmsi.setItems();
                    dialog.showAndWait();


                    CGame cGame = new CGame(vmsi.getPath());


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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        close.setOnAction(event -> Platform.exit());
    }


    void set(Scene scene) {

        mainPane.getChildren().add(gridManager.getGridPane());
        System.out.println(s);
        setControllers();

        mGame.getmTimer().addObserver((o, arg) -> timerLabel.setText("Temps : " + mGame.getmTimer().getFormatedTime()));
        this.s = scene;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
