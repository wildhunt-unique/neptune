package com.qtu404.neptune.server.dao;

import com.qtu404.neptune.util.mapper.MapperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午9:51
 */
public class MapperBuild {
    public static void main(String[] args) {
//        MapperBuilder builder = MapperBuilder.builder()
//                .db("neptune")
//                .username("root")
//                .password("anywhere")
//                .path("/Users/admin/Desktop/")
//                .build();
//        builder.build("shop_category","ShopCategory");

        List<Long> longs = new ArrayList<>();
        List<String> strings = longs.stream().map(String::valueOf).collect(Collectors.toList());
        System.out.println(strings.size());
    }
}
