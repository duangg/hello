package com.nolan.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HSqlDbTest {
    public static void main(String[] args) {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:hsqldb:mydatabase","SA","");
            conn.createStatement().executeUpdate("create table contacts (name varchar(45),email varchar(45),phone varchar(45))");
            conn.createStatement().executeUpdate("INSERT INTO contacts (name, email, phone) VALUES ('John', 'john@example.com', '19999999999')");
            ResultSet result = conn.createStatement().executeQuery("SELECT name, email, phone FROM contacts");
            result.next();
            System.out.printf("Name: %s, email: %s, phone: %s\n",
                    result.getString("name"), result.getString("email"), result.getString("phone"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
