package com.campus.campusconnect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    com.campus.campusconnect.session.UserSession s = new com.campus.campusconnect.session.UserSession();

    @Override
    public void start(Stage stage) throws IOException {

        if (s.isLoggedIn()) {

            scene = new Scene(loadFXML("home"));

        } else {
            scene = new Scene(loadFXML("auth"));

        }

        stage.setTitle("Campus Connect");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
