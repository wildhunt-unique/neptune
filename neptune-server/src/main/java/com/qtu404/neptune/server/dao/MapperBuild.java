package com.qtu404.neptune.server.dao;

import com.qtu404.neptune.util.mapper.MapperBuilder;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午9:51
 */
public class MapperBuild {
    public static void main(String[] args) {
        MapperBuilder builder = MapperBuilder.builder()
                .db("neptune")
                .username("root")
                .password("anywhere")
                .path("/Users/admin/Desktop/")
                .build();
        builder.build("shop_category","ShopCategory");
    }
}
