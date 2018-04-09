package cassetete;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("vues/vmainmenu.fxml"));
        Parent r = FXMLLoader.load(getClass().getResource("vues/vmainmenu.fxml"));

        root.lookup("");

        primaryStage.setTitle("Le kass tete");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
