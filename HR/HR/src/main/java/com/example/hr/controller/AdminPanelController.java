package com.example.hr.controller;

import com.example.hr.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminPanelController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;

    @FXML
    void assign(ActionEvent event) {
        loadPage("assign",event);
    }

    @FXML
    void checkTasks(ActionEvent event) {
        loadPage("checkTasks",event);
    }

    @FXML
    void costs(ActionEvent event) {
        loadPage("costs",event);
    }

    @FXML
    void hire(ActionEvent event) {
        loadPage("hire",event);
    }

    @FXML
    void logout(ActionEvent event) { loadPage("login-view",event);}

    @FXML
    void salary(ActionEvent event) {
        loadPage("salary",event);
    }
    private void loadPage(String page,ActionEvent event)
    {

        if(page.compareTo("login-view") == 0)
        {
            URL url = null;
            Parent root = null;
            try{
                url = new File("src/main/resources/com/example/hr/view/"+page+".fxml").toURI().toURL();
                root = FXMLLoader.load(url);
            }
            catch(Exception e){}

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        URL url = null;
        try {
            url = new File("src/main/resources/com/example/hr/view/"+page+".fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        Parent root = null;

        try {
            root = FXMLLoader.load(url);
        }
        catch(IOException exception){}
        bp.setCenter(root);
    }

}
