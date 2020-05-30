package com.example.addcontacts;

public class Temp {
    public  static MyDbHandler dbHandler;

    public static MyDbHandler getDbHandler() {
        return dbHandler;
    }

    public static void setDbHandler(MyDbHandler dbHandler) {
        Temp.dbHandler = dbHandler;
    }
}
