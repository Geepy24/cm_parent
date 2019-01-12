/*
 Navicat MySQL Data Transfer

 Source Server         : link
 Source Server Type    : MySQL
 Source Server Version : 50642
 Source Host           : localhost:3306
 Source Schema         : cm

 Target Server Type    : MySQL
 Target Server Version : 50642
 File Encoding         : 65001

 Date: 12/01/2019 10:29:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for picture_check
-- ----------------------------
DROP TABLE IF EXISTS `picture_check`;
CREATE TABLE `picture_check` (
  `pic_id` int(11) NOT NULL AUTO_INCREMENT,
  `pic_uri` varchar(255) DEFAULT NULL,
  `pic_name` varchar(255) DEFAULT NULL,
  `check_tag` int(4) DEFAULT NULL COMMENT '-1/0/1',
  `check_com` varchar(255) DEFAULT NULL COMMENT '注释',
  `user_id` bigint(32) NOT NULL COMMENT '不是外键',
  `res_com` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
