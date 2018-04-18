package cassetete.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CMainMenu implements Initializable {
    public void close(ActionEvent actionEvent) {
        //Node n = (Node)actionEvent.getSource();
        Platform.exit();
    }


    public void play(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/vgrid.fxml"));


        Button b = (Button) actionEvent.getSource();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("../vues/vmodalSelectionItem.fxml"));

        Stage dialog=new Stage();
        Parent root = loader1.load();

        dialog.setTitle("Le kass tete");
        dialog.setScene(new Scene(root));
        dialog.initOwner(b.getScene().getWindow());
        dialog.initModality(Modality.APPLICATION_MODAL);
        CModalSelectItem cmsi = loader1.getController();
        System.out.println(cmsi);
        cmsi.setItems();
        dialog.showAndWait();
        if(cmsi.getPath() !=null){
        CGame cGame = new CGame(cmsi.getPath());
        loader.setController(cGame);
        Parent p = loader.load();
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene s = new Scene(p);
        cGame.set(s);
        app_stage.setScene(s);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

