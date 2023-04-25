package com.coliand.test.githubaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MysqlConnTest {
    public static final void main(String[] args) {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/GITHUB_ACCESS", "root", "admin")) {

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

