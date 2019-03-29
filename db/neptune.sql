
# ************************************************************
# Sequel Pro SQL dump
# Version 5425
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.23)
# Database: neptune
# Generation Time: 2019-03-29 00:57:17 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` smallint(6) NOT NULL COMMENT '评价类型，订单:1',
  `target_id` int(11) DEFAULT NULL COMMENT '目标id，来源域控制',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `top_id` int(11) DEFAULT NULL COMMENT '根节点id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_name` varchar(64) NOT NULL COMMENT '用户名',
  `user_avatar` varchar(128) DEFAULT NULL COMMENT '用户头像',
  `rate` smallint(6) DEFAULT NULL COMMENT '评分，非必要',
  `context` varchar(1024) DEFAULT NULL COMMENT '买家评论',
  `image_json` varchar(1024) DEFAULT NULL COMMENT '评论图片',
  `status` smallint(3) NOT NULL COMMENT '状态',
  `has_pursue` smallint(1) NOT NULL,
  `extra_json` varchar(1024) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_type_target` (`type`,`target_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评价表';



# Dump of table item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `item`;

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
  `attribute_json` varchar(4096) DEFAULT NULL COMMENT 'sku销售属性集合',
  `extra_json` text COMMENT '其它内容',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '最后更新时间',
  `price` int(11) DEFAULT NULL,
  `inventory` bigint(20) DEFAULT '0' COMMENT '库存量',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_parana_item_category_id` (`category_id`) USING BTREE,
  KEY `idx_parana_item_shop_id` (`shop_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;

INSERT INTO `item` (`id`, `category_id`, `item_code`, `shop_id`, `shop_name`, `name`, `advertise`, `main_image`, `video_url`, `status`, `type`, `attribute_json`, `extra_json`, `created_at`, `updated_at`, `price`, `inventory`)
VALUES
	(11000000,101,NULL,1100016003,'星巴克-家佳源店','鸡公煲小份',NULL,NULL,NULL,1,NULL,'{\"口味\":[\"中辣\",\"特辣\"]}',NULL,'2019-02-28 18:23:25','2019-03-01 10:13:00',11,33),
	(11000001,101,NULL,1100016003,'星巴克-家佳源店','焦糖星冰乐',NULL,NULL,NULL,1,NULL,'{\"杯\":[\"中杯\",\"大杯\",\"特大杯\"]}',NULL,'2019-02-28 18:24:21','2019-03-01 10:00:37',22,22),
	(11000002,101,NULL,1100016003,'星巴克-家佳源店','鸡公煲中份',NULL,NULL,NULL,-2,NULL,'{\"口味\":[\"中辣\",\"特辣\"]}',NULL,'2019-02-28 18:26:32','2019-02-28 18:26:32',18,0),
	(11000022,101,NULL,1100016003,'星巴克-家佳源店','asdasd',NULL,'string',NULL,-2,NULL,'{\"test\":\"asasd\"}',NULL,'2019-03-23 17:09:27','2019-03-23 17:09:27',123,0);

/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table item_comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `item_comment`;

CREATE TABLE `item_comment` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `top_id` bigint(20) DEFAULT NULL COMMENT '根节点id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `user_name` varchar(64) NOT NULL COMMENT '用户名',
  `user_avatar` varchar(128) DEFAULT NULL COMMENT '用户头像',
  `user_type` smallint(6) NOT NULL COMMENT '用户类型',
  `item_id` bigint(20) NOT NULL COMMENT '商品id',
  `sku_order_id` bigint(20) NOT NULL COMMENT 'sku订单id',
  `item_name` varchar(128) DEFAULT NULL COMMENT '商品名',
  `sku_attr_json` varchar(512) DEFAULT NULL COMMENT 'sku销售属性',
  `shop_id` bigint(20) NOT NULL COMMENT '店铺id',
  `quality` smallint(20) NOT NULL COMMENT '购买数量',
  `describe` smallint(3) DEFAULT NULL COMMENT '商品描述符合程度',
  `service` smallint(3) DEFAULT NULL COMMENT '服务',
  `express` smallint(3) DEFAULT NULL COMMENT '快递服务',
  `context` varchar(1024) DEFAULT NULL COMMENT '买家评论',
  `images_json` varchar(1024) DEFAULT NULL COMMENT '评论图片',
  `status` smallint(3) NOT NULL COMMENT '状态',
  `has_pursue` smallint(1) NOT NULL,
  `extra_json` text,
  `tags` bigint(32) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_item_id` (`item_id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品评论';



# Dump of table order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `order`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺订单';

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;

INSERT INTO `order` (`id`, `out_id`, `buyer_id`, `buyer_name`, `shop_name`, `shop_id`, `enable_status`, `pay_status`, `status`, `receive_status`, `reverse_status`, `pay_at`, `shipping_at`, `confirm_at`, `paid_amount`, `accomplish_at`, `item_total_amount`, `delivery_address`, `buyer_notes`, `shop_notes`, `tag`, `extra_json`, `created_at`, `updated_at`)
VALUES
	(1001,'1',8,'pony','小康人家',1100016005,1,'-1','1','-1','-1',NULL,NULL,NULL,12,NULL,NULL,'青岛理工大学','少放香菜，多放辣椒','string',NULL,NULL,'2019-03-18 19:29:14','2019-03-18 19:29:14'),
	(1002,NULL,8,'pony','星巴克-家佳源店',1100016003,1,'-1','1','-1','-1',NULL,NULL,NULL,65,NULL,2,NULL,'不要放香菜',NULL,NULL,NULL,'2019-03-20 19:23:14','2019-03-20 19:23:14');

/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table order_line
# ------------------------------------------------------------

DROP TABLE IF EXISTS `order_line`;

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
  `receive_status` tinyint(4) DEFAULT NULL COMMENT '接受状态',
  `extra_json` varchar(512) DEFAULT NULL COMMENT '拓展字段',
  `status` tinyint(4) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_order_line_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单行';

LOCK TABLES `order_line` WRITE;
/*!40000 ALTER TABLE `order_line` DISABLE KEYS */;

INSERT INTO `order_line` (`id`, `out_id`, `order_id`, `buyer_id`, `buyer_name`, `shop_id`, `shop_name`, `item_id`, `item_code`, `item_name`, `item_image`, `item_attr`, `quantity`, `paid_amount`, `confirm_at`, `receive_status`, `extra_json`, `status`, `created_at`, `updated_at`)
VALUES
	(100001,NULL,1002,8,'pony',1100016003,'星巴克-家佳源店',11000000,NULL,'鸡公煲小份',NULL,NULL,1,22,NULL,-1,NULL,1,'2019-03-20 19:23:14','2019-03-20 19:23:14'),
	(100002,NULL,1002,8,'pony',1100016003,'星巴克-家佳源店',11000001,NULL,'焦糖星冰乐',NULL,NULL,1,43,NULL,-1,NULL,1,'2019-03-20 19:23:14','2019-03-20 19:23:14');

/*!40000 ALTER TABLE `order_line` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table shop
# ------------------------------------------------------------

DROP TABLE IF EXISTS `shop`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺表';

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;

INSERT INTO `shop` (`id`, `outer_id`, `user_id`, `user_name`, `name`, `type`, `mobile`, `email`, `image_url`, `address`, `tags`, `status`, `extra_json`, `created_at`, `updated_at`)
VALUES
	(1100016003,NULL,100,'dx','星巴克-家佳源店',0,'17806236033','tao@qtu404.com',NULL,'青岛市黄岛区家佳源一楼',NULL,1,NULL,'2018-03-01 00:00:00','2019-03-08 16:26:16'),
	(1100016004,NULL,100,'葛华亚','星巴克-佳世客店',1,'17806236033',NULL,NULL,'青岛市黄岛区长江中路佳世客',NULL,-2,NULL,'2019-03-05 09:39:11','2019-03-08 16:25:59'),
	(1100016005,NULL,7,'任小康','小康人家',1,'17812341234',NULL,NULL,NULL,NULL,-2,NULL,'2019-03-08 15:53:40','2019-03-08 15:53:40');

/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table shop_category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `shop_category`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='店铺类目表';

LOCK TABLES `shop_category` WRITE;
/*!40000 ALTER TABLE `shop_category` DISABLE KEYS */;

INSERT INTO `shop_category` (`id`, `shop_id`, `name`, `remark`, `pid`, `logo`, `level`, `has_children`, `type`, `sort_index`, `disclosed`, `status`, `extra_json`, `created_at`, `updated_at`)
VALUES
	(101,1100016003,'煲类','不含米饭',-1,NULL,1,NULL,NULL,1,NULL,1,NULL,'2019-03-01 11:49:43','2019-03-01 11:49:43'),
	(102,1100016003,'饮料','',-1,NULL,1,NULL,NULL,1,NULL,1,NULL,'2019-03-01 11:49:43','2019-03-01 11:49:43');

/*!40000 ALTER TABLE `shop_category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tag
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tag`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='打标信息';

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;

INSERT INTO `tag` (`id`, `name`, `type`, `content`, `extra_json`, `status`, `created_at`, `updated_at`)
VALUES
	(101,'咖啡',1,NULL,NULL,1,'2019-03-07 14:28:45','2019-03-07 14:28:45'),
	(102,'火锅',1,NULL,NULL,1,'2019-03-07 14:29:00','2019-03-07 14:29:00'),
	(103,'家常菜',1,NULL,NULL,1,'2019-03-08 15:50:23','2019-03-08 15:50:23');

/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tag_binding
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tag_binding`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='抽象标签绑定表';

LOCK TABLES `tag_binding` WRITE;
/*!40000 ALTER TABLE `tag_binding` DISABLE KEYS */;

INSERT INTO `tag_binding` (`id`, `tag_id`, `type`, `target_id`, `extra_json`, `status`, `created_at`, `updated_at`)
VALUES
	(28,103,1,1100016005,NULL,1,'2019-03-08 15:53:40','2019-03-08 15:53:40'),
	(29,101,1,1100016004,NULL,-3,'2019-03-08 16:23:20','2019-03-08 16:23:20'),
	(30,101,1,1100016004,NULL,-3,'2019-03-08 16:23:59','2019-03-08 16:23:59'),
	(31,101,1,1100016004,NULL,-3,'2019-03-08 16:24:22','2019-03-08 16:24:22'),
	(32,101,1,1100016004,NULL,-3,'2019-03-08 16:24:22','2019-03-08 16:24:22'),
	(33,101,1,1100016004,NULL,1,'2019-03-08 16:25:59','2019-03-08 16:25:59'),
	(34,101,1,1100016003,NULL,1,'2019-03-08 16:26:16','2019-03-08 16:26:16');

/*!40000 ALTER TABLE `tag_binding` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `nickname`, `username`, `email`, `mobile`, `password`, `type`, `avatar`, `name`, `status`, `roles_json`, `extra_json`, `tags_json`, `created_at`, `updated_at`)
VALUES
	(5,'wildhunt',NULL,NULL,'17866625604','123',2,'static.qtu404.com/nf4slide/assets/user.png',NULL,1,NULL,NULL,NULL,'2019-02-27 16:00:06','2019-02-27 16:00:06'),
	(6,'ya',NULL,NULL,'17806236033','123',2,'static.qtu404.com/nf4slide/assets/user.png',NULL,1,NULL,NULL,NULL,'2019-02-27 16:00:06','2019-02-27 16:00:06'),
	(7,'toable','toable',NULL,'17812341234','1',2,'static.qtu404.com/nf4slide/assets/user.png',NULL,1,NULL,NULL,NULL,'2018-01-01 00:00:00','2019-03-08 15:53:40'),
	(8,'pony','pony',NULL,'pony','1',1,'static.qtu404.com/nf4slide/assets/user.png',NULL,1,NULL,NULL,NULL,'2018-01-01 00:00:00','2018-01-01 00:00:00'),
	(100,'admin','admin',NULL,'admin','1',3,'static.qtu404.com/nf4slide/assets/user.png',NULL,1,NULL,NULL,NULL,'2019-02-27 16:00:06','2019-02-27 16:00:06');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
