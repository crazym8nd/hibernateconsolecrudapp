package com.vitaly.hibernatepostgrescrudapp;
/*
  11-Jan-24
 gh crazym8nd
*/

import com.vitaly.hibernatepostgrescrudapp.view.MainView;

import java.sql.*;


public class AppRunner {

    public static void main(String[] args) throws SQLException {
        MainView mainView = new MainView();
        mainView.start();


    }
}
