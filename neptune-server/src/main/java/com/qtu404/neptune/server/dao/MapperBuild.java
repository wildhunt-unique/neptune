package com.qtu404.neptune.server.dao;

import com.qtu404.neptune.util.mapper.MapperBuilder;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午9:51
 */
public class MapperBuild {
    public static void main(String[] args) {
        MapperBuilder builder = MapperBuilder.builder()
                .host("120.24.186.116")
                .db("neptune")
                .username("root")
                .password("geralt")
                .path("/")
                .build();
        builder.build("shopping_cart","ShoppingCart");
    }
}
