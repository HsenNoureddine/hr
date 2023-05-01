package com.example.hr;

import com.example.hr.model.AdminModel;
import com.example.hr.model.EmployeeModel;

import java.util.ArrayList;

public class SalaryCostStrategy implements CostStrategy{
    @Override
    public float execute() {
        Session s = Session.getSession();
        AdminModel a = new AdminModel(s.getUserID());
        a.getAllEmployees();
        ArrayList<EmployeeModel> employees = a.getEmployees();
        float costs = 0;
        for(int i = 0;i < employees.size();i++)
        {
            costs += employees.get(i).getSalary();
        }
        return costs;
    }
}
