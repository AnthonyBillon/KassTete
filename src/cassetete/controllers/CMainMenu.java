package cassetete.controllers;

import cassetete.models.MSymbol;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CMainMenu implements Initializable {
    public void close(ActionEvent actionEvent) {

    }


    public void play(ActionEvent actionEvent) throws IOException {
        //MGame m = new MGame(5, null);
        System.out.println("you clicked ! ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/vgrid.fxml"));
        ArrayList<MSymbol> l = new ArrayList<>();
        l.add(new MSymbol(2, 3, 3));
        l.add(new MSymbol(2, 0, 1));
        l.add(new MSymbol(3, 4, 2));
        l.add(new MSymbol(3, 0, 4));

        CGame cGame = new CGame(5, l);
        loader.setController(cGame);
        Parent p = loader.load();
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        cGame.set();
        app_stage.setScene(new Scene(p));



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
