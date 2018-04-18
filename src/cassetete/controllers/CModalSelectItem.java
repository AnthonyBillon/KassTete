package cassetete.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class CModalSelectItem implements Initializable {
    private static final String path = "assets/maps/";
    @FXML
    public ListView listOfMenu;

    private String p;



    public void setItems(){
        File folder = new File(path);
        if (folder.listFiles() != null) {
            ObservableList<String> obs = FXCollections.observableArrayList();
            for (int i = 0; i < folder.listFiles().length; i++) {

                obs.add(folder.listFiles()[i].getName());
            }

            listOfMenu.setItems(obs);

        }

    }

    public void accept(ActionEvent actionEvent) {
        if (listOfMenu.getSelectionModel().getSelectedItems().size()>0) {
            p = path + listOfMenu.getSelectionModel().getSelectedItems().get(0);
            Button b = (Button) actionEvent.getSource();
            b.getScene().getWindow().hide();

        }


    }

    public void cancel(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        b.getScene().getWindow().hide();
    }

    public String getPath() {
        return p;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
