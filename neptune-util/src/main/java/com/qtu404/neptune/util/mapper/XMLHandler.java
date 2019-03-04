package com.qtu404.neptune.util.mapper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

class XMLHandler {
    private Document doc = null;
    private TableInfo info;
    private String clazz;
    private String path;

    XMLHandler(TableInfo info, String clazz, String path) {
        this.info = info;
        this.clazz = clazz;
        this.path = path;

        DocumentBuilder builder = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        assert builder != null;
        this.doc = builder.newDocument();
        doc.setXmlStandalone(true);
    }

    void createXML() throws ParserConfigurationException, TransformerException, FileNotFoundException {
        Element mapper = this.doc.createElement("mapper");
        mapper.setAttribute("namespace", clazz);

        // mapper 根节点
        this.doc.appendChild(mapper);

        // resultMap
        this.addResultMap(mapper);

        // 各种sql
        this.addSqlToken(mapper);

        // save and batch save
        this.addSaveSql(mapper);

        // remove and batchRemove
        this.addRemoveSql(mapper);

        // update
        this.addUpdateSql(mapper);

        // fetch and batch fetch
        this.addFetchSql(mapper);

        // list and condition list
        this.addListSql(mapper);

        // count
        this.addCountSql(mapper);

        // 输出文件
        outputFile(doc);
    }

    private void addCountSql(Element mapper) {
        Element count = doc.createElement("select");
        count.setAttribute("id", "count");
        count.setAttribute("resultType", "java.lang.Integer");
        count.setAttribute("parameterType", "java.util.Map");
        count.setTextContent("\n SELECT count(1)\n" +
                "        FROM\n" +
                "        <include refid=\"tb\"/>\n" +
                "        WHERE TRUE\n" +
                "        <include refid=\"criteria\"/>");
        mapper.appendChild(count);
    }

    private void addListSql(Element mapper) {
        // list
        Element list = doc.createElement("select");
        list.setAttribute("id", "listAll");
        list.setAttribute("resultMap", "FullMap");
        list.setTextContent("\nSELECT\n" +
                "        <include refid=\"cols_all\"/>\n" +
                "        FROM\n" +
                "        <include refid=\"tb\"/>");
        mapper.appendChild(list);

        // condition list
        Element conditionList = doc.createElement("select");
        conditionList.setAttribute("id", "listCriteria");
        conditionList.setAttribute("resultMap", "FullMap");
        conditionList.setAttribute("parameterType", "java.util.Map");
        conditionList.setTextContent("SELECT\n" +
                "        <include refid=\"cols_all\"/>\n" +
                "        FROM\n" +
                "        <include refid=\"tb\"/>\n" +
                "        WHERE TRUE\n" +
                "        <include refid=\"criteria\"/>\n" +
                "        ORDER BY id DESC\n" +
                "        <include refid=\"paging\"/>");
        mapper.appendChild(conditionList);
    }

    private void addFetchSql(Element mapper) {
        Element fetch = doc.createElement("select");
        fetch.setAttribute("id", "fetch");
        fetch.setAttribute("parameterType", "java.lang.Long");
        fetch.setAttribute("resultMap", "FullMap");
        fetch.setTextContent("\nSELECT\n" +
                "        <include refid=\"cols_all\"/>\n" +
                "        FROM\n" +
                "        <include refid=\"tb\"/>\n" +
                "        WHERE id = #{id}");

        Element batchElement = doc.createElement("select");
        batchElement.setAttribute("id", "fetchByIds");
        batchElement.setAttribute("parameterType", "java.util.List");
        batchElement.setAttribute("resultMap", "FullMap");
        batchElement.setTextContent("\nSELECT\n" +
                "        <include refid=\"cols_all\"/>\n" +
                "        FROM\n" +
                "        <include refid=\"tb\"/>\n" +
                "        WHERE id IN\n" +
                "        <foreach item=\"id\" collection=\"list\" open=\"(\" separator=\",\" close=\")\">\n" +
                "            #{id}\n" +
                "        </foreach>");
        mapper.appendChild(fetch);
        mapper.appendChild(batchElement);
    }

    private void addUpdateSql(Element mapper) {
        Element update = doc.createElement("update");
        update.setAttribute("id", "update");
        update.setAttribute("parameterType", clazz);
        update.setTextContent("\n UPDATE \n <include refid=\"tb\"/>");
        Element set = doc.createElement("set");

        for (int i = 0; i < info.getColumns().size(); i++) {
            Element if_test = doc.createElement("if");
            if_test.setAttribute("test", info.getField().get(i) + " != null");
            if_test.setTextContent(info.getColumns().get(i) + " = " + "#{" + info.getField().get(i) + "},");
            set.appendChild(if_test);
        }
        Text text = doc.createTextNode("updated_at=now()");
        set.appendChild(text);

        Text value = doc.createTextNode("WHERE id=#{id}");
        update.appendChild(set);
        update.appendChild(value);
        mapper.appendChild(update);
    }

    private void addRemoveSql(Element mapper) {
        // remove
        Element remove = doc.createElement("delete");
        remove.setAttribute("id", "remove");
        remove.setAttribute("parameterType", "java.lang.Long");
        remove.setTextContent("\nDELETE FROM\n" +
                "        <include refid=\"tb\"/>\n" +
                "        WHERE id = #{id}");
        mapper.appendChild(remove);

        // batch remove
        Element batchRemove = doc.createElement("delete");
        batchRemove.setAttribute("id", "removes");
        batchRemove.setAttribute("parameterType", "java.util.List");
        batchRemove.setTextContent("\nDELETE FROM\n" +
                "        <include refid=\"tb\"/>\n" +
                "        WHERE\n" +
                "        <foreach collection=\"list\" item=\"ids\" separator=\"OR\">\n" +
                "            id = #{ids}\n" +
                "        </foreach>");
        mapper.appendChild(batchRemove);
    }


    private void addSaveSql(Element mapper) {
        // save
        Element insert = doc.createElement("insert");
        insert.setAttribute("id", "save");
        insert.setAttribute("parameterType", clazz);
        insert.setAttribute("keyProperty", "id");
        insert.setAttribute("useGeneratedKeys", "true");
        insert.setTextContent("\nINSERT INTO " + " <include refid=\"tb\"/> " + " (<include refid=\"cols_exclude_id\"/>) " +
                "VALUES (<include refid=\"vals\"/>)");
        mapper.appendChild(insert);

        // batchSave
        Element batchInsert = doc.createElement("insert");
        batchInsert.setAttribute("id", "saves");
        batchInsert.setAttribute("parameterType", "java.util.List");
        batchInsert.setAttribute("keyProperty", "id");
        batchInsert.setAttribute("useGeneratedKeys", "true");
        batchInsert.setTextContent("\nINSERT INTO " + " <include refid=\"tb\"/> " + " (<include refid=\"cols_exclude_id\"/>) " +
                "VALUES <foreach collection=\"list\" item=\"item\" sortIndex=\"sortIndex\" separator=\",\">\n" +
                " (<include refid=\"listVals\"/>)\n" +
                "</foreach>");
        mapper.appendChild(batchInsert);
    }

    /**
     * resultMap节点
     *
     * @param mapper mapper节点
     */
    private void addResultMap(Element mapper) {
        Element resultMap = doc.createElement("resultMap");

        // resultMap节点自己属性的构造
        resultMap.setAttribute("id", "FullMap");
        resultMap.setAttribute("type", this.clazz);

        // result list的构造
        Element id = doc.createElement("id");
        id.setAttribute("column", "id");
        id.setAttribute("property", "id");
        resultMap.appendChild(id);

        for (int i = 0; i < info.getField().size(); i++) {
            if (info.getColumns().get(i).equals("id")) continue;
            Element result = doc.createElement("result");
            result.setAttribute("column", info.getColumns().get(i));
            result.setAttribute("property", info.getField().get(i));
            resultMap.appendChild(result);
        }

        mapper.appendChild(resultMap);
    }


    private void addSqlToken(Element mapper) {
        // tb
        Element tb = doc.createElement("sql");
        tb.setAttribute("id", "tb");
        Text tbText = doc.createTextNode(info.getTableName());
        tb.appendChild(tbText);
        mapper.appendChild(tb);

        // cols_all
        Element colsAll = doc.createElement("sql");
        colsAll.setAttribute("id", "cols_all");
        Text colsAllText = doc.createTextNode("id,");
        Element ref_cols_exclude_id = doc.createElement("include");
        ref_cols_exclude_id.setAttribute("refid", "cols_exclude_id");
        colsAll.appendChild(colsAllText);
        colsAll.appendChild(ref_cols_exclude_id);
        mapper.appendChild(colsAll);

        // cols_exclude_id
        Element cols_exclude_id_sql = doc.createElement("sql");
        cols_exclude_id_sql.setAttribute("id", "cols_exclude_id");
        Text cols_exclude_id_sql_text = doc.createTextNode(info.getColsExcludeId());
        cols_exclude_id_sql.appendChild(cols_exclude_id_sql_text);
        mapper.appendChild(cols_exclude_id_sql);

        // vals
        Element vals = doc.createElement("sql");
        vals.setAttribute("id", "vals");
        Text val_text = doc.createTextNode(info.getVals());
        vals.appendChild(val_text);
        mapper.appendChild(vals);

        // listVals
        Element listVals = doc.createElement("sql");
        listVals.setAttribute("id", "listVals");
        Text list_val_text = doc.createTextNode(info.getListVals());
        listVals.appendChild(list_val_text);
        mapper.appendChild(listVals);

        // criteria
        Element criteria = doc.createElement("sql");
        criteria.setAttribute("id", "criteria");
        for (int i = 0; i < info.getField().size(); i++) {
            Element if_test = doc.createElement("if");
            String test = info.getField().get(i) + " != null";
            if_test.setAttribute("test", test);

            Text test_text = doc.createTextNode("AND " + info.getColumns().get(i) + " = #{" + info.getField().get(i) + "}");
            if_test.appendChild(test_text);
            criteria.appendChild(if_test);
        }
        mapper.appendChild(criteria);

        // paging
        Element paging = doc.createElement("sql");
        paging.setAttribute("id", "paging");
        Element if_test = doc.createElement("if");
        if_test.setAttribute("test", "offset != null");
        Text if_test_text = doc.createTextNode("LIMIT #{offset},#{limit}");
        if_test.appendChild(if_test_text);
        paging.appendChild(if_test);
        mapper.appendChild(paging);
    }

    private void outputFile(Document doc) throws TransformerException, FileNotFoundException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(doc);
        transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        PrintWriter pw = new PrintWriter(new FileOutputStream(path + "mapper.xml"));
        StreamResult result = new StreamResult(pw);
        transformer.transform(source, result);
        System.out.println("生成XML文件成功!");
    }
}
