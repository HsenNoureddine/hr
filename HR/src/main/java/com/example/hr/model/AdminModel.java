package com.example.hr.model;

import com.example.hr.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminModel extends EmployeeModel {
    private ArrayList<EmployeeModel> employees;
    public AdminModel(int uid)
    {
        super(uid);
        employees = new ArrayList<>();
    }

    public AdminModel(String fn,String ln,String un,int uid,int type,int status,float salary)
    {
        super(fn, ln, un, uid, type, status, salary);
        employees = new ArrayList<>();
    }
    public void getAllEmployees()
    {
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "Select userID from user";
        try
        {
            Statement s = connectDB.createStatement();
            ResultSet resultSet = s.executeQuery(query);
            while(resultSet.next())
            {

                EmployeeModel e = new EmployeeModel(resultSet.getInt("userID"));
                e.setInfo();
                employees.add(e);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public ArrayList<EmployeeModel> getEmployees() {
        return employees;
    }
}
