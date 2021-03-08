/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : shard_two

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2021-03-08 13:42:03
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
INSERT INTO `t_user1` VALUES ('1364459628488765441', '丁-2', null, null, 'phone-2', null, '2021-02-24 14:18:20', 'pw2');
INSERT INTO `t_user1` VALUES ('1364459628891418625', '丁-4', null, null, 'phone-4', null, '2021-02-24 14:18:20', 'pw4');
INSERT INTO `t_user1` VALUES ('1364459629205991425', '丁-5', null, null, 'phone-5', null, '2021-02-24 14:18:20', 'pw5');
