package com.example.hr.controller;

import com.example.hr.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label label;

    @FXML
    private PasswordField password;

    @FXML
    private Button register;

    @FXML
    private Button submit;

    @FXML
    private TextField username;

    @FXML
    void register(ActionEvent event) throws IOException {
        loadPage("register-view",event);
    }
    @FXML
    void adminPanel(ActionEvent event) throws IOException {
        loadPage("adminPanel-view",event);
    }

    void loadPage(String page,ActionEvent event) throws IOException {
        if(page.compareTo("adminPanel-view") == 0)
        {
            if(username.getText().compareTo("") == 0 || password.getText().compareTo("") == 0)
            {
                System.out.println("please fill username and password");
                return;
            }
            DBConnection connectNow = new DBConnection();
            Connection connectDB = connectNow.getConnection();
            String query = "Select * from user where userName='"+username.getText()+"' and password='"+password.getText()+"'";
            try {
                Statement s = connectDB.createStatement();
                ResultSet resultSet = s.executeQuery(query);
                if(resultSet.next())
                {
                    if(resultSet.getInt("status") != 0)
                    {
                        URL url = new File("src/main/resources/com/example/hr/view/"+page+".fxml").toURI().toURL();
                        Parent root = FXMLLoader.load(url);
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                    else
                    {
                        System.out.println("you are not hired yet");
                    }
                }
                else
                {
                    System.out.println("username or password is invalid");
                    return;
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

        }
        else
        {
            URL url = new File("src/main/resources/com/example/hr/view/"+page+".fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }


}


