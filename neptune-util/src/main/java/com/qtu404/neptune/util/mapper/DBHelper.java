package com.qtu404.neptune.util.mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DBHelper {
    private String username;
    private String password;
    private String dbName;


    DBHelper(String username, String password, String dbName) {
        this.username = username;
        this.password = password;
        this.dbName = dbName;
    }


    TableInfo getTableInfo(String tableName) throws ClassNotFoundException, SQLException {
        List<String> column = new ArrayList<>();

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = null;
        ResultSet rst = null;
        Statement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, username, password);
            stmt = connection.createStatement();
            rst = stmt.executeQuery("show full columns from `" + tableName + "`");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (rst.next()) {
            column.add(rst.getString("Field"));
        }

        return new TableInfo(tableName, column);
    }

}
