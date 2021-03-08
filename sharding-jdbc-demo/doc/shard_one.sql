/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : shard_one

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2021-03-08 13:41:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user0`
-- ----------------------------
DROP TABLE IF EXISTS `t_user0`;
CREATE TABLE `t_user0` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `city_id` int(12) DEFAULT NULL COMMENT '城市',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别',
  `phone` varchar(32) DEFAULT NULL COMMENT '电话',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user0
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user1`
-- ----------------------------
DROP TABLE IF EXISTS `t_user1`;
CREATE TABLE `t_user1` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `city_id` int(12) DEFAULT NULL COMMENT '城市',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别',
  `phone` varchar(32) DEFAULT NULL COMMENT '电话',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user1
-- ----------------------------
