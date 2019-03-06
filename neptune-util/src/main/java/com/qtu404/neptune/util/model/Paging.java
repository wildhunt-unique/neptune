package com.qtu404.neptune.util.model;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午1:44
 */

@Data
public class Paging<T> implements Serializable {
    private static final long serialVersionUID = -7236584153535008879L;

    private Integer total;
    private List<T> data;

    public Paging(Integer total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public static Paging empty() {
        return new Paging(0, new ArrayList(0));
    }
}