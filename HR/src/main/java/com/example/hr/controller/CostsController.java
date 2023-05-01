package com.example.hr.controller;

import com.example.hr.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CostsController {

    @FXML
    private VBox vb;

    @FXML
    public void initialize(){
        Session s = Session.getSession();
        System.out.println(s.getStrategy());
        Label l = new Label("the total costs are: "+(s.getStrategy()).execute()+" $");
        vb.getChildren().add(l);
    }
}

