# ************************************************************
# Sequel Pro SQL dump
# Version 5425
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.23)
# Database: neptune
# Generation Time: 2019-02-28 08:32:20 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE=''NO_AUTO_VALUE_ON_ZERO'' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table shop
# ------------------------------------------------------------

DROP TABLE IF EXISTS `shop`;

CREATE TABLE `shop` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `outer_id` varchar(32) DEFAULT NULL COMMENT ''外部店铺编码'',
  `user_id` bigint(20) DEFAULT NULL COMMENT ''商家id'',
  `user_name` varchar(32) DEFAULT NULL COMMENT ''商家名称'',
  `name` varchar(64) NOT NULL COMMENT ''店铺名称'',
  `type` tinyint(1) NOT NULL COMMENT ''店铺类型 1:门店 2:商家'',
  `mobile` varchar(32) DEFAULT NULL COMMENT ''联系电话'',
  `email` varchar(32) DEFAULT NULL COMMENT ''电子邮件'',
  `image_url` varchar(128) DEFAULT NULL COMMENT ''店铺图片url'',
  `address` varchar(128) DEFAULT NULL COMMENT ''店铺地址'',
  `tags` bigint(20) DEFAULT NULL COMMENT ''店铺标签'',
  `status` tinyint(1) NOT NULL COMMENT ''状态 1:正常, -1:关闭, -2:冻结'',
  `extra_json` varchar(4096) DEFAULT NULL COMMENT ''店铺额外信息,建议json字符串'',
  `created_at` datetime NOT NULL COMMENT ''创建时间'',
  `updated_at` datetime NOT NULL COMMENT ''更新时间'',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT=''店铺表'';



# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(40) DEFAULT NULL COMMENT ''昵称'',
  `username` varchar(40) DEFAULT NULL COMMENT ''用户名'',
  `email` varchar(32) DEFAULT NULL COMMENT ''邮件'',
  `mobile` varchar(16) DEFAULT NULL COMMENT ''手机号码'',
  `password` varchar(32) DEFAULT NULL COMMENT ''登录密码'',
  `type` smallint(6) NOT NULL COMMENT ''用户类型'',
  `avatar` varchar(255) DEFAULT NULL COMMENT ''头像图片地址'',
  `name` varchar(40) DEFAULT NULL COMMENT ''真实姓名'',
  `status` tinyint(4) NOT NULL COMMENT ''状态 0:未激活, 1:正常, -1:锁定, -2:冻结, -3: 删除'',
  `roles_json` varchar(512) DEFAULT NULL COMMENT ''用户权限信息'',
  `extra_json` varchar(1024) DEFAULT NULL COMMENT ''用户额外信息'',
  `tags_json` varchar(1024) DEFAULT NULL COMMENT ''用户标签的json表示形式'',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_users_name` (`username`),
  UNIQUE KEY `idx_users_email` (`email`),
  UNIQUE KEY `idx_users_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=''用户表'';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
