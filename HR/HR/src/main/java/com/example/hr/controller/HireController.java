package com.example.hr.controller;


import com.example.hr.DBConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;


public class HireController {
    @FXML
    private VBox vb;
    private Stage stage;
    private Scene scene;
    private Parent root;
    ArrayList<Integer> users = new ArrayList<>();

    @FXML
    public void initialize(){
        VBox container = new VBox();
        Connection connectDB = fill(container);
        vb.getChildren().add(container);
        container.getStyleClass().add("container");
        Button b = new Button();
        b.setText("Hire");
        vb.getChildren().add(b);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Set<Node> toHire = vb.lookupAll("#ifHire");
                int i = 0;
                for(Node n : toHire) {
                    if (((CheckBox) n).isSelected()) {
                        String hireQuery = "Update user Set status=1 where userID=" + users.get(i);
                        try {
                            Statement s = connectDB.createStatement();
                            s.executeUpdate(hireQuery);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("hired " + users.get(i));

                    }
                    i++;
                }

//                users.removeAll(users);
//                vb.getChildren().remove(b);
//                vb.getChildren().remove(container);
//                fill(container);
//                vb.getChildren().add(container);
//                vb.getChildren().add(b);
                loadPage(actionEvent,"adminPanel-view");
            }
        });

    }
    public Connection fill(VBox container)
    {
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "Select * from user where status=0";
        try {
            Statement s = connectDB.createStatement();
            ResultSet resultSet = s.executeQuery(query);
            container.getStyleClass().add("container");
            while (resultSet.next()) {
                if (resultSet.getInt("status") != 0) continue;

                users.add(resultSet.getInt("userID"));

                HBox hb = new HBox();
                Label un = new Label("username: ");
                un.setMinWidth(50);

                Label userName = new Label(resultSet.getString("userName"));
                userName.setMinWidth(50);

                CheckBox cb = new CheckBox();
                cb.setId("ifHire");

                hb.getChildren().addAll(un,userName,cb);

                HBox.setHgrow(un, Priority.ALWAYS);
                HBox.setHgrow(userName, Priority.ALWAYS);

                hb.getStyleClass().add("HireHBox");

                container.getChildren().add(hb);
            }

        }catch (Exception e)
        {

        }
        return connectDB;
    }

    @FXML
    void loadPage(ActionEvent event,String page) {
        URL url = null;
        Parent root = null;
        try {
            url = new File("src/main/resources/com/example/hr/view/"+page+".fxml").toURI().toURL();
            root = FXMLLoader.load(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
