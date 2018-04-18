package cassetete.controllers;

import cassetete.models.InvalidLevelException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
        dialog.getIcons().add(new Image("file:assets/icons/choose.png"));

        dialog.setTitle("Le kass tete");
        dialog.setScene(new Scene(root));
        dialog.initOwner(b.getScene().getWindow());
        dialog.initModality(Modality.APPLICATION_MODAL);
        CModalSelectItem cmsi = loader1.getController();
        System.out.println(cmsi);
        cmsi.setItems();
        dialog.showAndWait();
        if(cmsi.getPath() !=null){
            CGame cGame = null;
            try {
                cGame = new CGame(cmsi.getPath());

            loader.setController(cGame);
        Parent p = loader.load();
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene s = new Scene(p);
        cGame.set(s);
        app_stage.setScene(s);
            } catch (InvalidLevelException e) {
                invalidMap(e);
            }
        }
    }

    static void invalidMap(InvalidLevelException e) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        Stage s = (Stage)alert.getDialogPane().getScene().getWindow();
        s.getIcons().add(new Image("file:assets/icons/cass.png"));
        alert.setTitle("Erreur lors du chargement du niveau");
        alert.setHeaderText("Le niveau selectionné n'est pas valide");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

