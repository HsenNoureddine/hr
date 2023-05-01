package com.example.hr;

import com.example.hr.model.EmployeeModel;

public class Session {

    private static Session session;

    private CostStrategy strategy;
    private int userID;

    public EmployeeModel me;
    public static Session getSession()
    {
        if(session == null)session = new Session();
        return session;
    }

    private Session()
    {
        me = new EmployeeModel(this.userID);
        me.setInfo();
    }

    public void setUserID(int num)
    {
        me = new EmployeeModel(this.userID);
        me.setInfo();
        userID = num;
    }
    public int getUserID()
    {
        return userID;
    }

    public void setStrategy(CostStrategy strategy) {
        this.strategy = strategy;
    }

    public CostStrategy getStrategy() {
        return strategy;
    }
}
