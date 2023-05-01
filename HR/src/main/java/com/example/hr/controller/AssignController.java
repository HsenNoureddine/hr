package com.example.hr.controller;

import com.example.hr.DBConnection;
import com.example.hr.Session;
import com.example.hr.model.AdminModel;
import com.example.hr.model.EmployeeModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class AssignController {

    @FXML
    private VBox vb;

    private VBox container;

    @FXML
    public void initialize()
    {
        fill();
    }

    public void fill()
    {
        container = new VBox();
        Label who = new Label("Assign task to:");

        ArrayList<String> employees = new ArrayList<>();
        ArrayList<Integer> employeeIDs = new ArrayList<>();

        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();

        ArrayList<EmployeeModel> eList = new ArrayList<>();
        Session s = Session.getSession();
        AdminModel a = new AdminModel(s.getUserID());
        a.getAllEmployees();
        eList = a.getEmployees();

        for(int i = 0; i < eList.size();i++)
        {
            EmployeeModel employee = eList.get(i);
            if(employee.getStatus() == 0) continue;
            employeeIDs.add(employee.getUserID());
            employees.add(employee.getUserName());
        }
        container.getStyleClass().add("container");

        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(employees));

        Button b = new Button();
        b.setText("Assign");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(combo_box.getValue() != null)
                {
                    TextArea t = (TextArea)vb.lookup("#todo");
                    String name = combo_box.getValue().toString();
                    int user = employeeIDs.get(employees.indexOf(name));
                    String assignTask = "INSERT INTO `task`(`userID`, `task`) VALUES ("+user+",'"+t.getText()+"')";
                    try{
                        Statement s = connectDB.createStatement();
                        s.executeUpdate(assignTask);
                        t.setText("");
                    }
                    catch (Exception e){}
                }
                else
                {
                    System.out.println("please choose someone to assign task to");
                }
            }
        });


        container.getChildren().addAll(who,combo_box);
        TextArea t = new TextArea();
        t.setId("todo");
        container.getChildren().add(t);
        vb.getChildren().addAll(container,b);


    }



}

