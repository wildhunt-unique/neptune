package com.qtu404.neptune.server.dao;

import com.qtu404.neptune.util.model.Paging;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MyBatisDAO<T> {
    private final String namespaces;

    @Autowired
    protected SqlSessionTemplate sqlSession;

    public MyBatisDAO() {
        if (this.getClass().getGenericSuperclass() instanceof ParameterizedType) {
            this.namespaces = ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
        } else {
            this.namespaces = ((Class) ((ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
        }
    }


    private static final String SAVE = "save";
    private static final String SAVES = "saves";

    private static final String REMOVE = "remove";
    private static final String REMOVES = "removes";

    private static final String UPDATE = "update";

    private static final String FETCH_BY_ID = "fetch";
    private static final String FETCH_BY_BATCH_ID = "fetchByIds";

    private static final String LIST_ALL = "listAll";
    private static final String LIST_CRITERIA = "listCriteria";
    private static final String COUNT = "count";

    public Boolean save(T t) {
        return this.sqlSession.insert(this.sqlId(SAVE), t) == 1;
    }

    public Integer save(List<T> ts) {
        return this.sqlSession.insert(this.sqlId(SAVES), ts);
    }

    public Boolean remove(Long id) {
        return this.sqlSession.delete(this.sqlId(REMOVE), id) == 1;
    }

    public Integer remove(List<Long> ids) {
        return this.sqlSession.delete(sqlId(REMOVES), ids);
    }

    public Boolean update(T t) {
        return this.sqlSession.update(sqlId(UPDATE), t) == 1;
    }

    public T fetch(Long id) {
        return this.sqlSession.selectOne(sqlId(FETCH_BY_ID), id);
    }

    public List<T> fetch(List<Long> ids) {
        if (ids.size() == 0) return new ArrayList<>();
        return this.sqlSession.selectList(sqlId(FETCH_BY_BATCH_ID), ids);
    }

    public T querySingleByCondition(Map<String, Object> criteria) {
        return this.list(criteria).stream().findFirst().orElse(null);
    }

    public List<T> list() {
        return this.sqlSession.selectList(this.sqlId(LIST_ALL));
    }

    public List<T> list(Map<String, Object> criteria) {
        return sqlSession.selectList(sqlId(LIST_CRITERIA), criteria);
    }

    public Paging<T> paging(Integer offset, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        map.put("limit", limit);
        map.put("offset", offset);
        return this.paging(map);
    }

    public Paging<T> paging(Map<String, Object> criteria) {
        return new Paging<>(this.count(criteria), this.list(criteria));
    }

    public Integer count(Map<String, Object> criteria) {
        if (criteria == null) criteria = new HashMap<>();
        return this.sqlSession.selectOne(sqlId(COUNT), criteria);
    }

    public Integer count() {
        return this.sqlSession.selectOne(sqlId(COUNT), null);
    }

    private String sqlId(String id) {
        return namespaces + "." + id;
    }
}
