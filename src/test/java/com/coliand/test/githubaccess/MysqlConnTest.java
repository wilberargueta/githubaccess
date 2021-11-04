package com.coliand.test.githubaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.coliand.test.githubaccess.jpa.entity.AuditRequestsProcessEntity;

public class MysqlConnTest {
    public static final void main(String[] args) {

        Super supers = new Sub();
        supers.Super();
        AuditRequestsProcessEntity dd = new AuditRequestsProcessEntity();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/GITHUB_ACCESS", "root", "admin")) {

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

/**
 * InnerMysqlConnTest
 */
class Super {
    void Super() {
        System.out.println("Super");
    }

    Super() {
        System.out.println("Super class");
    }
}

class Sub extends Super {
    void Super() {
        System.out.println("sub super");
    }
}

/**
 * InnerMysqlConnTest
 */
 interface InnerMysqlConnTest {
public static final int a =1;
  void correr();
}