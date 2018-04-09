package cassetete.controllers;

import cassetete.models.MGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CMainMenu implements Initializable {
    public void close(ActionEvent actionEvent) {

    }


    public void play(ActionEvent actionEvent) throws IOException {
        //MGame m = new MGame(5, null);
        System.out.println("you clicked ! ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vues/vmainmenu.fxml"));

        CGame cGame = new CGame(new MGame());
        loader.setController(cGame);
        Parent p = loader.

        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
