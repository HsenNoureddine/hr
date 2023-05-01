package com.example.hr.controller;

import com.example.hr.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterController {

    private Stage stage;
    private Parent root;
    private Scene scene;
    @FXML
    private RadioButton admin;

    @FXML
    private RadioButton employee;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private Button login;

    @FXML
    private RadioButton manager;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phone;

    @FXML
    private ToggleGroup position;

    @FXML
    private Button submit;

    @FXML
    private TextField username;

    @FXML
    void login(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/com/example/hr/view/login-view.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void submit(ActionEvent event) {
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();
        int type = 0;
        if(manager.isSelected()) type = 1;
        else if(admin.isSelected()) type = 2;
        String insertQuery = "INSERT INTO `user`(`firstName`, `lastName`, `userName`, `password`, `phone`, `salary`, `type`) VALUES ('"+firstname.getText()+"','"+lastname.getText()+"','"+username.getText()+"','"+password.getText()+"','"+phone.getText()+"',0,"+type+")";

        try{
            Statement s = connectDB.createStatement();
            s.executeUpdate(insertQuery);
            login(event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
