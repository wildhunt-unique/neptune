CREATE TABLE `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) DEFAULT NULL COMMENT '类目id',
  `item_code` varchar(45) DEFAULT NULL COMMENT '商品编码',
  `shop_id` bigint(20) NOT NULL COMMENT '店铺id',
  `shop_name` varchar(128) DEFAULT '' COMMENT '店铺名',
  `name` varchar(1024) NOT NULL DEFAULT '' COMMENT '商品名',
  `advertise` text COMMENT '广告',
  `main_image` varchar(512) DEFAULT '' COMMENT '主图地址',
  `video_url` varchar(512) DEFAULT NULL COMMENT '视频地址',
  `status` smallint(6) NOT NULL COMMENT '商品状态：1(上架),-1(下架),-2(冻结),-3(删除)',
  `type` smallint(6) DEFAULT NULL COMMENT '商品类型',
  `attributes_json` varchar(4096) DEFAULT NULL COMMENT 'sku销售属性集合',
  `extra_json` text COMMENT '其它内容',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '最后更新时间',
  `price` int(11) DEFAULT NULL,
  `inventory` bigint(20) DEFAULT '0' COMMENT '库存量',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_parana_item_category_id` (`category_id`) USING BTREE,
  KEY `idx_parana_item_shop_id` (`shop_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11000001 DEFAULT CHARSET=utf8;

CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `out_id` varchar(32) DEFAULT '' COMMENT '外部订单id',
  `buyer_id` bigint(19) NOT NULL COMMENT '买家id',
  `buyer_name` varchar(64) NOT NULL DEFAULT '' COMMENT '买家姓名',
  `shop_name` varchar(64) DEFAULT NULL COMMENT '卖家姓名',
  `shop_id` bigint(20) NOT NULL COMMENT '卖家id',
  `enable_status` smallint(5) NOT NULL COMMENT '是否可见, 1: 可见; 0: 不可见',
  `pay_status` varchar(64) NOT NULL,
  `status` varchar(64) NOT NULL,
  `receive_status` varchar(64) NOT NULL,
  `reverse_status` varchar(64) NOT NULL,
  `pay_at` datetime DEFAULT NULL COMMENT '支付完成时间',
  `shipping_at` datetime DEFAULT NULL COMMENT '发货时间',
  `confirm_at` datetime DEFAULT NULL COMMENT '确认收货时间',
  `paid_amount` bigint(20) NOT NULL COMMENT '实际支付金额',
  `accomplish_at` datetime DEFAULT NULL COMMENT '订单完成时间',
  `item_total_amount` bigint(20) DEFAULT '0',
  `delivery_address` varchar(256) DEFAULT NULL COMMENT '收货地址, json格式,包含详细地址和各级id,以及type',
  `buyer_notes` varchar(127) DEFAULT NULL COMMENT '买家留言',
  `shop_notes` varchar(127) DEFAULT NULL COMMENT '卖家留言',
  `tag` bigint(19) DEFAULT NULL COMMENT '打标',
  `extra_json` varchar(512) DEFAULT NULL COMMENT '拓展字段',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_order_buyer_id` (`buyer_id`),
  KEY `idx_order_shop_id` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COMMENT='店铺订单';

CREATE TABLE `shop` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `outer_id` varchar(32) DEFAULT NULL COMMENT '外部店铺编码',
  `user_id` bigint(20) DEFAULT NULL COMMENT '商家id',
  `user_name` varchar(32) DEFAULT NULL COMMENT '商家名称',
  `name` varchar(64) NOT NULL COMMENT '店铺名称',
  `type` tinyint(1) NOT NULL COMMENT '店铺类型 1:门店 2:商家',
  `mobile` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(32) DEFAULT NULL COMMENT '电子邮件',
  `image_url` varchar(128) DEFAULT NULL COMMENT '店铺图片url',
  `address` varchar(128) DEFAULT NULL COMMENT '店铺地址',
  `tags` bigint(20) DEFAULT NULL COMMENT '店铺标签',
  `status` tinyint(1) NOT NULL COMMENT '状态',
  `extra_json` varchar(4096) DEFAULT NULL COMMENT '店铺额外信息',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4 COMMENT='店铺表';

CREATE TABLE `shop_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `shop_id` bigint(20) DEFAULT NULL COMMENT '目标id',
  `name` varchar(255) DEFAULT NULL COMMENT '类目名',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `pid` bigint(20) NOT NULL COMMENT '父id，一级类目为-1',
  `logo` varchar(255) DEFAULT NULL COMMENT '类目logo',
  `level` tinyint(6) NOT NULL COMMENT '本类目所属层级',
  `has_children` tinyint(1) DEFAULT '0' COMMENT '标识是否有孩子类目',
  `type` tinyint(2) DEFAULT NULL COMMENT '叶子类目时绑定目标类型，0：后台类目，1：商品',
  `sort_index` smallint(6) NOT NULL COMMENT '类目在本级中的排序',
  `disclosed` tinyint(1) DEFAULT '0' COMMENT '是否默认展开',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  `extra_json` varchar(255) DEFAULT NULL COMMENT 'extra属性json',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_pid` (`pid`) USING BTREE,
  KEY `idx_shop_id` (`shop_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COMMENT='店铺类目表';

CREATE TABLE `tag` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL COMMENT '标签名',
  `type` smallint(6) NOT NULL COMMENT '标签类型',
  `content` varchar(256) DEFAULT NULL COMMENT '标签自定义内容',
  `extra_json` varchar(1024) DEFAULT NULL COMMENT '额外信息',
  `status` tinyint(4) NOT NULL COMMENT '状态 0:未激活, 1:正常, -1:锁定, -2:冻结, -3: 删除',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COMMENT='打标信息';

CREATE TABLE `tag_binding` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_id` int(11) NOT NULL COMMENT '标签id',
  `type` smallint(6) NOT NULL COMMENT '绑定目标类型',
  `target_id` int(11) NOT NULL COMMENT '绑定的目标id',
  `extra_json` varchar(256) DEFAULT NULL COMMENT '额外信息',
  `status` tinyint(4) NOT NULL COMMENT '状态 0:未激活, 1:正常, -1:锁定, -2:冻结, -3: 删除',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_tag_id_target_id` (`tag_id`,`type`),
  KEY `idx_type_target_id` (`type`,`target_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 COMMENT='抽象标签绑定表';

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(40) DEFAULT NULL COMMENT '昵称',
  `username` varchar(40) DEFAULT NULL COMMENT '用户名',
  `email` varchar(32) DEFAULT NULL COMMENT '邮件',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(32) DEFAULT NULL COMMENT '登录密码',
  `type` smallint(6) NOT NULL COMMENT '用户类型',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像图片地址',
  `name` varchar(40) DEFAULT NULL COMMENT '真实姓名',
  `status` tinyint(4) NOT NULL COMMENT '状态 0:未激活, 1:正常, -1:锁定, -2:冻结, -3: 删除',
  `roles_json` varchar(512) DEFAULT NULL COMMENT '用户权限信息',
  `extra_json` varchar(1024) DEFAULT NULL COMMENT '用户额外信息',
  `tags_json` varchar(1024) DEFAULT NULL COMMENT '用户标签的json表示形式',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_users_name` (`username`),
  UNIQUE KEY `idx_users_email` (`email`),
  UNIQUE KEY `idx_users_mobile` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE `order_line` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `out_id` varchar(32) DEFAULT NULL COMMENT '外部订单号',
  `order_id` bigint(20) NOT NULL COMMENT '店铺订单id',
  `buyer_id` bigint(20) NOT NULL COMMENT '买家id',
  `buyer_name` varchar(64) NOT NULL DEFAULT '' COMMENT '买家姓名',
  `shop_id` bigint(20) NOT NULL COMMENT '店铺id',
  `shop_name` varchar(128) DEFAULT '' COMMENT '店铺名称',
  `item_id` bigint(19) DEFAULT NULL COMMENT 'sku id',
  `item_code` varchar(64) DEFAULT NULL COMMENT 'sku code',
  `item_name` varchar(512) NOT NULL DEFAULT '' COMMENT 'sku 名称',
  `item_image` varchar(512) DEFAULT '' COMMENT 'sku 缩率图',
  `item_attr` varchar(512) DEFAULT NULL COMMENT 'sku 销售属性',
  `quantity` int(10) NOT NULL COMMENT '数量',
  `paid_amount` bigint(20) NOT NULL COMMENT '实际支付金额',
  `confirm_at` datetime DEFAULT NULL COMMENT '确认时间',
  `receive_status` tinyint(4) COMMENT '接受状态',
  `extra_json` varchar(512) DEFAULT NULL COMMENT '拓展字段',
  `status` tinyint(4) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_order_line_order_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8mb4 COMMENT='订单行';