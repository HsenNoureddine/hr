package com.example.hr.model;

import com.example.hr.DBConnection;
import com.example.hr.Session;
import javafx.concurrent.Task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeModel {
    private String firstName;
    private String lastName;
    private String userName;
    private int userID;
    private float salary;
    private int type;
    private int status;
    private ArrayList<TaskModel> tasks = new ArrayList<>();
    public EmployeeModel(int uid)
    {
        this.userID = uid;
        setInfo();
    }

    public EmployeeModel(String fn,String ln,String un,int uid,int type,int status,float salary)
    {
        this.firstName = fn;
        this.lastName = ln;
        this.userName = un;
        this.userID = uid;
        this.type = type;
        this.status = status;
        this.salary = salary;
    }

    public void setInfo()
    {
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "Select * from user where userID="+this.getUserID();
        try
        {
            Statement s = connectDB.createStatement();
            ResultSet resultSet = s.executeQuery(query);
            if(resultSet.next())
            {
                setFirstName(resultSet.getString("firstName"));
                setLastName(resultSet.getString("lastName"));
                setUserName(resultSet.getString("userName"));
                setType(resultSet.getInt("type"));
                setSalary(resultSet.getFloat("salary"));
                setStatus(resultSet.getInt("status"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public void getAllEmployees(){}
    public float getSalary() {
        return salary;
    }

    public int getStatus() {
        return status;
    }

    public int getType() {
        return type;
    }

    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<TaskModel> getTasks()
    {
        Session session = Session.getSession();
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "Select * from task where userID="+session.getUserID();
        try{
            Statement s = connectDB.createStatement();
            ResultSet resultSet = s.executeQuery(query);
            while(resultSet.next())
            {
                TaskModel t = new TaskModel();
                t.task = resultSet.getString("task");
                t.status = resultSet.getInt("status");
                if(t.status == 1)continue;
                tasks.add(t);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return tasks;
    }

}
