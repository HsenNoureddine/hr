package com.example.hr.controller;

import com.example.hr.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminPanelController {

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;

    @FXML
    void assign(ActionEvent event) {
        loadPage("assign");
    }

    @FXML
    void checkTasks(ActionEvent event) {
        loadPage("checkTasks");
    }

    @FXML
    void costs(ActionEvent event) {
        loadPage("costs");
    }

    @FXML
    void hire(ActionEvent event) {
        loadPage("hire");
    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void salary(ActionEvent event) {
        loadPage("salary");
    }
    private void loadPage(String page)
    {
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
