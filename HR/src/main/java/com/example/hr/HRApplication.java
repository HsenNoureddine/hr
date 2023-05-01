package com.example.hr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HRApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Session s = Session.getSession();
        s.setStrategy(new SalaryCostStrategy());
        FXMLLoader fxmlLoader = new FXMLLoader(HRApplication.class.getResource("view/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Login Form");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}