package com.example.hr.controller;

import com.example.hr.DBConnection;
import com.example.hr.Session;
import com.example.hr.model.EmployeeModel;
import com.example.hr.model.TaskModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CheckTasksController {

    @FXML
    private VBox vb;

    private VBox container;
    private ArrayList<CheckBox> todo;

    @FXML
    public void initialize()
    {
        fill();
    }

    public void fill()
    {
        Session session = Session.getSession();
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();
        container = new VBox();
        container.getStyleClass().add("container");
        todo = new ArrayList<>();

        EmployeeModel e = new EmployeeModel(session.getUserID());
        e.setInfo();
        ArrayList<TaskModel> tasks = e.getTasks();
        for(int i = 0; i < tasks.size();i++)
        {
            TaskModel t = tasks.get(i);
            if (t.status == 1)continue;

            HBox hb = new HBox();
            hb.getStyleClass().add("TaskBox");
            Label task = new Label(t.task);
            CheckBox cb = new CheckBox();
            cb.setId("status");
            hb.getChildren().addAll(task,cb);

            task.setMinWidth(100);
            HBox.setHgrow(task, Priority.ALWAYS);

            todo.add(cb);

            container.getChildren().add(hb);
        }
        Button b = new Button();
        b.setText("submit");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBConnection connectNow = new DBConnection();
                Connection connectDB = connectNow.getConnection();

                String deleteDone = "DELETE FROM `task` WHERE userID="+session.getUserID()+" and status=1";
                try{
                    Statement s = connectDB.createStatement();
                    s.executeUpdate(deleteDone);
                }
                catch (Exception e) {}

                for(int i = 0; i < tasks.size();i++)
                {
                    TaskModel t = tasks.get(i);
                    if((todo.get(i)).isSelected())
                    {
                        String query = "Update task set status=1 where userID="+session.getUserID()+" and task='"+t.task+"'";
                        try{
                            Statement s = connectDB.createStatement();
                            s.executeUpdate(query);
                        }
                        catch (Exception e) {}
                    }
                }
                vb.getChildren().clear();
                fill();
            }
        });
        vb.getChildren().addAll(container,b);


    }



}
