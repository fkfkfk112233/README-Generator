package controller;

import java.sql.Connection;

import util.DbConnection;

public class TestConnection {

    public static void main(String[] args) {

        Connection conn = DbConnection.getConnection();

        if (conn != null) {

            System.out.println("Database Connected!");

        }

    }

}
