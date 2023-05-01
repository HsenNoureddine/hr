package com.example.hr.controller;

import com.example.hr.DBConnection;
import com.example.hr.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AdminPanelController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button Hire;
    @FXML
    private Button Assign;
    @FXML
    private Button Check;
    @FXML
    private Button Logout;
    @FXML
    private Button Costs;
    @FXML
    private Button Salary;

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

    @FXML
    public void initialize()
    {
        Session s = Session.getSession();
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "Select * from user where userID="+s.getUserID();
        try{
            VBox temp = (VBox) bp.getLeft();
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            System.out.println(resultSet.getInt("type"));
            switch (resultSet.getInt("type"))
            {
                case 0:
                    temp.getChildren().clear();
                    temp.getChildren().add(Check);
                    temp.getChildren().add(Logout);
                    break;
                case 1: ;
                    temp.getChildren().clear();
                    temp.getChildren().add(Assign);
                    temp.getChildren().add(Check);
                    temp.getChildren().add(Logout);
                    break;
            }
        }catch (Exception e) {
            System.out.println(e);
        }

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
