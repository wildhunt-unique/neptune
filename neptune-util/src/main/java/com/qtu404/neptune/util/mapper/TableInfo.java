package com.qtu404.neptune.util.mapper;

import java.util.ArrayList;
import java.util.List;

class TableInfo {
    private String tableName;
    private List<String> columns;
    private List<String> field = new ArrayList<>();

    TableInfo(String tableName, List<String> cloumns) {
        this.tableName = tableName;
        this.columns = cloumns;

        cloumns.remove("id");

        columns.forEach(e -> {
            char[] str = e.toCharArray();
            char before = str[0];
            for (int i = 0; i < str.length; i++) {
                if (before == '_') {
                    str[i] -= 32;
                }
                before = str[i];
            }
            this.field.add(String.valueOf(str).replace("_", ""));
        });
    }

    String getVals() {
        StringBuilder builder = new StringBuilder();
        String last = field.get(field.size() - 1);
        field.forEach(e -> {
            if (e.equals("createdAt") || e.equals("updatedAt")) {
                builder.append("now()");
            } else {
                builder.append("#{").append(e).append("}");
            }
            if (!last.equals(e)) {
                builder.append(",");
            }
        });
        return builder.toString();
    }

    String getListVals() {
        StringBuilder builder = new StringBuilder();
        String last = field.get(field.size() - 1);
        field.forEach(e -> {
            if (e.equals("createdAt") || e.equals("updatedAt")) {
                builder.append("now()");
            } else {
                builder.append("#{item.").append(e).append("}");
            }
            if (!last.equals(e)) {
                builder.append(",");
            }
        });
        return builder.toString();
    }

    String getColsExcludeId() {
        StringBuilder builder = new StringBuilder();
        String last = columns.get(columns.size() - 1);
        columns.forEach(e -> {
            builder.append(e);
            if (!last.equals(e)) {
                builder.append(",");
            }
        });
        return builder.toString();
    }

    String getNamespace() {
        char[] s = this.tableName.toCharArray();
        s[0] -= 32;
        char before = s[0];
        for (int i = 0; i < s.length; i++) {
            if (before == '_') {
                s[i] -= 32;
            }
            before = s[i];
        }
        return String.valueOf(s);
    }

    String getTableName() {
        return tableName;
    }


    List<String> getColumns() {
        return columns;
    }

    List<String> getField() {
        return field;
    }


}
