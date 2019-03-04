package com.qtu404.neptune.util.mapper;


import lombok.*;

import java.io.File;
import java.io.FileOutputStream;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class MapperBuilder {
    private  String username = "root";
    private  String password = "123456";
    private  String db = "qh";
    private  String path = "/Users/admin/Desktop/";

    /**
     * @param tableName 表名
     * @param clazzName 对应的do的name
     */
    public  void build(String tableName, String clazzName) {
        try {
            TableInfo tableInfo = new DBHelper(username, password, db)
                    .getTableInfo(tableName);
            XMLHandler handler = new XMLHandler(tableInfo, clazzName, path);
            handler.createXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void refactorBuild(String tableName, String clazzName) {
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
}


