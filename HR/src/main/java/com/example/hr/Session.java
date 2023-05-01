package com.example.hr;

public class Session {

    private static Session session;
    private int userID;
    public static Session getSession()
    {
        if(session == null)session = new Session();
        return session;
    }

    private Session()
    {}

    public void setUserID(int num)
    {
        userID = num;
    }
    public int getUserID()
    {
        return userID;
    }

}
