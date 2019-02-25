package com.qtu404.neptune.util.mapper;


import lombok.Data;

import java.io.File;
import java.io.FileOutputStream;

@Data
public class MapperBuilder {
    private static String username = "root";
    private static String password = "123456";
    private static String db = "qh";
    private static String path = "/Users/admin/Desktop/";

    /**
     * @param tableName 表名
     * @param clazzName 对应的do的name
     */
    public static void build(String tableName, String clazzName) {
        try {
            TableInfo tableInfo = new DBHelper(username, password, db)
                    .getTableInfo(tableName);
            XMLHandler handler = new XMLHandler(tableInfo, clazzName, path);
            handler.createXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void refactorBuild(String tableName, String clazzName) {
        try {
            File file = new File(path + clazzName + "Mapper.xml");
            if (!file.exists()) file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n".getBytes());
            out.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n".getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MapperBuilder.build("item","item");
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        MapperBuilder.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        MapperBuilder.password = password;
    }

    public static String getDb() {
        return db;
    }

    public static void setDb(String db) {
        MapperBuilder.db = db;
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        MapperBuilder.path = path;
    }
}


