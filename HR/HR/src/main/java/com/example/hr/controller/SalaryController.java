package com.example.hr.controller;

import com.example.hr.DBConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SalaryController {

    @FXML
    private VBox vb;
    private VBox container;

    private ArrayList<TextField> salaries = new ArrayList<>();
    private ArrayList<Integer> users = new ArrayList<>();
    @FXML
    public void initialize()
    {
        fill();
    }

    public void fill()
    {
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "Select * from user where status=1";
        container = new VBox();
        container.getStyleClass().add("container");
        try{
            Statement s = connectDB.createStatement();
            ResultSet resultSet = s.executeQuery(query);
            while(resultSet.next())
            {
                users.add(resultSet.getInt("userID"));


                HBox hb = new HBox();
                Label lb = new Label(resultSet.getString("userName")+" :");
                TextField salary =  new TextField(resultSet.getFloat("salary")+"");

                salaries.add(salary);

                salary.setMinWidth(50);
                lb.setMinWidth(50);

                HBox.setHgrow(salary, Priority.ALWAYS);
                HBox.setHgrow(lb, Priority.ALWAYS);

                hb.getStyleClass().add("SalaryBox");

                hb.getChildren().addAll(lb,salary);
                container.getChildren().add(hb);
            }
            Button b = new Button();
            b.setText("Update");
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    for(int i = 0 ; i< users.size();i++)
                    {
                        float slry = Float.parseFloat((salaries.get(i)).getText());
                        String updateSalary = "Update user Set salary="+slry+" where userID="+users.get(i)+"";
                        try{
                            Statement s = connectDB.createStatement();
                            s.executeUpdate(updateSalary);
                        }
                        catch (Exception e){}
                    }
                }
            });
            vb.getChildren().add(container);
            vb.getChildren().add(b);
        }
        catch(Exception e){}
    }

}
