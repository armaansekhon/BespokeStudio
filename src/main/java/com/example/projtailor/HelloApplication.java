package com.example.projtailor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//  FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("measure/Mview.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("WorkersCon/Worker-View.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("getD/get-View.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tabW/tab-View.fxml"));
//      FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("explore/Mexp-View.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("panell/Panel-View.fxml"));
     FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Landpage/Main-View.fxml"));
//       FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Dash/Dash-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}