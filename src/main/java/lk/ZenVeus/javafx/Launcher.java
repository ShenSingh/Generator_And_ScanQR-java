package lk.ZenVeus.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application
{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent rooNode = FXMLLoader.load(getClass().getResource("/View/MainPage.fxml"));

        Scene scene = new Scene(rooNode);

        stage.setScene(scene);
        stage.show();
    }
}
