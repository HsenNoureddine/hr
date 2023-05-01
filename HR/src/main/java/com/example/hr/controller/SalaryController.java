package com.example.hr.controller;

import com.example.hr.DBConnection;
import com.example.hr.Session;
import com.example.hr.model.AdminModel;
import com.example.hr.model.EmployeeModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
    private ScrollPane sp;

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

        container = new VBox();
        sp = new ScrollPane();
        container.getStyleClass().add("container");
        Session s = Session.getSession();
        AdminModel a = new AdminModel(s.getUserID());
        a.getAllEmployees();
        ArrayList<EmployeeModel> employees = a.getEmployees();
        for(int i = 0; i < employees.size();i++)
        {

            EmployeeModel e = employees.get(i);
            if(e.getStatus() == 0)continue;
            users.add(e.getUserID());

            HBox hb = new HBox();
            Label lb = new Label(e.getUserName()+" :");
            TextField salary =  new TextField(e.getSalary()+"");

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
        sp.setContent(container);
        vb.getChildren().add(sp);
        vb.getChildren().add(b);
    }

}
