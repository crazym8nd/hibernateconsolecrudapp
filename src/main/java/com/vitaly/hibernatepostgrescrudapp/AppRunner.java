package com.vitaly.hibernatepostgrescrudapp;
//  11-Jan-24
// gh crazym8nd


import com.vitaly.hibernatepostgrescrudapp.dao.LabelDao;
import com.vitaly.hibernatepostgrescrudapp.model.Label;

import java.sql.*;
import java.util.List;

public class AppRunner {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost/hibernateconsolecrud";
    private static final String USER = "postgres";
    private static final String PASSWORD = "666666";



    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World!");

        LabelDao labelDao = new LabelDao();

        List<Label> labels = labelDao.getLabels();
        System.out.println(labels);
        System.out.println("END");

    }
}
