package campusconnect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primartStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        primartStage.setTitle("CAMPUS CONNECT");
        primartStage.setScene(new Scene(root, 400, 300));
        primartStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
